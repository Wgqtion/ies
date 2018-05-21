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
import com.vsc.business.gerd.entity.work.WxOrder;
import com.vsc.business.gerd.service.work.WxOrderService;
import com.vsc.constants.Constants;

/**
 * 小程序订单管理 视图控制
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + WxOrderController.PATH)
public class WxOrderController extends BaseController {

	@Autowired
	private WxOrderService wxOrderService;
	
	public static final String PATH = "work/wxOrder";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request,
			WxOrder entity) throws Exception {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<WxOrder> page = wxOrderService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		model.addAttribute("entity",entity);
		return PATH_LIST;
	}
	
	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", wxOrderService.getObjectById(id));
		return PATH_VIEW;
	}

	@ModelAttribute("preloadModel")
	public WxOrder getModel(@RequestParam(value = "id", required = false) java.lang.Long id) {
		if (id != null) {
			return wxOrderService.getObjectById(id);
		}
		return null;
	}

}
