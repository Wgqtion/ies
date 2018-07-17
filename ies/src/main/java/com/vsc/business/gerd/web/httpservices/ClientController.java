package com.vsc.business.gerd.web.httpservices;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.vsc.business.gerd.entity.validate.work.InParkingOrderValidate;
import com.vsc.business.gerd.entity.validate.work.OutParkingOrderValidate;
import com.vsc.business.gerd.entity.validate.work.PayParkingOrderValidate;
import com.vsc.business.gerd.entity.work.ParkingCameraLog;
import com.vsc.business.gerd.entity.work.ParkingInOut;
import com.vsc.business.gerd.service.work.ParkingCameraLogService;
import com.vsc.business.gerd.service.work.ParkingInOutService;
import com.vsc.constants.Constants;

/**
 * 客户端调用的接口控制类
 * 
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ClientController.PATH)
public class ClientController extends HttpServiceBaseController {
	// 控制层 URL地址映射
	public static final String PATH = PATH_BASE + Constants.SPT + "client";
	// 视图地址映射
	public static final String V_PATH = V_PATH_BASE;
	public static final String V_PATH_INDEX = PATH_BASE + Constants.SPT + "client";

	@Autowired
	private ParkingInOutService parkingOrderService;

	@Autowired
	private ParkingCameraLogService parkingCameraLogService;

	@RequestMapping(value = "")
	public String index(Model model) {
		return V_PATH_INDEX;
	}

	/**
	 * 车辆进入接口
	 * 
	 * @throws Exception
	 * *
	 * 
	 */
	@RequestMapping(value = "order/parkingIn")
	public ModelAndView parkingIn(@Valid InParkingOrderValidate validate, BindingResult result,
			ParkingInOut parkingOrder, HttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			StringBuffer sb = new StringBuffer();
			for (FieldError fieldError : result.getFieldErrors()) {
				if (sb.length() > 0) {
					sb.append(",");
				}
				sb.append(fieldError.getDefaultMessage());
			}
			return this.ajaxDoneError(sb.toString());
		}
		parkingOrder.setPlateNo(validate.getInPlateNo());
		this.parkingOrderService.inParkingOrder(parkingOrder);
		String jsonstr = "\"" + parkingOrder.getPayNumber() + "\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 车辆出去接口 *
	 * 
	 * @return
	 */
	@RequestMapping(value = "order/parkingOut")
	public ModelAndView parkingOut(@Valid OutParkingOrderValidate validate, BindingResult result,
			ParkingInOut parkingOrder, HttpServletRequest request) {
		if (result.hasErrors()) {
			StringBuffer sb = new StringBuffer();
			for (FieldError fieldError : result.getFieldErrors()) {
				if (sb.length() > 0) {
					sb.append(",");
				}
				sb.append(fieldError.getDefaultMessage());
			}
			return this.ajaxDoneError(sb.toString());
		}
		boolean flag = false;
		try {
			parkingOrder.setPlateNo(validate.getOutPlateNo());
			this.parkingOrderService.outParkingOrder(parkingOrder);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		String jsonstr = "\"" + flag + "\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 车辆支付接口 *
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "order/parkingPay")
	public ModelAndView parkingPay(@Valid PayParkingOrderValidate validate, BindingResult result,
			ParkingInOut parkingOrder, HttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			StringBuffer sb = new StringBuffer();
			for (FieldError fieldError : result.getFieldErrors()) {
				if (sb.length() > 0) {
					sb.append(",");
				}
				sb.append(fieldError.getDefaultMessage());
			}
			return this.ajaxDoneError(sb.toString());
		}
		parkingOrder.setPlateNo(validate.getPayPlateNo());
		this.parkingOrderService.payParkingOrder(parkingOrder);

		String jsonstr = "\"" + parkingOrder.getPayNumber() + "\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}

	/**
	 * 全视频上报接口
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "parking/parkingCameraLog", method = RequestMethod.POST)
	public ModelAndView service2(@Valid ParkingCameraLog parkingCameraLog, MultipartFile picture) throws Exception {
		parkingCameraLogService.save(parkingCameraLog, picture);
		String jsonstr = "\"true\"";
		return this.ajaxDoneSuccess(this.getMessage("httpservices.service_success"), jsonstr);
	}
}