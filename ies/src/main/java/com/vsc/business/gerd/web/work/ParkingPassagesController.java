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
import com.vsc.business.gerd.entity.work.ParkingPassages;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.business.gerd.service.work.ParkingPassagesService;
import com.vsc.constants.Constants;

 
/**
 * 进出口 视图控制
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value =  Constants.SPT+ParkingPassagesController.PATH)
public class ParkingPassagesController extends BaseController {
	 
	@Autowired
	private ParkingPassagesService parkingPassagesService;
	
	@Autowired
	private ParkingLotService parkingLotService;
	
	public static final String PATH = "work/parkingpassages";
	public static final String PATH_LIST = PATH +Constants.SPT+ "list";
	public static final String PATH_EDIT = PATH + Constants.SPT+"edit";
	public static final String PATH_VIEW = PATH + Constants.SPT+"view";
	public static final String PATH_SEARCH = PATH + Constants.SPT+"search";
	public static final String PATH_SELECT = PATH + Constants.SPT + "select";
	
	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) throws Exception {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();
	 

		Page<ParkingPassages> page = parkingPassagesService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
	
		return PATH_LIST;
	}
	

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) throws Exception {
		model.addAttribute("vm", new ParkingPassages());
		model.addAttribute("action", BaseController.CREATE);
		model.addAttribute("parkingLotTree",this.parkingLotService.findTree());
		return PATH_EDIT;
	}

	@RequestMapping(value =  BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid ParkingPassages parkingPassages) throws Exception {
		parkingPassagesService.save(parkingPassages);		 
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value =  BaseController.UPDATE+"/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		ParkingPassages parkingPassages=parkingPassagesService.getObjectById(id);
		model.addAttribute("vm", parkingPassages);
		model.addAttribute("parkingLot",parkingPassages.getParkingLot());
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel")ParkingPassages parkingPassages) throws Exception {
		parkingPassagesService.save(parkingPassages);		
		return this.ajaxDoneSuccess("修改成功");
	}
	
	
	@RequestMapping(value =  BaseController.VIEW+"/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", parkingPassagesService.getObjectById(id));
		return PATH_VIEW;
	}

	@RequestMapping(value = BaseController.DELETE+"/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) throws Exception {
		parkingPassagesService.deleteUpdateById(id);		 
		return this.ajaxDoneSuccess("删除成功");
	}
	
	@RequestMapping(value = BaseController.DELETE,method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) throws Exception {
		parkingPassagesService.deleteUpdateByIds(ids);		 
		return this.ajaxDoneSuccess("删除成功");
	}

	 
	@ModelAttribute("preloadModel")
	public ParkingPassages getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return parkingPassagesService.getObjectById(id);
		}
		return null;
	}

}
