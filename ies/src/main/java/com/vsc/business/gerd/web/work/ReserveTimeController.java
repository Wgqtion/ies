package com.vsc.business.gerd.web.work;

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
import com.vsc.business.gerd.entity.work.ReserveTime;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.business.gerd.service.work.ReserveTimeService;
import com.vsc.constants.Constants;

/**
 * 预约时间视图控制
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ReserveTimeController.PATH)
public class ReserveTimeController extends BaseController {

	@Autowired
	private ReserveTimeService reserveTimeService;
	
	@Autowired
	private ParkingLotService parkingLotService;
	
	public static final String PATH = "work/reserveTime";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SELECT = PATH + Constants.SPT + "select";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) throws Exception {

		PageRequest pageRequest = this.getPageRequest("parkingLotCode,week,startTime","ASC,ASC,ASC");
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<ReserveTime> page = reserveTimeService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		model.addAttribute("parkingLotTree",this.parkingLotService.findTree());
		return PATH_LIST;
	}
	
	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) throws Exception {
		ReserveTime entity=new ReserveTime();
		model.addAttribute("vm",entity);
		model.addAttribute("action", BaseController.CREATE);
		model.addAttribute("parkingLotTree",this.parkingLotService.findTree());
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid ReserveTime entity) throws Exception {
		reserveTimeService.save(entity);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") java.lang.Long id, Model model) throws Exception {
		ReserveTime entity= reserveTimeService.getObjectById(id);
		model.addAttribute("vm",entity);
		model.addAttribute("action", BaseController.UPDATE);
		model.addAttribute("parkingLot",entity.getParkingLot());
		model.addAttribute("parkingLotTree",this.parkingLotService.findTree());
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") ReserveTime entity) throws Exception {
		reserveTimeService.save(entity);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") java.lang.Long id) throws Exception {
		reserveTimeService.deleteUpdateById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam java.lang.Long[] ids) throws Exception {
		reserveTimeService.deleteUpdateByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	@ModelAttribute("preloadModel")
	public ReserveTime getModel(@RequestParam(value = "id", required = false) java.lang.Long id) {
		if (id != null) {
			return reserveTimeService.getObjectById(id);
		}
		return null;
	}

}
