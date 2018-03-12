package com.vsc.business.core.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.modules.entity.ReportView;

@Controller
public class IndexController extends BaseController {

	@Autowired
	private ParkingLotService parkingLotService;
	
	
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

	/**
	 * 首页
	 */
	@RequestMapping(value = "/homeView")
	public String homeView(Model model, ReportView reportView, HttpServletRequest request) {
		Date now = new Date();
		reportView.setStartDate(DateFormatUtils.format(now, "yyyy-MM-dd"));
		reportView.setEndDate(reportView.getStartDate());

		Map<String, Object> filterParams = new HashMap<String, Object>();
		User user=this.getCurrentUser();
		filterParams.put("RLIKE_companyCode", user.getCompany().getCode());
		filterParams.put("EQ_isDelete", 0);
		filterParams.put("EQ_isEnabled", 1);
		List<ParkingLot> lotAreaList = parkingLotService.findList(filterParams);

		model.addAttribute("reportView", reportView);
		model.addAttribute("lotAreaList", lotAreaList);
		return "homeView";
	}
	
}