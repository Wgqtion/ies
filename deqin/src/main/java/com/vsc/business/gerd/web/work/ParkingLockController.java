package com.vsc.business.gerd.web.work;

import java.util.List;
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
import com.vsc.business.gerd.entity.work.ParkingLock;
import com.vsc.business.gerd.entity.work.ParkingLockOperationEvent;
import com.vsc.business.gerd.service.work.ParkingLockService;
import com.vsc.constants.Constants;
import com.vsc.util.CoreUtils;

/**
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ParkingLockController.PATH)
public class ParkingLockController extends BaseController {

	@Autowired
	private ParkingLockService parkingLockService;
	public static final String PATH = "work/parkinglock";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";
	public static final String PATH_FOREVER = PATH + Constants.SPT + "forever";
	public static final String PATH_OPEN = PATH + Constants.SPT + "open";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();

		Page<ParkingLock> page = parkingLockService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH_LIST;
	}

	@RequestMapping(value = "export")
	public ModelAndView exportList(HttpServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		List<ParkingLock> list = parkingLockService.findAll(searchParams, this.getSortOrderBy(),
				this.getSortOrderDesc());
		return this.reportView(PATH_LIST, list, REPORT_FORMAT_XLS);

	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new ParkingLock());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid ParkingLock parkingLock,
			@RequestParam(value = "parkingGarageGroup.id", required = false) Long parkingGarageId,
			String surplusDetections) {
		parkingLock.setCreateTime(CoreUtils.nowtime());
		parkingLock.setSurplusDetection(surplusDetections);
		parkingLockService.save(parkingLock);
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
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") ParkingLock parkingLock,
			@RequestParam(value = "parkingGarageGroup.id", required = false) Long parkingGarageId,
			String surplusDetections) {
		parkingLock.setSurplusDetection(surplusDetections);
		parkingLockService.save(parkingLock);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		parkingLockService.deleteById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = "reverse", method = RequestMethod.POST)
	public ModelAndView reverseBatch(@RequestParam Long[] ids,@RequestParam(value = "state", required = true) String state) {
		if(ids!=null&&ids.length>0){		
			String message=this.parkingLockService.reverse(ids, state, this.getCurrentShiroUser().id,
					ParkingLockOperationEvent.SOURCETYPE_PC);
			if(message.length()>0){
				return this.ajaxDoneError(message);
			}
		}
		
		return this.ajaxDoneSuccess("指令下发成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		parkingLockService.deleteByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	/**
	 *  高级查询界面
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "search")
	public String search(HttpServletRequest request) {
		return PATH_SEARCH;
	}

	@ModelAttribute("preloadModel")
	public ParkingLock getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return parkingLockService.getObjectById(id);
		}
		return null;
	}

}
