package com.vsc.business.gerd.web.work;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.service.security.UserService;
import com.vsc.business.core.web.BaseController;
import com.vsc.constants.Constants;
import com.vsc.util.CoreUtils;

/**
 * 用户管理的Controller 
 * @author 付翀
 */
@Controller
@RequestMapping(value = Constants.SPT + MemberController.PATH)
public class MemberController extends BaseController {

	public static final String PATH = "work/member";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";
	public static final String PATH_SELECT = PATH + Constants.SPT + "select";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) {

		PageRequest pageRequest = this.getPageRequest("createTime", "desc");
		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("EQ_userType", 1);
		Page<User> page = userService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH_LIST;
	}

	@RequestMapping(value = "select")
	public String select(Model model, ServletRequest request) {
		PageRequest pageRequest = this.getPageRequest("name", "asc");
		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("EQ_userType", 1);
		Page<User> page = userService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		return PATH_SELECT;
	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new User());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid User user,
			@RequestParam(value = "photoAttachId", required = false) Long photoAttachId) {
		user.setPassword(user.getPlainPassword());
		user.setCreateTime(CoreUtils.nowtime());
		user.setUserType(1L);
		userService.save(user, photoAttachId, user.getUserType());
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", userService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", userService.getObjectById(id));
		return PATH_VIEW;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") User user,
			@RequestParam(value = "photoAttachId", required = false) Long photoAttachId) {
		//user.setPassword(MD5Util.MD5(user.getPlainPassword())); 
		user.setUpdateTime(CoreUtils.nowtime());
		user.setUserType(1L);
		userService.save(user, photoAttachId, user.getUserType());
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = "reset/{id}", method = RequestMethod.GET)
	public ModelAndView resetFrom(@PathVariable("id") Long id) {
		//user.setPassword(MD5Util.MD5(user.getPlainPassword())); 
		User user = userService.getObjectById(id);
		if (user == null) {
			return this.ajaxDoneError("会员不存在");
		}
		Subject subject = SecurityUtils.getSubject();
		if (!subject.hasRole("管理员")) {
			if (!this.getCurrentUser().getId().equals(user.getId())) {
				return this.ajaxDoneError("无权限访问");
			}
		}
		ModelAndView mav = new ModelAndView("work/member/reset");
		mav.addObject("vm", user);
		return mav;
	}

	@RequestMapping(value = "reset", method = RequestMethod.POST)
	public ModelAndView reset(@RequestParam("id") Long id, @RequestParam("plainPassword") String plainPassword) {
		User user = userService.getObjectById(id);
		if (user == null) {
			return this.ajaxDoneError("会员不存在");
		}
		user.setPassword(plainPassword);
		userService.save(user);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		userService.deleteByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}
	
	 


	@RequestMapping(value = "checkloginname")
	@ResponseBody
	public Boolean checkLoginName(@RequestParam(value = "oldLoginName", required = false) String oldLoginName,
			@RequestParam(value = "loginName") String loginName) {
		return userService.isPropertyUnique("loginName", loginName, oldLoginName);
	}

	@ModelAttribute("preloadModel")
	public User getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return userService.getObjectById(id);
		}
		return null;
	}

}
