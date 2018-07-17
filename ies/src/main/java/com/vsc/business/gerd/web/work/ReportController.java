package com.vsc.business.gerd.web.work;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vsc.business.core.web.BaseController;
import com.vsc.constants.Constants;

/**
 * 报表统计
 * 
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ReportController.PATH)
public class ReportController extends BaseController {

	public static final String PATH = "work/report";

}
