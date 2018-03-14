package com.vsc.business.gerd.web.work;

import java.util.Map;

import javax.servlet.ServletRequest;
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
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.entity.work.Passages;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.business.gerd.service.work.PassagesService;
import com.vsc.constants.Constants;

 
/**
 * 
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value =  Constants.SPT+PassagesController.PATH)
public class PassagesController extends BaseController {
	 
	@Autowired
	private PassagesService passagesService;
	
	@Autowired
	private ParkingLotService parkingLotService;
	
	public static final String PATH = "work/passages";
	public static final String PATH_LIST = PATH +Constants.SPT+ "list";
	public static final String PATH_EDIT = PATH + Constants.SPT+"edit";
	public static final String PATH_VIEW = PATH + Constants.SPT+"view";
	public static final String PATH_SEARCH = PATH + Constants.SPT+"search";
	public static final String PATH_SELECT = PATH + Constants.SPT + "select";
	
	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();
	 

		Page<Passages> page = passagesService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
	
		return PATH_LIST;
	}
	

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new Passages());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value =  BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid Passages passages,
			@RequestParam(value = "parkinglotGroup.id", required = true) Long parkinglotId) {
		
		ParkingLot pm = parkingLotService.getObjectById(parkinglotId);
		passages.setParkingLot(pm);
		
		passagesService.save(passages);		 
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value =  BaseController.UPDATE+"/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", passagesService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}
	
	
	@RequestMapping(value =  BaseController.VIEW+"/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", passagesService.getObjectById(id));
		return PATH_VIEW;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") Passages passages,
			@RequestParam(value = "parkinglotGroup.id", required = true) Long parkinglotId) {
		ParkingLot pm = parkingLotService.getObjectById(parkinglotId);
		passages.setParkingLot(pm);
		passagesService.save(passages);		
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE+"/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		passagesService.deleteUpdateById(id);		 
		return this.ajaxDoneSuccess("删除成功");
	}
	
	@RequestMapping(value = BaseController.DELETE,method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		passagesService.deleteUpdateByIds(ids);		 
		return this.ajaxDoneSuccess("删除成功");
	}

	 
	@ModelAttribute("preloadModel")
	public Passages getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return passagesService.getObjectById(id);
		}
		return null;
	}
	@RequestMapping(value = "select")
	public String select(Model model, ServletRequest request) {
		PageRequest pageRequest = this.getPageRequest("name", "asc");
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<Passages> page = passagesService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		return PATH_SELECT;
	}
	

}
