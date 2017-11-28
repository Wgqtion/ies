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
import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.entity.work.ParkingOrder;
import com.vsc.business.gerd.service.work.ParkingGarageService;
import com.vsc.business.gerd.service.work.ParkingOrderService;
import com.vsc.constants.Constants;

/**
 * 车位反寻实际检索 当前通过全视频系统报送至 车位的车牌号
 * 反寻车位其实就是查询出所有当前车位上有车牌的记录
 * @author jerry
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ParkingOrderNotFinishedController.PATH)
public class ParkingOrderNotFinishedController extends BaseController {

	@Autowired
	private ParkingOrderService parkingOrderService;
	 
	@Autowired
	private ParkingGarageService parkingGarageService;

	
	public static final String PATH = "work/parkingordernotfinished";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";
	public static final String PATH_SEARCH = PATH + Constants.SPT + "search";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();
		searchParams.put("NOTNULL_carNo", "");
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

	 

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", parkingGarageService.getObjectById(id));
		return PATH_VIEW;
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
	public ParkingGarage getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return parkingGarageService.getObjectById(id);
		}
		return null;
	}

}
