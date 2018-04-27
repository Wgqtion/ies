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

import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.Announcement;
import com.vsc.business.gerd.service.work.AnnouncementService;
import com.vsc.constants.Constants;
import com.vsc.util.CoreUtils;

/**
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + AnnouncementController.PATH)
public class AnnouncementController extends BaseController {

	@Autowired
	private AnnouncementService announcementService;
	public static final String PATH = "work/ann";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) throws Exception {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();

		Page<Announcement> page = announcementService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH_LIST;
	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new Announcement());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid Announcement announcement, @RequestParam("user.id") Long[] uids) throws Exception {
		User user = this.getCurrentUser();
		announcement.setCreateTime(CoreUtils.nowtime());
		announcement.setCreateUser(user);
		announcementService.save(announcement, uids, user.getId());
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") java.lang.Long id, Model model) {
		model.addAttribute("vm", announcementService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") java.lang.Long id, Model model) {
		model.addAttribute("vm", announcementService.getObjectById(id));
		return PATH_VIEW;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") Announcement announcement,
			@RequestParam("user.id") Long[] uids) throws Exception {
		announcementService.save(announcement, uids,  this.getCurrentShiroUser().id);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") java.lang.Long id) {
		announcementService.deleteById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = "destroy", method = RequestMethod.POST)
	public ModelAndView destroyBatch(@RequestParam java.lang.Long[] ids) throws Exception {
		announcementService.destroyByIds(ids);
		return this.ajaxDoneSuccess("撤销成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam java.lang.Long[] ids) {
		announcementService.deleteByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	@ModelAttribute("preloadModel")
	public Announcement getModel(@RequestParam(value = "id", required = false) java.lang.Long id) {
		if (id != null) {
			return announcementService.getObjectById(id);
		}
		return null;
	}

}
