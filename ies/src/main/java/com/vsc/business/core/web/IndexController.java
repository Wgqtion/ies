package com.vsc.business.core.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vsc.business.core.entity.security.Authority;
import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.service.security.AuthorityService;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.modules.entity.ReportView;
import com.vsc.modules.shiro.ShiroUserUtils;

@Controller
public class IndexController extends BaseController {

	@Autowired
	private AuthorityService resourceService;
	
	@Autowired
	private ParkingLotService parkingLotService;
	
	
	@RequestMapping(value = "/index")
	public String index(Model model) throws Exception {
		User user=ShiroUserUtils.GetCurrentUser();
		//功能模块
		List<Authority> homePage=resourceService.getMenus(user.getId(),0);
		List<Authority> resources1=resourceService.getMenus(user.getId(),1);
		List<Authority> resources2=resourceService.getMenus(user.getId(),2);
		List<Authority> resources3=resourceService.getMenus(user.getId(),3);
		model.addAttribute("homePage", homePage);
		model.addAttribute("resources1", resources1);
		model.addAttribute("resources2", resources2);
		model.addAttribute("resources3", resources3);
		return "index";
	}

	@RequestMapping(value = "/welcome")
	public String welcome(Model model) {
		return "welcome";
	}

	/**
	 * 首页
	 * @throws Exception 
	 */
	@RequestMapping(value = "/homeView")
	public String homeView(Model model, ReportView reportView, HttpServletRequest request) throws Exception {
		Date now = new Date();
		reportView.setStartDate(DateFormatUtils.format(now, "yyyy-MM-dd"));
		reportView.setEndDate(reportView.getStartDate());

		Map<String, Object> filterParams = new HashMap<String, Object>();
		filterParams.put("EQ_isEnabled", 1);
		List<ParkingLot> lotAreaList = parkingLotService.findList(filterParams);

		model.addAttribute("reportView", reportView);
		model.addAttribute("lotAreaList", lotAreaList);
		return "homeView";
	}
	
}