package com.vsc.business.gerd.web.work;

import java.util.List;
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

import com.google.common.collect.Maps;
import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.service.work.ParkingGarageService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.constants.Constants;

/**
 * 
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ParkingGarageController.PATH)
public class ParkingGarageController extends BaseController {

	@Autowired
	private ParkingGarageService parkingGarageService;
	
	@Autowired
	private ParkingLotService parkingLotService;

	public static final String PATH = "work/parkinggarage";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";
	public static final String PATH_SELECT = PATH + Constants.SPT + "select";
	public static final String PATH_TREE = PATH + Constants.SPT + "tree";

	@RequestMapping(value = "")
	public String tree(Model model, HttpServletRequest request) {
		List<ParkingLot> vl = this.parkingLotService.findTree();
		this.list(model, request,null);
		model.addAttribute("vl", vl);
		return PATH_TREE;
	}

	@RequestMapping(value = "list")
	public String list(Model model, HttpServletRequest request,
			Long parkingLotId) {
		PageRequest pageRequest = this.getPageRequest("parkingLot.code","ASC");
		Map<String, Object> searchParams = this.getSearchRequest();
		if(parkingLotId!=null){
			ParkingLot parkingLot=this.parkingLotService.getObjectById(parkingLotId);
			searchParams.put("RLIKE_parkingLot.code",parkingLot.getCode());
			model.addAttribute("parkingLotId", parkingLotId);
		}
		Page<ParkingGarage> page = parkingGarageService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		return PATH_LIST;
	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model,
			Long parkingLotId) {
		model.addAttribute("vm", new ParkingGarage());
		model.addAttribute("action", BaseController.CREATE);
		model.addAttribute("parkingLot",this.parkingLotService.getObjectById(parkingLotId));
		Map<String, Object> searchParams = Maps.newHashMap();
		model.addAttribute("parkingLotTree",this.parkingLotService.findList(searchParams));
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid ParkingGarage parkingGarage) {
		parkingGarageService.save(parkingGarage);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		ParkingGarage parkingGarage=parkingGarageService.getObjectById(id);
		model.addAttribute("vm", parkingGarage);
		model.addAttribute("parkingLot",parkingGarage.getParkingLot());
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") ParkingGarage parkingGarage) {
		parkingGarageService.save(parkingGarage);
		return this.ajaxDoneSuccess("修改成功");
	}


	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", parkingGarageService.getObjectById(id));
		return PATH_VIEW;
	}
	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		parkingGarageService.deleteUpdateById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		parkingGarageService.deleteUpdateByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = "select")
	public String select(Model model, ServletRequest request) {
		PageRequest pageRequest = this.getPageRequest("name", "asc");
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<ParkingGarage> page = parkingGarageService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		return PATH_SELECT;
	}

	 
	@ModelAttribute("preloadModel")
	public ParkingGarage getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return parkingGarageService.getObjectById(id);
		}
		return null;
	}

}
