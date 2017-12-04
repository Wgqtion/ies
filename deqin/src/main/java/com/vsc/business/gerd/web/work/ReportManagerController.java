package com.vsc.business.gerd.web.work;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.service.work.ReportService;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.MapBean;

/**
 * 报表统计
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ReportManagerController.PATH)
public class ReportManagerController extends BaseController {

	public static final String PATH = "work/report/reportmanager";

	@Autowired
	private ReportService reportService;
	
	/**
	 * 停车场进出次数统计图表
	 */
	@RequestMapping(value = "carInOutTotal")
	public String carInOutTotal(Model model, HttpServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		String starttime = (String) searchParams.get("GTE_startTime");
		Date now = new Date();
		// 默认查看当天
		if (StringUtils.isBlank(starttime)) {
			starttime = DateFormatUtils.format(now, "yyyy-MM-dd");
			searchParams.put("GTE_startTime", starttime);
		}
		String endTime = (String) searchParams.get("LTE_endTime");
		if (StringUtils.isBlank(endTime)) {
			endTime = DateFormatUtils.format(now, "yyyy-MM-dd");
			searchParams.put("LTE_endTime", endTime);
		}

		searchParams.put("who", "PAS.PLNAME");//通道
		searchParams.put("who", "PT.PLNAME");//停车场
		List<MapBean<String, Object>> lm = this.reportService.findIbatisQuery("report.carInOutTotal.total", searchParams);
		model.addAttribute("lm", lm);
		model.addAttribute("now", now);
		model.addAttribute("startTime", starttime);
		model.addAttribute("endTime", endTime);
		return PATH + Constants.SPT + "carInOutTotal";
	}
	/**
	 * 停车场收费统计图表
	 */
	@RequestMapping(value = "carChargeTotal")
	public String carChargeTotal(Model model, HttpServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		String starttime = (String) searchParams.get("GTE_startTime");
		Date now = new Date();
		// 默认查看当天
		if (StringUtils.isBlank(starttime)) {
			starttime = DateFormatUtils.format(now, "yyyy-MM-dd");
			searchParams.put("GTE_startTime", starttime);
		}
		String endTime = (String) searchParams.get("LTE_endTime");
		if (StringUtils.isBlank(endTime)) {
			endTime = DateFormatUtils.format(now, "yyyy-MM-dd");
			searchParams.put("LTE_endTime", endTime);
		}

		List<MapBean<String, Object>> lm = this.reportService.findIbatisQuery("report.carChargeTotal.total", searchParams);
		model.addAttribute("lm", lm);
		model.addAttribute("now", now);
		model.addAttribute("startTime", starttime);
		model.addAttribute("endTime", endTime);
		return PATH + Constants.SPT + "carChargeTotal";
	}
}
