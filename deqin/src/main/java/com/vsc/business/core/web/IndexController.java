package com.vsc.business.core.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vsc.business.core.service.security.RoleService;
import com.vsc.business.gerd.service.work.SystemNoticeService;

@Controller
public class IndexController extends BaseController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private SystemNoticeService systemNoticeService;

	@RequestMapping(value = "/index")
	public String index(Model model) {
		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();

		return "index";
	}

	@RequestMapping(value = "/welcome")
	public String welcome(Model model) {
		return "welcome";
	}

}