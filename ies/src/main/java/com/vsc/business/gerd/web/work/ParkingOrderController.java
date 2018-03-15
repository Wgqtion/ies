package com.vsc.business.gerd.web.work;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.ParkingOrder;
import com.vsc.business.gerd.service.work.ParkingOrderService;
import com.vsc.constants.Constants;
import com.vsc.modules.shiro.ShiroUserUtils;

/**
 * 
 * @author jerry
 * 
 */
@Controller
@RequestMapping(value = Constants.SPT + ParkingOrderController.PATH)
public class ParkingOrderController extends BaseController {

	@Autowired
	private ParkingOrderService parkingOrderService;

	public static final String PATH = "work/parkingorder";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";

	@RequestMapping(value = "")
	public String list(Model model,boolean isHome, HttpServletRequest request) {

		PageRequest pageRequest = null;
		if(isHome){
			pageRequest=this.getPageRequest("id","DESC");
		}else{
			pageRequest=this.getPageRequest();	
		}
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<ParkingOrder> page = parkingOrderService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH_LIST;
	}
	
	@RequestMapping(value = "test")
	public String test(Model model,boolean isHome, HttpServletRequest request) {

		PageRequest pageRequest = null;
		if(isHome){
			pageRequest=this.getPageRequest("id","DESC");
		}else{
			pageRequest=this.getPageRequest();	
		}
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<ParkingOrder> page = parkingOrderService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH+Constants.SPT+"test";
	}
	
	@ModelAttribute("preloadModel")
	public ParkingOrder getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return parkingOrderService.getObjectById(id);
		}
		return null;
	}

}
