package com.vsc.business.core.web.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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


	@RequestMapping(value = "select/{roleId}", method = RequestMethod.GET)
	public String select(@PathVariable("roleId") Long roleId, Model model, ServletRequest request) throws Exception {
		List<Authority> list = authorityService.findAll(new HashMap<String, Object>(),"parentCode,sort", "asc,asc");
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_roleList.id",roleId);
		List<Authority> authoritys =authorityService.findList(searchParams);
		model.addAttribute("list", list);
		model.addAttribute("authoritys", authoritys);
		model.addAttribute("roleId", roleId);
		return "sys/authority/select";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(Long roleId,String codes) throws Exception {
		this.authorityService.save(roleId, codes);
		return this.ajaxDoneSuccess("保存成功");
	}

	@ModelAttribute("preloadModel")
	public Authority getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return authorityService.getObjectById(id);
		}
		return null;
	}
}
