package com.vsc.business.gerd.web.work;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.web.servlet.ModelAndView;

import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.YudingSetting;
import com.vsc.business.gerd.service.work.YudingSettingService;
import com.vsc.constants.Constants;

/**
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + YudingSettingController.PATH)
public class YudingSettingController extends BaseController {

	@Autowired
	private YudingSettingService yudingSettingService;
	public static final String PATH = "work/yudingsetting";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();

		Page<YudingSetting> page = yudingSettingService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH_LIST;
	}

	 

	 

	@RequestMapping(value = BaseController.UPDATE , method = RequestMethod.GET)
	public String updateForm(Model model) {		
		List<YudingSetting> vl=yudingSettingService.getAllList();
		YudingSetting vm=new YudingSetting();
		if(!vl.isEmpty()){
			vm=vl.get(0);
		}
		model.addAttribute("vm", vm);
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", yudingSettingService.getObjectById(id));
		return PATH_VIEW;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") YudingSetting yudingSetting) {
		yudingSettingService.save(yudingSetting);
		return this.ajaxDoneSuccess("保存成功");
	}
 
	@ModelAttribute("preloadModel")
	public YudingSetting getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return yudingSettingService.getObjectById(id);
		}
		return new YudingSetting();
	}

}
