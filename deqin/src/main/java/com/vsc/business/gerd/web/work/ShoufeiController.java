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
import org.springframework.web.servlet.ModelAndView;

import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.CardType;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.entity.work.Shoufei;
import com.vsc.business.gerd.service.work.CardTypeService;
import com.vsc.business.gerd.service.work.JinXiaoZhengService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.business.gerd.service.work.ShoufeiService;
import com.vsc.business.gerd.service.work.SyncLogService;
import com.vsc.constants.Constants;
import com.vsc.util.CoreUtils;

/**
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping(value = Constants.SPT + ShoufeiController.PATH)
public class ShoufeiController extends BaseController {

	@Autowired
	private ShoufeiService shoufeiService;
	@Autowired
	private JinXiaoZhengService jinXiaoZhengService;
	@Autowired
	private CardTypeService cardTypeService;
	@Autowired
	private ParkingLotService parkingLotService;
	@Autowired
	private SyncLogService syncLogService;
	
	public static final String PATH = "work/shoufei";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();

		Page<Shoufei> page = shoufeiService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);

		return PATH_LIST;
	}

	@RequestMapping(value = "export")
	public ModelAndView exportList(HttpServletRequest request) {
		Map<String, Object> searchParams = this.getSearchRequest();
		List<Shoufei> list = shoufeiService.findAll(searchParams,
				this.getSortOrderBy(), this.getSortOrderDesc());
		return this.reportView(PATH_LIST, list, REPORT_FORMAT_XLS);

	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new Shoufei());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid Shoufei shoufei,
			@RequestParam(value = "cardTypeGroup.id", required = true) Long cardtypeId,
			@RequestParam(value = "parkingLotGroup.id", required = true) Long parkinglotId) {
		CardType cardType = this.cardTypeService.getObjectById(cardtypeId);
		ParkingLot parkingLot= this.parkingLotService.getObjectById(parkinglotId);
		shoufei.setCardType(cardType);
		shoufei.setParkingLot(parkingLot);
		shoufei.setLasttime(CoreUtils.nowtime().getTime());
		shoufeiService.save(shoufei);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", shoufeiService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", shoufeiService.getObjectById(id));
		return PATH_VIEW;
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(
			@Valid @ModelAttribute("preloadModel") Shoufei shoufei,
			@RequestParam(value = "cardTypeGroup.id", required = true) Long cardtypeId,
			@RequestParam(value = "parkingLotGroup.id", required = true) Long parkinglotId) {
		CardType cardType = this.cardTypeService.getObjectById(cardtypeId);
		ParkingLot parkingLot= this.parkingLotService.getObjectById(parkinglotId);
		shoufei.setCardType(cardType);
		shoufei.setParkingLot(parkingLot);
		shoufeiService.save(shoufei);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		shoufeiService.deleteById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		shoufeiService.deleteByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = "sync")
	public ModelAndView sync(Model model, ServletRequest request) {
		this.jinXiaoZhengService.synchronizationShoufei();
		this.shoufeiService.synchronizationShoufei();
		this.syncLogService.addSyncLog("sys", "同步收费规则", "sys");
		return this.ajaxDoneSuccess("同步收费规则数据成功");
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
	public Shoufei getModel(
			@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return shoufeiService.getObjectById(id);
		}
		return null;
	}

}

