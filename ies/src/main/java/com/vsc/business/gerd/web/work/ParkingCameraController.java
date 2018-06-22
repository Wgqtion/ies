package com.vsc.business.gerd.web.work;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.ParkingCamera;
import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.service.work.ParkingCameraService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.constants.Constants;

/**
 * 全视频相机视图
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ParkingCameraController.PATH)
public class ParkingCameraController extends BaseController {

	@Autowired
	private ParkingCameraService parkingCameraService;
	
	@Autowired
	private ParkingLotService parkingLotService;
	
	public static final String PATH = "work/parkingCamera";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) throws Exception {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();
		model.addAttribute("searchCodeParkingCamera", searchParams.get("IN_parkingGarage.parkingLotCode"));
		Page<ParkingCamera> page = parkingCameraService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		model.addAttribute("parkingLotTree",this.parkingLotService.findTree());
		return PATH_LIST;
	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new ParkingCamera());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid ParkingCamera entity,
			@RequestParam(value = "parkingGarageGroup.id", required = false) Long parkingGarageId) throws Exception {
		ParkingGarage pg=new ParkingGarage();
		pg.setId(parkingGarageId);
		entity.setParkingGarage(pg);
		parkingCameraService.save(entity);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", parkingCameraService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", parkingCameraService.getObjectById(id));
		return PATH_VIEW;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") ParkingCamera entity,
			@RequestParam(value = "parkingGarageGroup.id", required = false) Long parkingGarageId) throws Exception {
		ParkingGarage pg=new ParkingGarage();
		pg.setId(parkingGarageId);
		entity.setParkingGarage(pg);
		parkingCameraService.save(entity);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) throws Exception {
		parkingCameraService.deleteUpdateById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) throws Exception {
		parkingCameraService.deleteUpdateByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	@ModelAttribute("preloadModel")
	public ParkingCamera getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return parkingCameraService.getObjectById(id);
		}
		return null;
	}

}
