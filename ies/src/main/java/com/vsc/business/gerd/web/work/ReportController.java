package com.vsc.business.gerd.web.work;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.service.work.ParkingGarageService;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.MapBean;
import com.vsc.modules.entity.ReportView;
import com.vsc.modules.shiro.ShiroUserUtils;

/**
 * 报表统计
 * 
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ReportController.PATH)
public class ReportController extends BaseController {

	public static final String PATH = "work/report";

	@Autowired
	private ParkingGarageService parkingGarageService;

	@RequestMapping(value = "parkingGarageStatus")
	public String parkingGarageStatus(Model model,
			ReportView<ParkingGarage> reportView,
			ParkingGarage parkingGarage,
			HttpServletRequest request) {
		reportView.setEntity(parkingGarage);
		User user=ShiroUserUtils.GetCurrentUser();
		reportView.setCompanyCode(user.getCompanyCode());
		List<MapBean<String, Object>> lm = this.parkingGarageService.findIbatisQuery("report.parkingGarageStatus.total", reportView);
		model.addAttribute("reportView", reportView);
		model.addAttribute("lm", lm);
		return PATH+Constants.SPT+"parkingGarageStatus";
	}
	
}
