package com.vsc.business.gerd.web.work;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.ParkingInOut;
import com.vsc.business.gerd.service.work.ParkingInOutService;
import com.vsc.constants.Constants;

/**
 * 进出口记录
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ParkingInOutController.PATH)
public class ParkingInOutController extends BaseController {

	@Autowired
	private ParkingInOutService parkingInOutService;

	public static final String PATH = "work/parkingInOut";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";

	@RequestMapping(value = "")
	public String list(Model model,boolean isHome, HttpServletRequest request) throws Exception {

		PageRequest pageRequest = null;
		if(isHome){
			pageRequest=this.getPageRequest("id","DESC");
		}else{
			pageRequest=this.getPageRequest();	
		}
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<ParkingInOut> page = parkingInOutService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH_LIST;
	}
}
