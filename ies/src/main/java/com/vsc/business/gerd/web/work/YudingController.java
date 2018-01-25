package com.vsc.business.gerd.web.work;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
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

import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.ParkingLotArea;
import com.vsc.business.gerd.entity.work.Yuding;
import com.vsc.business.gerd.service.work.ParkingLotAreaService;
import com.vsc.business.gerd.service.work.YudingService;
import com.vsc.constants.Constants;

/**
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + YudingController.PATH)
public class YudingController extends BaseController {

	@Autowired
	private YudingService yudingService;
	@Autowired
	private ParkingLotAreaService parkingLotAreaService;

	public static final String PATH = "work/yuding";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();

		Page<Yuding> page = yudingService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH_LIST;
	}

	@RequestMapping(value = "export")
	public ModelAndView exportList(HttpServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		List<Yuding> list = yudingService.findAll(searchParams, this.getSortOrderBy(), this.getSortOrderDesc());
		return this.reportView(PATH_LIST, list, REPORT_FORMAT_XLS);

	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new Yuding());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid Yuding yuding,
			@RequestParam(value = "yudingUserGroup.id", required = false) Long memberId,
			@RequestParam(value = "parkingLotAreaGroup.id", required = false) Long parkingLotAreaId) {
		Date now = new Date();
		yuding.setLasttime(now.getTime());
		yuding.setCreateTime(now);
		if ((yuding.getYuyueTime() != null) && (yuding.getLockedMinutes() != null)) {
			Date lockedStarttime = DateUtils.addMinutes(yuding.getYuyueTime(), -1 * yuding.getLockedMinutes());
			yuding.setLockedStartTime(lockedStarttime);
		}
		if (memberId != null) {
			yuding.setUser(this.userService.getObjectById(memberId));
		}
		yuding.setParkingLotArea(this.parkingLotAreaService.getObjectById(parkingLotAreaId));

		yudingService.save(yuding);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", yudingService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", yudingService.getObjectById(id));
		return PATH_VIEW;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") Yuding yuding,
			@RequestParam(value = "yudingUserGroup.id", required = false) Long memberId,
			@RequestParam(value = "parkingLotAreaGroup.id", required = false) Long parkingLotAreaId) {
		Date now = new Date();
		yuding.setLasttime(now.getTime());

		if ((yuding.getYuyueTime() != null) && (yuding.getLockedMinutes() != null)) {
			Date lockedStarttime = DateUtils.addMinutes(yuding.getYuyueTime(), -1 * yuding.getLockedMinutes());
			yuding.setLockedStartTime(lockedStarttime);
		} else {
			yuding.setLockedStartTime(null);
		}
		if (memberId != null) {
			yuding.setUser(this.userService.getObjectById(memberId));
		} else {
			yuding.setUser(null);
		}

		ParkingLotArea parkingLotArea = this.parkingLotAreaService.getObjectById(parkingLotAreaId);
		yuding.setParkingLotArea(parkingLotArea);

		yudingService.save(yuding);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		yudingService.deleteById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		yudingService.deleteByIds(ids);
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

	@ModelAttribute("preloadModel")
	public Yuding getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return yudingService.getObjectById(id);
		}
		return null;
	}

	@RequestMapping(value = "checkParkingLotAreaIsMinNode")
	@ResponseBody
	public boolean checkParkingLotAreaIsMinNode(@RequestParam(value = "parkingLotAreaGroup.id") Long pid) {

		ParkingLotArea parkingLotArea = this.parkingLotAreaService.getObjectById(pid);
		return parkingLotArea.getChildren().isEmpty();

	}

}
