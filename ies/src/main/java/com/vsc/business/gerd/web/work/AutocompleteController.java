package com.vsc.business.gerd.web.work;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;
import com.vsc.business.core.web.BaseController;
import com.vsc.constants.Constants;

/**
 * 
 * @author jerry
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + AutocompleteController.PATH)
public class AutocompleteController extends BaseController {

	public static final String PATH = "work/ac";
	public static final String PATH_AREA = PATH + Constants.SPT + "area";
	public static final String PATH_HOSPITAL = PATH + Constants.SPT + "hospital";

	@RequestMapping(value = "area")
	public String area(Model model, HttpServletRequest request) {

		Map<String, Object> searchParams = Maps.newHashMap();
		return PATH_AREA;
	}

}
