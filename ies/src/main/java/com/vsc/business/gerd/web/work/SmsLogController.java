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

import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.SmsLog;
import com.vsc.business.gerd.service.work.SmsLogService;
import com.vsc.constants.Constants;

 
/**
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping(value =  Constants.SPT+SmsLogController.PATH)
public class SmsLogController extends BaseController {
	 
	@Autowired
	private SmsLogService smsLogService;
	public static final String PATH = "work/smslog";
	public static final String PATH_LIST = PATH +Constants.SPT+ "list";
	public static final String PATH_EDIT = PATH + Constants.SPT+"edit";
	public static final String PATH_VIEW = PATH + Constants.SPT+"view";
	public static final String PATH_SEARCH = PATH + Constants.SPT+"search";
	
	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) throws Exception {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();
	 

		Page<SmsLog> page = smsLogService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
	
		return PATH_LIST;
	}
	
	  
	
	@RequestMapping(value =  BaseController.VIEW+"/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") java.lang.Long id, Model model) {
		model.addAttribute("vm", smsLogService.getObjectById(id));
		return PATH_VIEW;
	}
 

	 
	@ModelAttribute("preloadModel")
	public SmsLog getModel(@RequestParam(value = "id", required = false) java.lang.Long id) {
		if (id != null) {
			return smsLogService.getObjectById(id);
		}
		return null;
	}
	

}
