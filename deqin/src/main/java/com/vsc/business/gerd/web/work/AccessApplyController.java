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
import com.vsc.business.gerd.entity.work.AccessApply;
import com.vsc.business.gerd.service.work.AccessApplyService;
import com.vsc.business.gerd.service.work.CardInfoService;
import com.vsc.business.gerd.service.work.PassagesService;
import com.vsc.constants.Constants;
import com.vsc.util.CoreUtils;

 
/**
 * 
 * @author ThinkPad
 *
 */
@Controller
@RequestMapping(value =  Constants.SPT+AccessApplyController.PATH)
public class AccessApplyController extends BaseController {
	 
	@Autowired
	private AccessApplyService accessApplyService;
	
	@Autowired
	private CardInfoService cardInfoService;
	
	@Autowired
	private PassagesService passagesService;
	
	public static final String PATH = "work/accessapply";
	public static final String PATH_LIST = PATH +Constants.SPT+ "list";
	public static final String PATH_EDIT = PATH + Constants.SPT+"edit";
	public static final String PATH_VIEW = PATH + Constants.SPT+"view";
	public static final String PATH_SEARCH = PATH + Constants.SPT+"search";
	
	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();
	 

		Page<AccessApply> page = accessApplyService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
	
		return PATH_LIST;
	}
	
	@RequestMapping(value = "export")
	public ModelAndView exportList(HttpServletRequest request){
		Map<String, Object> searchParams = this.getSearchRequest();
		List<AccessApply> list=accessApplyService.findAll(searchParams, this.getSortOrderBy(), this.getSortOrderDesc());
		return this.reportView(PATH_LIST, list, REPORT_FORMAT_XLS);
		
	}
	
	

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new AccessApply());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value =  BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid AccessApply accessApply,
			@RequestParam(value = "applyUserGroup.id", required = true) Long applyUserId,
			@RequestParam(value = "cardInfoGroup.id", required = true) Long carInfoId,
			@RequestParam(value = "passagesGroup.id", required = true) Long passagesId) {
		
		
		accessApply.setUser(userService.getObjectById(applyUserId));
		accessApply.setCardInfo(cardInfoService.getObjectById(carInfoId));
		accessApply.setPassages(passagesService.getObjectById(passagesId));
		accessApply.setCreateTime(CoreUtils.nowtime());
		accessApply.setCreateUser(this.getCurrentUser());
	 
		
		accessApplyService.save(accessApply);		 
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value =  BaseController.UPDATE+"/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", accessApplyService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}
	
	
	@RequestMapping(value =  BaseController.VIEW+"/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", accessApplyService.getObjectById(id));
		return PATH_VIEW;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") AccessApply accessApply,
			@RequestParam(value = "applyUserGroup.id", required = true) Long applyUserId,
			@RequestParam(value = "cardInfoGroup.id", required = true) Long carInfoId,
			@RequestParam(value = "passagesGroup.id", required = true) Long passagesId) {
		
		accessApply.setUser(userService.getObjectById(applyUserId));
		accessApply.setCardInfo(cardInfoService.getObjectById(carInfoId));
		accessApply.setPassages(passagesService.getObjectById(passagesId));
	 
		accessApplyService.save(accessApply);		
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE+"/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		accessApplyService.deleteById(id);		 
		return this.ajaxDoneSuccess("删除成功");
	}
	
	@RequestMapping(value = BaseController.DELETE,method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		accessApplyService.deleteByIds(ids);		 
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
	public AccessApply getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return accessApplyService.getObjectById(id);
		}
		return null;
	}
	

}
