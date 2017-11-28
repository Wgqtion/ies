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
import com.vsc.business.gerd.entity.work.VideoDevice;
import com.vsc.business.gerd.service.work.VideoDeviceService;
import com.vsc.constants.Constants;

/**
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping(value = Constants.SPT + VideoDeviceController.PATH)
public class VideoDeviceController extends BaseController {

	@Autowired
	private VideoDeviceService videoDeviceService;
	public static final String PATH = "work/videodevice";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();

		Page<VideoDevice> page = videoDeviceService.findPage(searchParams,
				pageRequest);
		model.addAttribute("page", page);

		return PATH_LIST;
	}

	@RequestMapping(value = "export")
	public ModelAndView exportList(HttpServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		List<VideoDevice> list = videoDeviceService.findAll(searchParams,
				this.getSortOrderBy(), this.getSortOrderDesc());
		return this.reportView(PATH_LIST, list, REPORT_FORMAT_XLS);

	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new VideoDevice());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid VideoDevice videoDevice) {
		videoDeviceService.save(videoDevice);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", videoDeviceService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", videoDeviceService.getObjectById(id));
		return PATH_VIEW;
	}
	
	
	@RequestMapping(value =   "onlineview/{id}", method = RequestMethod.GET)
	public String onlineview(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", videoDeviceService.getObjectById(id));
		return "work/videodevice/online";
	}
	
	@RequestMapping(value =   "ls/{id}", method = RequestMethod.GET)
	public String ls(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", videoDeviceService.getObjectById(id));
		return "work/videodevice/ls";
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(
			@Valid @ModelAttribute("preloadModel") VideoDevice videoDevice) {
		videoDeviceService.save(videoDevice);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		videoDeviceService.deleteById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		videoDeviceService.deleteByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	/**
	 * 高级查询界面
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "search")
	public String search(HttpServletRequest request) {
		return PATH_SEARCH;
	}

	@ModelAttribute("preloadModel")
	public VideoDevice getModel(
			@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return videoDeviceService.getObjectById(id);
		}
		return null;
	}

}
