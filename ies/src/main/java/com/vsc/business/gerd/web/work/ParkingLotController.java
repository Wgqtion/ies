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
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.service.work.CompanyService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.constants.Constants;

/**
 * 场区 视图控制
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ParkingLotController.PATH)
public class ParkingLotController extends BaseController {

	@Autowired
	private ParkingLotService parkingLotService;
	
	@Autowired
	private CompanyService companyService;
	
	public static final String PATH = "work/parkinglot";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";
	public static final String PATH_IMAGEVIEW=PATH + Constants.SPT + "imageview"; 
	public static final String PATH_TREE = PATH + Constants.SPT + "tree";
	
	@RequestMapping(value = "")
	public String tree(Model model, HttpServletRequest request) {
		List<ParkingLot> vl = this.parkingLotService.findTree();
		this.list(model, request,null,null);
		model.addAttribute("vl", vl);
		return PATH_TREE;
	}
	
	@RequestMapping(value = "list")
	public String list(Model model, HttpServletRequest request,
			Long companyId,Long parentId) {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();
		if(companyId!=null){
			searchParams.put("EQ_company.id",companyId);
			model.addAttribute("companyId", companyId);
		}
		if(parentId!=null){
			ParkingLot parent=this.parkingLotService.getObjectById(parentId);
			searchParams.put("RLIKE_parentCode",parent.getCode());
			model.addAttribute("parentId", parentId);
		}
		Page<ParkingLot> page = parkingLotService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH_LIST;
	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model,Long parentId) {
		model.addAttribute("vm", new ParkingLot());
		model.addAttribute("action", BaseController.CREATE);
		model.addAttribute("parent",this.parkingLotService.getObjectById(parentId));
		model.addAttribute("companyList",companyService.getList());
		model.addAttribute("parkingLotTree",this.parkingLotService.findTree());
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid ParkingLot parkingLot,
			@RequestParam(value = "photoAttachId", required = false) Long photoAttachId) throws Exception {
		parkingLotService.save(parkingLot, photoAttachId);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		ParkingLot parkingLot=parkingLotService.getObjectById(id);
		model.addAttribute("vm", parkingLot);
		model.addAttribute("action", BaseController.UPDATE);
		model.addAttribute("parent",parkingLot.getParent());
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") ParkingLot parkingLot,
			@RequestParam(value = "photoAttachId", required = false) Long photoAttachId) throws Exception {
		parkingLotService.save(parkingLot, photoAttachId);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", parkingLotService.getObjectById(id));
		return PATH_VIEW;
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		parkingLotService.deleteUpdateById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		parkingLotService.deleteUpdateByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}
	
	@RequestMapping(value = "orgAuthority")
	public String orgAuthority(Model model, ServletRequest request) {
		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<ParkingLot> page = parkingLotService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		return PATH+"/orgAuthority";
	}

	@ModelAttribute("preloadModel")
	public ParkingLot getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return parkingLotService.getObjectById(id);
		}
		return null;
	}
}
