package com.vsc.business.core.web.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
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

import com.vsc.business.core.entity.security.Role;
import com.vsc.business.core.service.security.RoleService;
import com.vsc.business.core.web.BaseController;

@Controller
@RequestMapping(value = "/sys/role")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "")
	public String list(Model model, ServletRequest request) throws Exception {
		PageRequest pageRequest = this.getPageRequest("id","asc");
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<Role> page = roleService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		return "sys/role/list";
	}
	
	@RequestMapping(value = "select/{userId}", method = RequestMethod.GET)
	public String select(@PathVariable("userId") Long userId, Model model, ServletRequest request) throws Exception {
		List<Role> list = roleService.getAllList();
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_userList.id",userId);
		searchParams.put("EQ_userList.isDelete",0);
		List<Role> roles =roleService.findList(searchParams);
		model.addAttribute("list", list);
		model.addAttribute("roles", roles);
		model.addAttribute("userId", userId);
		return "sys/role/select";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(Long userId,String codes) throws Exception {
		this.roleService.save(userId, codes);
		return this.ajaxDoneSuccess("保存成功");
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", roleService.getObjectById(id));
		return "sys/role/view";
	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new Role());
		model.addAttribute("action", BaseController.CREATE);
		return "sys/role/edit";
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid Role role) throws Exception {
		roleService.save(role);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", roleService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return "sys/role/edit";
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") Role role) throws Exception {
		roleService.save(role);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		roleService.deleteById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		roleService.deleteByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}
 

	@ModelAttribute("preloadModel")
	public Role getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return roleService.getObjectById(id);
		}
		return null;
	}
}
