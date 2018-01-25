package com.vsc.business.core.web;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;
import org.springside.modules.utils.Encodes;

import com.google.common.collect.Maps;
import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.service.security.ShiroDbRealm.ShiroUser;
import com.vsc.business.core.service.security.UserService;
import com.vsc.modules.spring.mvc.editor.DateTypeEditor;
import com.vsc.util.CoreUtils;

public abstract class BaseController {
	@Autowired
	protected ResourceBundleMessageSource _res;
	//默认分页参数request 中的名称
	public static final String PAGE_NUM_PARAM = "pageNum";
	public static final String NUM_PER_PAGE_PARAM = "numPerPage";
	public static final String ORDER_FIELD_PARAM = "orderField";
	public static final String ORDER_DIRECTION_PARAM = "orderDirection";

	//默认查询请求参数前缀字符串
	public static final String SEARCH_PARAMETERS_STARTING_WITH_PARAM = "search_";

	//默认映射Action地址名
	public static final String NEW = "new"; //进入新增界面
	public static final String CREATE = "create"; //新增表单保存
	public static final String UPDATE = "update"; //进入修改表单界面update/{0}或保存修改
	public static final String DELETE = "delete"; //删除数据delete/{0}
	public static final String VIEW = "view"; //查看数据view/{0}

	public static final String MES_ERROR_TITLE = "errorTitle";
	public static final String MES_ERROR_CONTENT = "errorContent";
	//默认视图路径
	public static final String PATH_ERROR = "error/default"; //默认出错提示页面

	//默认分页大小
	public static final int DEFAUTL_PAGE_SIZE = 45;

	//默认报表文件名后缀
	public static final String REPORT_SUFFIX = ".rpt";
	public static final String REPORT_FORMAT_CSV = "csv";
	public static final String REPORT_FORMAT_HTML = "html";
	public static final String REPORT_FORMAT_PDF = "pdf";
	public static final String REPORT_FORMAT_XLS = "xls";
	public static final String REPORT_FORMAT_RTF = "rtf";
	public static final String REQUEST_REPORT_FORMAT_TYPE = "_report_format_type";
	public static final String[] ALL_REPORT_FORMAT_TYPE = { REPORT_FORMAT_CSV, REPORT_FORMAT_HTML, REPORT_FORMAT_PDF,
			REPORT_FORMAT_XLS, REPORT_FORMAT_RTF };

	@Autowired
	protected UserService userService;

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new DateTypeEditor());

	}

	protected ModelAndView ajaxDone(int statusCode, String message, String data) {
		ModelAndView mav = new ModelAndView("ajaxDone");
		mav.addObject("statusCode", statusCode);
		mav.addObject("message", message);
		if (data != null) {
			mav.addObject("callbackData", data);
		}
		return mav;
	}

	protected ModelAndView ajaxDoneSuccess(String message) {
		return ajaxDone(200, message, null);
	}

	protected ModelAndView ajaxDoneError(String message) {
		return ajaxDone(300, message, null);
	}

	protected ModelAndView ajaxDoneError(String message, String data) {
		return ajaxDone(300, message, data);
	}
	
	protected ModelAndView ajaxDoneSuccess(int stateCode,String message, String data) {
		return ajaxDone(stateCode, message, data);
	}

	protected ModelAndView ajaxDoneSuccess(String message, String data) {
		return ajaxDone(200, message, data);
	}

	protected ModelAndView reportView(String viewName, Object reportData, String format) {
		return reportView(viewName, reportData, format, "");
	}

	protected ModelAndView reportView(String viewName, Object reportData, String format,
			Map<String, Object> customerParameters) {

		return reportView(viewName, reportData, format, "", "", customerParameters);
	}

	protected ModelAndView reportView(String viewName, Object reportData, String format, String documentName) {
		return reportView(viewName, reportData, format, "", documentName, null);
	}

	/**
	 * 
	 * @param viewName
	 * @param reportData
	 * @param format csv html pdf xls
	 * @return
	 */
	protected ModelAndView reportView(String viewName, Object reportData, String format, String contentDisposition,
			String documentName, Map<String, Object> customerParameters) {
		ModelAndView mav = new ModelAndView(viewName + REPORT_SUFFIX);
		if (customerParameters != null && !customerParameters.isEmpty()) {
			mav.addAllObjects(customerParameters);
		}
		if (StringUtils.isBlank(documentName)) {
			documentName = "file-" + CoreUtils.formatDate(Calendar.getInstance().getTime(), 2);
		}

		mav.addObject("reportData", reportData);
		if (ArrayUtils.indexOf(ALL_REPORT_FORMAT_TYPE, format) == ArrayUtils.INDEX_NOT_FOUND) {
			format = REPORT_FORMAT_XLS;
		}
		;
		mav.addObject("format", format);
		if (contentDisposition != null || documentName != null) {
			final StringBuffer tmp = new StringBuffer();
			tmp.append((contentDisposition == null) ? "inline" : contentDisposition);

			if (documentName != null) {
				tmp.append("; filename=");
				tmp.append(Encodes.urlEncode(documentName));
				tmp.append(".");
				tmp.append(format.toLowerCase());
			}
			Properties headers = new Properties();
			headers.put("Content-Disposition", tmp.toString());
			mav.addObject("headers", headers);
		}

		return mav;
	}

	protected String getMessage(String code) {
		return this.getMessage(code, new Object[] {});
	}

	protected String getMessage(String code, Object arg0) {
		return getMessage(code, new Object[] { arg0 });
	}

	protected String getMessage(String code, Object arg0, Object arg1) {
		return getMessage(code, new Object[] { arg0, arg1 });
	}

	protected String getMessage(String code, Object arg0, Object arg1, Object arg2) {
		return getMessage(code, new Object[] { arg0, arg1, arg2 });
	}

	protected String getMessage(String code, Object arg0, Object arg1, Object arg2, Object arg3) {
		return getMessage(code, new Object[] { arg0, arg1, arg2, arg3 });
	}

	protected String getMessage(String code, Object[] args) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale locale = localeResolver.resolveLocale(request);

		return _res.getMessage(code, args, locale);
	}

	/**
	 * 使用默认规则，从 request中获取分页请求对象
	 * @param request
	 * @return
	 */
	private PageRequest getPageRequest(HttpServletRequest request, int pageNumber, int pagzSize, String sortOrderBy,
			String sortOrderDesc, boolean forceReset) {
		pageNumber = pageNumber <= 0 ? 1 : pageNumber;
		pagzSize = pagzSize <= 0 ? DEFAUTL_PAGE_SIZE : pagzSize;
		if (!forceReset) {
			String pageNumberString = WebUtils.findParameterValue(request, PAGE_NUM_PARAM);
			if (StringUtils.isNotBlank(pageNumberString) && StringUtils.isNumeric(pageNumberString)) {
				pageNumber = Integer.parseInt(pageNumberString);
			}
			String pagzSizeString = WebUtils.findParameterValue(request, NUM_PER_PAGE_PARAM);
			if (StringUtils.isNotBlank(pagzSizeString) && StringUtils.isNumeric(pagzSizeString)) {
				pagzSize = Integer.parseInt(pagzSizeString);
			}
			String sortOrderByString = WebUtils.findParameterValue(request, ORDER_FIELD_PARAM);
			String sortOrderDescString = WebUtils.findParameterValue(request, ORDER_DIRECTION_PARAM);
			if (StringUtils.isNotBlank(sortOrderByString) && StringUtils.isNotBlank(sortOrderDescString)) {
				sortOrderBy = sortOrderByString;
				sortOrderDesc = sortOrderDescString;
			}
		}
		return CoreUtils.buildPageRequest(pageNumber, pagzSize, sortOrderBy, sortOrderDesc);
	}

	protected String getSortOrderBy(HttpServletRequest request) {
		return WebUtils.findParameterValue(request, ORDER_FIELD_PARAM);
	}

	protected String getSortOrderBy() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return getSortOrderBy(request);
	}

	protected String getSortOrderDesc() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return getSortOrderDesc(request);

	}

	protected String getSortOrderDesc(HttpServletRequest request) {
		return WebUtils.findParameterValue(request, ORDER_DIRECTION_PARAM);
	}

	protected PageRequest getPageRequest() {
		return getPageRequest(-1, -1, null, null, false);
	}

	/**
	 * 当request中没有设定分页信息时，将用该设定值进行分页
	 * @param pageNumber	当前页码
	 * @param pagzSize			每页数量
	 * @return
	 */
	protected PageRequest getPageRequest(int pageNumber, int pagzSize) {
		return getPageRequest(pageNumber, pagzSize, false);
	}

	/**
	 * @param pageNumber	当前页码
	 * @param pagzSize			每页数量
	 * @param forceReset		是否强制覆盖request中传递的参数
	 * @return
	 */
	protected PageRequest getPageRequest(int pageNumber, int pagzSize, boolean forceReset) {
		return getPageRequest(pageNumber, pagzSize, null, null, forceReset);
	}

	/**
	 *  当request中没有设定排序信息时，将用该设定值进行排序
	 * @param sortOrderBy		排序字段,多个用 , 分隔
	 * @param sortOrderDesc	排序字段类型,多个用 , 分隔，必须与一致
	 * @return
	 */
	protected PageRequest getPageRequest(String sortOrderBy, String sortOrderDesc) {
		return getPageRequest(sortOrderBy, sortOrderDesc, false);
	}

	/**
	 * 
	 * @param sortOrderBy		排序字段,多个用 , 分隔
	 * @param sortOrderDesc	排序字段类型,多个用 , 分隔，必须与一致
	 * @param forceReset		是否强制覆盖request中传递的参数
	 * @return
	 */
	protected PageRequest getPageRequest(String sortOrderBy, String sortOrderDesc, boolean forceReset) {
		return getPageRequest(-1, -1, sortOrderBy, sortOrderDesc, forceReset);
	}

	/**
	 * 
	 * @param pageNumber	当前页码
	 * @param pagzSize			每页数量
	 * @param sortOrderBy		排序字段,多个用 , 分隔
	 * @param sortOrderDesc 排序字段类型,多个用 , 分隔，必须与一致
	 * @param forceReset		是否强制覆盖request中传递的参数
	 * @return
	 */
	protected PageRequest getPageRequest(int pageNumber, int pagzSize, String sortOrderBy, String sortOrderDesc,
			boolean forceReset) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return getPageRequest(request, pageNumber, pagzSize, sortOrderBy, sortOrderDesc, forceReset);
	}

	/**
	 * 根据默认查询参数前缀，获取所有查询条件
	 * @param request
	 * @return
	 */
	protected Map<String, Object> getSearchRequest(HttpServletRequest request) {
		return CoreUtils.getParametersStartingWith(request, SEARCH_PARAMETERS_STARTING_WITH_PARAM);
	}

	protected Map<String, Object> getSearchRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return getSearchRequest(request);
	}

	/**
	 * 取出Shiro中的当前用户,不查询数据库
	 */
	protected ShiroUser getCurrentShiroUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 取出当前用户,查询数据库
	 */
	protected User getCurrentUser() {
		ShiroUser shiroUser = getCurrentShiroUser();
		if (shiroUser != null && shiroUser.id != null) {
			return this.userService.getObjectById(shiroUser.id);
		}
		return null;
	}

}
