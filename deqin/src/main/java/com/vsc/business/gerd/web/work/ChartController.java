package com.vsc.business.gerd.web.work;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.MapBean;
import com.vsc.modules.entity.ReportView;

/**
 * 报表统计
 * 
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ChartController.PATH)
public class ChartController extends BaseController {

	public static final String PATH = "work/chart";

	@Autowired
	private ParkingLotService parkingLotService;

	/**
	 * 停车场进出次数统计图表 页面
	 */
	@RequestMapping(value = "parkingInOutTotalView")
	public String parkingInOutTotalView(Model model, ReportView reportView, HttpServletRequest request) {
		Date now = new Date();
		reportView.setStartDate(DateFormatUtils.format(now, "yyyy-MM-dd"));
		reportView.setEndDate(reportView.getStartDate());

		Map<String, Object> filterParams = new HashMap<String, Object>();
		filterParams.put("EQ_isEnabled", 1);
		List<ParkingLot> lotAreaList = parkingLotService.findList(filterParams);

		model.addAttribute("reportView", reportView);
		model.addAttribute("lotAreaList", lotAreaList);
		return PATH + Constants.SPT + "parkingInOutTotalView";
	}

	/**
	 * 停车场进出次数统计图表 数据
	 */
	@RequestMapping(value = "parkingInOutTotalData")
	@ResponseBody
	public Map<String, Object> parkingInOutTotalData(ReportView reportView, HttpServletRequest request) {
		reportView.setSelectType("PL");
		if (StringUtils.isNotBlank(reportView.getSelectId())) {
			reportView.setSelectType("PAS");
		}
		List<MapBean<String, Object>> lm = this.parkingLotService.findIbatisQuery("report.carInOutTotal.total", reportView);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		map.put("data", lm);
		return map;
	}

	/**
	 * 停车场收费统计图表 页面
	 */
	@RequestMapping(value = "parkingChargeTotalView")
	public String parkingChargeTotalView(Model model, ReportView reportView, HttpServletRequest request) {
		Date now = new Date();
		reportView.setStartDate(DateFormatUtils.format(now, "yyyy-MM-dd"));
		reportView.setEndDate(reportView.getStartDate());

		Map<String, Object> filterParams = new HashMap<String, Object>();
		filterParams.put("EQ_isEnabled", 1);
		List<ParkingLot> lotAreaList = parkingLotService.findList(filterParams);

		model.addAttribute("reportView", reportView);
		model.addAttribute("lotAreaList", lotAreaList);
		return PATH + Constants.SPT + "parkingChargeTotalView";
	}

	/**
	 * 停车场收费统计图表 数据
	 */
	@RequestMapping(value = "parkingChargeTotalData")
	@ResponseBody
	public Map<String, Object> carChargeTotalData(ReportView reportView, HttpServletRequest request) {
		reportView.setSelectType("PL");
		if (StringUtils.isNotBlank(reportView.getSelectId())) {
			reportView.setSelectType("PAS");
		}
		List<MapBean<String, Object>> lm = this.parkingLotService.findIbatisQuery("report.carChargeTotal.total",
				reportView);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		map.put("data", lm);
		return map;
	}
	
	/**
	 * 停车场余位统计图表 页面
	 */
	@RequestMapping(value = "parkingSurplusTotalView")
	public String parkingSurplusTotalView(Model model, ReportView reportView, HttpServletRequest request) {
		Date now = new Date();
		reportView.setStartDate(DateFormatUtils.format(now, "yyyy-MM-dd"));
		reportView.setEndDate(reportView.getStartDate());

		Map<String, Object> filterParams = new HashMap<String, Object>();
		filterParams.put("EQ_isEnabled", 1);
		List<ParkingLot> lotAreaList = parkingLotService.findList(filterParams);

		model.addAttribute("reportView", reportView);
		model.addAttribute("lotAreaList", lotAreaList);
		return PATH + Constants.SPT + "parkingSurplusTotalView";
	}
}
