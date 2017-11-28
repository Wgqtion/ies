package com.vsc.business.gerd.web.work;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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

import com.google.common.collect.Maps;
import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.entity.work.ParkingLotArea;
import com.vsc.business.gerd.service.work.ParkingGarageService;
import com.vsc.business.gerd.service.work.ParkingLotAreaService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.constants.Constants;
import com.vsc.modules.entity.MapBean;
import com.vsc.util.CoreUtils;

/**
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ParkingGarageController.PATH)
public class ParkingGarageController extends BaseController {

	@Autowired
	private ParkingGarageService parkingGarageService;

	@Autowired
	private ParkingLotAreaService parkinglotareaService;
	@Autowired
	private ParkingLotService parkingLotService;

	public static final String PATH = "work/parkinggarage";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";
	public static final String PATH_SELECT = PATH + Constants.SPT + "select";
	public static final String PATH_TREE = PATH + Constants.SPT + "tree";

	@RequestMapping(value = "")
	public String tree(Model model, HttpServletRequest request) {
		// PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = Maps.newHashMap();//树永远固定不接受查询条件
		List<ParkingLot> vl = this.parkingLotService.findList(searchParams);
		model.addAttribute("vl", vl);
		this.list(model, request);
		return PATH_TREE;
	}

	@RequestMapping(value = "list")
	public String list(Model model, HttpServletRequest request) {

		PageRequest pageRequest = this.getPageRequest("id","desc");
		Map<String, Object> searchParams = this.getSearchRequest();

		String parkingLotAreaFullNameId = request.getParameter("parkingLotAreaFullName");
		if (StringUtils.isNotBlank(parkingLotAreaFullNameId)) {
			ParkingLotArea qfullname = this.parkinglotareaService.getObjectById(Long.valueOf(parkingLotAreaFullNameId));

			searchParams.put("RLIKE_parkingLotArea.fullIndexName", qfullname.getFullIndexName());
		}

		Page<ParkingGarage> page = parkingGarageService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH_LIST;
	}

	@RequestMapping(value = "export")
	public ModelAndView exportList(HttpServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		List<ParkingGarage> list = parkingGarageService.findAll(searchParams, this.getSortOrderBy(),
				this.getSortOrderDesc());
		return this.reportView(PATH_LIST, list, REPORT_FORMAT_XLS);

	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new ParkingGarage());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid ParkingGarage parkingGarage,
			@RequestParam(value = "parkinglotareaGroup.id", required = true) Long parkinglotareaId) {
		ParkingLotArea pm = parkinglotareaService.getObjectById(parkinglotareaId);
		parkingGarage.setCreateTime(CoreUtils.nowtime());
		parkingGarage.setParkingLotArea(pm);
		parkingGarageService.save(parkingGarage);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", parkingGarageService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", parkingGarageService.getObjectById(id));
		return PATH_VIEW;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") ParkingGarage parkingGarage,
			@RequestParam(value = "parkinglotareaGroup.id", required = true) Long parkinglotareaId) {
		ParkingLotArea pm = parkinglotareaService.getObjectById(parkinglotareaId);
		parkingGarage.setParkingLotArea(pm);
		parkingGarageService.save(parkingGarage);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		parkingGarageService.deleteById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		parkingGarageService.deleteByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	/**
	 *  高级查询界面
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "search")
	public String search(HttpServletRequest request) {
		return PATH_SEARCH;
	}

	@RequestMapping(value = "select")
	public String select(Model model, ServletRequest request) {
		PageRequest pageRequest = this.getPageRequest("name", "asc");
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<ParkingGarage> page = parkingGarageService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		return PATH_SELECT;
	}

	 
	@ModelAttribute("preloadModel")
	public ParkingGarage getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return parkingGarageService.getObjectById(id);
		}
		return null;
	}

}
