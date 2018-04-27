package com.vsc.business.gerd.web.work;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.ParkingLockOperationEvent;
import com.vsc.business.gerd.service.work.ParkingLockOperationEventService;
import com.vsc.constants.Constants;

 
/**
 * 
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value =  Constants.SPT+ParkingLockOperationEventController.PATH)
public class ParkingLockOperationEventController extends BaseController {
	 
	@Autowired
	private ParkingLockOperationEventService parkingLockOperationEventService;
	public static final String PATH = "work/parkinglockoperationevent";
	public static final String PATH_LIST = PATH +Constants.SPT+ "list";
	public static final String PATH_VIEW = PATH + Constants.SPT+"view";
	
	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request,boolean isHome) throws Exception {

		PageRequest pageRequest = this.getPageRequest();
		if(isHome){
			pageRequest=this.getPageRequest("createTime","DESC");
		}else{
			pageRequest=this.getPageRequest();	
		}
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<ParkingLockOperationEvent> page = parkingLockOperationEventService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
	
		return PATH_LIST;
	}
	 
	@ModelAttribute("preloadModel")
	public ParkingLockOperationEvent getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return parkingLockOperationEventService.getObjectById(id);
		}
		return null;
	}
	

}
