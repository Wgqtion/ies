package com.vsc.business.core.web.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.vsc.business.core.entity.sys.Dict;
import com.vsc.business.core.service.sys.DictService;
import com.vsc.business.core.web.BaseController;

/**
 * 数据字典Controller 
 * @author 陈婷
 */
@Controller
@RequestMapping(value = "/sys/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;
	public static final String PATH = "sys/dict/";
	public static final String PATH_LIST = PATH + "list";
	public static final String PATH_EDIT = PATH + "edit";
	public static final String PATH_VIEW = PATH + "view";
	public static final String PATH_SEARCH = PATH + "search";
	public static final String PATH_TYPELIST = PATH + "typelist";

	@RequestMapping(value = "")
	public String list(Model model) {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<Dict> page = dictService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		return PATH_LIST;
	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new Dict());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid Dict dict) {
		dictService.save(dict);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", dictService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") Dict dict) {
		dictService.save(dict);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		dictService.deleteById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		dictService.deleteByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = "/typelist")
	public String typelist(Model model) {
		List<String> dictlist = dictService.findAllType();
		model.addAttribute("typelist", dictlist);
		return PATH_TYPELIST;
	}

	@RequestMapping(value = "/typelisttest")
	@ResponseBody
	public Object typelisttest(Model model) {
		List<String> dictlist = dictService.findAllType();
		return dictlist;
	}

	@RequestMapping(value = "typealltest")
	@ResponseBody
	public Object typealltest(Model model, HttpServletRequest request) {
		Map<String, Object> searchParams = Maps.newHashMap();
		//searchParams.put("EQ_type", "工作年限");
		List<Dict> dictlist = dictService.findAll(searchParams, "", "");
		System.out.println("\n============" + new DictController() + "\n");
		return dictlist;
	}

	/**
	 *  高级查询界面
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "search")
	public String search(ServletRequest request) {
		return PATH_SEARCH;
	}

	@ModelAttribute("preloadModel")
	public Dict getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return dictService.getObjectById(id);
		}
		return null;
	}

}
