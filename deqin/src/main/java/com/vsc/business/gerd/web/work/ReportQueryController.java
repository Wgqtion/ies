package com.vsc.business.gerd.web.work;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vsc.business.core.web.BaseController;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.MapBean;

/**
 * 报表查询
 * 
 * @author 付翀
 * 
 */
@Controller
@RequestMapping(value = Constants.SPT + ReportQueryController.PATH)
public class ReportQueryController extends BaseController {

	public static final String PATH = "work/report/query";
	public static final String PATH_DSTJ = PATH + Constants.SPT + "dstj";
	public static final String PATH_CWTJ = PATH + Constants.SPT + "cwtj";

	@RequestMapping(value = "dstj")
	public String list(Model model, HttpServletRequest request) {
		Map<String, Object> filters = this.getSearchRequest();
		List<MapBean<String, Object>> vl = this.userService.findIbatisQuery("report.parkinglock.onlinenum.total", filters);
		model.addAttribute("vl", vl);
		return PATH_DSTJ;
	}

	@RequestMapping(value = "cwtj")
	public String cwtj(Model model, ServletRequest request) {
		Map<String, Object> filters = this.getSearchRequest();
		List<MapBean<String, Object>> vl = this.userService.findIbatisQuery("report.parkinggarage.onlinenum.total", filters);
		model.addAttribute("vl", vl);
		return PATH_CWTJ;
	}

}
