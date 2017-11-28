package com.vsc.business.core.web.sys;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vsc.business.core.entity.sys.LoginLog;
import com.vsc.business.core.service.sys.LoginLogService;
import com.vsc.business.core.web.BaseController;

@Controller
@RequestMapping(value = "/sys/loginlog")
public class LoginLogController extends BaseController{
	@Autowired
	private LoginLogService loginLogService;
	
	@RequestMapping(value = "")
	public String list(Model model, ServletRequest request) {
		PageRequest pageRequest = this.getPageRequest("createTime","desc");
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<LoginLog> page = loginLogService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		return "sys/loginlog/list";
	}
	
	@RequestMapping(value = BaseController.DELETE+"/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		loginLogService.deleteById(id);		 
		return this.ajaxDoneSuccess("删除成功");
	}
	
	@RequestMapping(value = BaseController.DELETE,method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		loginLogService.deleteByIds(ids);		 
		return this.ajaxDoneSuccess("删除成功");
	}
}
