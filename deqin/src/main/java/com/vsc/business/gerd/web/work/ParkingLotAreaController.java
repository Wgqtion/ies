package com.vsc.business.gerd.web.work;

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
import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.entity.work.ParkingLotArea;
import com.vsc.business.gerd.service.work.ParkingLotAreaService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.constants.Constants;
import com.vsc.util.CoreUtils;

/**
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ParkingLotAreaController.PATH)
public class ParkingLotAreaController extends BaseController {

	@Autowired
	private ParkingLotAreaService parkingLotAreaService;

	@Autowired
	private ParkingLotService parkingLotService;

	public static final String PATH = "work/parkinglotarea";
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

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();

		Page<ParkingLotArea> page = parkingLotAreaService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH_LIST;
	}

	@RequestMapping(value = "export")
	public ModelAndView exportList(HttpServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		List<ParkingLotArea> list = parkingLotAreaService.findAll(searchParams, this.getSortOrderBy(),
				this.getSortOrderDesc());
		return this.reportView(PATH_LIST, list, REPORT_FORMAT_XLS);

	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new ParkingLotArea());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid ParkingLotArea parkingLotArea,
			@RequestParam(value = "parkinglotGroup.id", required = true) Long parkinglotId,
			@RequestParam(value = "photoAttachId", required = false) Long photoAttachId,
			@RequestParam(value = "parentId", required = false) Long parentId) {
		parkingLotArea.setCreateTime(CoreUtils.nowtime());
		ParkingLot pm = parkingLotService.getObjectById(parkinglotId);
		parkingLotArea.setParkingLot(pm);

		if (parentId == null) {
			parkingLotArea.setParent(null);
		} else {
			parkingLotArea.setParent(this.parkingLotAreaService.getObjectById(parentId));
		}
		parkingLotAreaService.save(parkingLotArea, photoAttachId);

		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", parkingLotAreaService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", parkingLotAreaService.getObjectById(id));
		return PATH_VIEW;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") ParkingLotArea parkingLotArea,
			@RequestParam(value = "parkinglotGroup.id", required = true) Long parkinglotId,
			@RequestParam(value = "photoAttachId", required = false) Long photoAttachId,
			@RequestParam(value = "parentId", required = false) Long parentId) {
		ParkingLot pm = parkingLotService.getObjectById(parkinglotId);
		parkingLotArea.setParkingLot(pm);
		if (parentId == null) {
			parkingLotArea.setParent(null);
		} else {
			parkingLotArea.setParent(this.parkingLotAreaService.getObjectById(parentId));
		}
		parkingLotAreaService.save(parkingLotArea, photoAttachId);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		parkingLotAreaService.deleteById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		parkingLotAreaService.deleteByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = "select")
	public String select(Model model, ServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		List<ParkingLot> vl = parkingLotService.findList(searchParams);
		model.addAttribute("vl", vl);
		return PATH_SELECT;
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

	@ModelAttribute("preloadModel")
	public ParkingLotArea getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return parkingLotAreaService.getObjectById(id);
		}
		return null;
	}

	@RequestMapping(value = "checkSelParkingLotArea")
	@ResponseBody
	public boolean checkSelParkingLotArea(@RequestParam(value = "parentId", required = false) Long newid,
			@RequestParam(value = "oldid", required = false) Long oldid, @RequestParam(value = "pid") Long pid) {

		if (newid != null) {
			if (newid.equals(pid)) {
				return false;
			}
			if (!newid.equals(oldid)) {
				ParkingLotArea catalog = this.parkingLotAreaService.getObjectById(pid);
				if (catalog.getId().equals(newid)) {
					return false;
				}
				return !checkSelParkingLotArea2(catalog.getChildren(), newid);
			}
		}
		return true;
	}

	/**
	   * 判断当前栏目与下级栏目中是否包含指定id的对象
	   * @param Catalog 需要查找的栏目
	   * @param newid  需要查找的 ID
	   * @return 包含返回 true 不存在 返回false
	   */
	private boolean checkSelParkingLotArea2(List<ParkingLotArea> catalogs, Long newid) {
		for (ParkingLotArea c : catalogs) {
			if (newid.equals(c.getId())) {
				return true;
			}
			if (!c.getChildren().isEmpty()) {
				if (checkSelParkingLotArea2(c.getChildren(), newid)) {
					return true;
				}
			}
		}
		return false;
	}

}