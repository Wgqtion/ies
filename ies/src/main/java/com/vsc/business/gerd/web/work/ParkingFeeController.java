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
import com.vsc.business.gerd.entity.work.ParkingFee;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.business.gerd.service.work.ParkingFeeService;
import com.vsc.constants.Constants;

/**
 * 收费设置视图控制
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ParkingFeeController.PATH)
public class ParkingFeeController extends BaseController {

	@Autowired
	private ParkingFeeService parkingFeeService;
	
	@Autowired
	private ParkingLotService parkingLotService;
	
	public static final String PATH = "work/parkingFee";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SELECT = PATH + Constants.SPT + "select";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request,
			ParkingFee entity) throws Exception {

		PageRequest pageRequest = this.getPageRequest("type,parkingLotCode,week,startTime","ASC,ASC,ASC,ASC");
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<ParkingFee> page = parkingFeeService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		model.addAttribute("entity",entity);
		return PATH_LIST;
	}
	
	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) throws Exception {
		ParkingFee entity=new ParkingFee();
		model.addAttribute("vm",entity);
		model.addAttribute("action", BaseController.CREATE);
		model.addAttribute("parkingLotTree",this.parkingLotService.findTree());
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid ParkingFee entity) throws Exception {
		parkingFeeService.save(entity);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") java.lang.Long id, Model model) throws Exception {
		ParkingFee entity= parkingFeeService.getObjectById(id);
		model.addAttribute("vm",entity);
		model.addAttribute("action", BaseController.UPDATE);
		model.addAttribute("parkingLot",entity.getParkingLot());
		model.addAttribute("parkingLotTree",this.parkingLotService.findTree());
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") ParkingFee entity) throws Exception {
		parkingFeeService.save(entity);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") java.lang.Long id) throws Exception {
		parkingFeeService.deleteUpdateById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam java.lang.Long[] ids) throws Exception {
		parkingFeeService.deleteUpdateByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	@ModelAttribute("preloadModel")
	public ParkingFee getModel(@RequestParam(value = "id", required = false) java.lang.Long id) {
		if (id != null) {
			return parkingFeeService.getObjectById(id);
		}
		return null;
	}

}
