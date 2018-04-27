package com.vsc.business.gerd.web.work;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.SystemNotice;
import com.vsc.business.gerd.service.work.SystemNoticeService;
import com.vsc.constants.Constants;
import com.vsc.util.CoreUtils;

/**
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + SystemNoticeController.PATH)
public class SystemNoticeController extends BaseController {

	@Autowired
	private SystemNoticeService systemNoticeService;
	public static final String PATH = "work/systemnotice";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";
	public static final String PATH_MINE = PATH + Constants.SPT + "mine";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) throws Exception {

		PageRequest pageRequest = this.getPageRequest("sendTime", "DESC");
		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("EQ_toUser", this.getCurrentShiroUser().id);
		Page<SystemNotice> page = systemNoticeService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH_LIST;
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") java.lang.Long id, Model model) throws Exception {
		SystemNotice vm = systemNoticeService.getObjectById(id);
		if (!vm.getIsRead()) {
			vm.setIsRead(Boolean.TRUE);
			vm.setReadTime(CoreUtils.nowtime());
			systemNoticeService.save(vm);
		}
		model.addAttribute("vm", vm);
		return PATH_VIEW;
	}

	@ModelAttribute("preloadModel")
	public SystemNotice getModel(@RequestParam(value = "id", required = false) java.lang.Long id) {
		if (id != null) {
			return systemNoticeService.getObjectById(id);
		}
		return null;
	}

	@RequestMapping(value = "mine")
	public String mine(Model model, HttpServletRequest request) throws Exception {

		PageRequest pageRequest = this.getPageRequest("sendTime", "DESC");
		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("EQ_toUser", this.getCurrentShiroUser() == null ? 0 : this.getCurrentShiroUser().id);
		Page<SystemNotice> page = systemNoticeService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH_MINE;
	}

}
