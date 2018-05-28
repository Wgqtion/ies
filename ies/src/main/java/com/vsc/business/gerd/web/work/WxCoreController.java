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
import com.vsc.business.gerd.entity.work.WxCore;
import com.vsc.business.gerd.service.work.WxCoreService;
import com.vsc.constants.Constants;

/**
 * 预约停车记录 视图控制
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + WxCoreController.PATH)
public class WxCoreController extends BaseController {

	@Autowired
	private WxCoreService wxCoreService;
	
	public static final String PATH = "work/wxCore";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request,
			WxCore entity) throws Exception {

		PageRequest pageRequest = this.getPageRequest("startTime","DESC");
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<WxCore> page = wxCoreService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		model.addAttribute("entity",entity);
		return PATH_LIST;
	}
	
	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", wxCoreService.getObjectById(id));
		return PATH_VIEW;
	}

	@ModelAttribute("preloadModel")
	public WxCore getModel(@RequestParam(value = "id", required = false) java.lang.Long id) {
		if (id != null) {
			return wxCoreService.getObjectById(id);
		}
		return null;
	}

}
