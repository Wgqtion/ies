package com.vsc.business.core.web.security;

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

import com.vsc.business.core.entity.security.Authority;
import com.vsc.business.core.service.security.AuthorityService;
import com.vsc.business.core.web.BaseController;

@Controller
@RequestMapping(value = "/sys/authority")
public class AuthorityController extends BaseController {
	@Autowired
	private AuthorityService authorityService;

	@RequestMapping(value = "")
	public String list(Model model, ServletRequest request) {
		PageRequest pageRequest = this.getPageRequest("name", "asc");
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<Authority> page = authorityService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		return "sys/authority/list";
	}

	@RequestMapping(value = "select")
	public String select(Model model, ServletRequest request) {
		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<Authority> page = authorityService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		return "sys/authority/select";
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", authorityService.getObjectById(id));
		return "sys/authority/view";
	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new Authority());
		model.addAttribute("action", BaseController.CREATE);
		return "sys/authority/edit";
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid Authority authority,
			@RequestParam(value = "resource.id", required = false) Long[] rIds) {
		authorityService.save(authority, rIds);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", authorityService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return "sys/authority/edit";
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") Authority authority,
			@RequestParam(value = "resource.id", required = false) Long[] rIds) {
		authorityService.save(authority, rIds);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		authorityService.deleteById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		authorityService.deleteByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	/**
	 *  高级查询界面
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	/*@RequestMapping(value = "search")
	public String search(ServletRequest request) {
		return "sys/authority/search";
	}*/

	@ModelAttribute("preloadModel")
	public Authority getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return authorityService.getObjectById(id);
		}
		return null;
	}
}
