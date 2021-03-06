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
import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.entity.work.ParkingLockOperationEvent;
import com.vsc.business.gerd.service.work.ParkingLockService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.constants.Constants;

/**
 * 地锁视图
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ParkingLockController.PATH)
public class ParkingLockController extends BaseController {

	@Autowired
	private ParkingLockService parkingLockService;
	
	@Autowired
	private ParkingLotService parkingLotService;
	
	public static final String PATH = "work/parkinglock";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";
	public static final String PATH_OPEN = PATH + Constants.SPT + "open";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) throws Exception {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();
		model.addAttribute("searchCodeParkingLock", searchParams.get("IN_parkingGarage.parkingLotCode"));
		Page<ParkingLock> page = parkingLockService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		model.addAttribute("parkingLotTree",this.parkingLotService.findTree());
		return PATH_LIST;
	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new ParkingLock());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid ParkingLock entity,
			@RequestParam(value = "parkingGarageGroup.id", required = false) Long parkingGarageId) throws Exception {
		ParkingGarage pg=new ParkingGarage();
		pg.setId(parkingGarageId);
		entity.setParkingGarage(pg);
		parkingLockService.save(entity);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", parkingLockService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", parkingLockService.getObjectById(id));
		return PATH_VIEW;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") ParkingLock entity,
			@RequestParam(value = "parkingGarageGroup.id", required = false) Long parkingGarageId) throws Exception {
		ParkingGarage pg=new ParkingGarage();
		pg.setId(parkingGarageId);
		entity.setParkingGarage(pg);
		parkingLockService.save(entity);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) throws Exception {
		parkingLockService.deleteUpdateById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = "reverse", method = RequestMethod.POST)
	public ModelAndView reverseBatch(@RequestParam Long[] ids,@RequestParam(value = "state", required = true) String state) throws Exception {
		if(ids!=null&&ids.length>0){		
			String message=this.parkingLockService.reverse(ids, state, this.getCurrentShiroUser().id+"",
					ParkingLockOperationEvent.SOURCETYPE_PC);
			if(message.length()>0){
				return this.ajaxDoneError(message);
			}
		}
		
		return this.ajaxDoneSuccess("指令下发成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) throws Exception {
		parkingLockService.deleteUpdateByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	@ModelAttribute("preloadModel")
	public ParkingLock getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return parkingLockService.getObjectById(id);
		}
		return null;
	}

}
