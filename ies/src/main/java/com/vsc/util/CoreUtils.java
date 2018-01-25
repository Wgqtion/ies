package com.vsc.util;

import java.io.File;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.vsc.constants.Constants;

public class CoreUtils {

	/**
	 * 创建一个新的分页请求
	 * @param pageNumber  当前页码
	 * @param pagzSize         每页数量
	 * @param sortOrderBy    排序字段数组
	 * @param sortOrderDesc  排序字段类型数组，必须与sortOrderBy长度一致
	 * @return 分页请求对象
	 */
	public static PageRequest buildPageRequest(int pageNumber, int pagzSize, String[] sortOrderBy,
			String[] sortOrderDesc) {
		return buildPageRequest(pageNumber, pagzSize, buildSort(sortOrderBy, sortOrderDesc));
	}

	public static PageRequest buildPageRequest(int pageNumber, int pagzSize, Sort sort) {
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	public static PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortOrderBy, String sortOrderDesc) {

		return buildPageRequest(pageNumber, pagzSize, buildSort(sortOrderBy, sortOrderDesc));
	}

	public static Sort buildSort(String sortOrderBy, String sortOrderDesc) {
		String[] orderByArray = null;
		String[] orderArray = null;
		if (StringUtils.isNotBlank(sortOrderBy) && StringUtils.isNotBlank(sortOrderDesc)) {
			orderByArray = StringUtils.split(sortOrderBy, ',');
			orderArray = StringUtils.split(sortOrderDesc, ',');
		}
		return buildSort(orderByArray, orderArray);
	}

	public static Sort buildSort(String[] sortOrderBy, String[] sortOrderDesc) {
		Sort sort = null;

		if (ArrayUtils.isNotEmpty(sortOrderBy) && ArrayUtils.isNotEmpty(sortOrderDesc)) {
			Assert.isTrue(sortOrderBy.length == sortOrderDesc.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");
			List<Order> orders = Lists.newArrayList();
			for (int i = 0; i < sortOrderBy.length; i++) {
				orders.add(new Order(Direction.fromString(sortOrderDesc[i]), sortOrderBy[i]));
			}
			sort = new Sort(orders);
		}
		return sort;
	}

	/**
	 * 判断iterable是否为空
	 * @param iterator
	 * @return
	 */
	public static boolean isEmpty(Iterable iterable) {
		return (iterable == null || !iterable.iterator().hasNext());
	}

	/**
	 * 判断一个请求是否为AJAX请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		return StringUtils.equals("XMLHttpRequest", requestType);
	}
	
	/**
	 * 判断是否是手机客户端的请求，以便给出不同返回
	 * @param request
	 * @return
	 */
	public static boolean isMobileClientRequest(HttpServletRequest request) {
		String headStr = request.getHeader("C-Requested-From");
		return StringUtils.equals("MobileClient", headStr);
	}

	/**
	 * 获取当前系统时间
	 * @return
	 */
	public static Date nowtime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
	 * 
	 * 返回的结果的Parameter名已去除前缀.
	 */
	public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
		Validate.notNull(request, "Request must not be null");
		Enumeration paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					if (StringUtils.isNotBlank(values[0])) {
						params.put(unprefixed, values[0]);
					}
				}
			}
		}
		return params;
	}

	/**
	 * 将一个相对路径转化为硬盘上的绝对路径
	 * @param request
	 * @param path 相对路径  upload/xxx/xxx
	 * @return 绝对路径  D:/server/root/upload/xxx/xxx
	 */
	public static String getStoragePath(HttpServletRequest request, String path) {
		return request.getSession().getServletContext().getRealPath(path);
	}

	/**
	 * 获取当前请求的IP地址。解决通过Apache等转发后无法获取客户端IP的问题
	 * @param request
	 * @return
	 */
	public static String getIpAddr(javax.servlet.http.HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 判断某种类型字符串是否属于图片类文件
	 * @param fileType
	 * @return
	 */
	public static boolean IsImage(String fileType) {
		return ArrayUtils.contains(Constants.IMG_TYPE, fileType);
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数),组合成List.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> fetchElementPropertyToList(final Collection collection, final String propertyName) {
		List list = new ArrayList();
		try {
			if (!CollectionUtils.isEmpty(collection)) {
				for (Object obj : collection) {
					list.add(PropertyUtils.getProperty(obj, propertyName));
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return (List<T>) list;
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数),组合成由分割符分隔的字符串.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 * @param separator 分隔符.
	 */
	@SuppressWarnings("unchecked")
	public static String fetchElementPropertyToString(final Collection collection, final String propertyName,
			final String separator) {
		List list = fetchElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, StringUtils.isEmpty(separator) ? "," : separator);
	}

	/**
	 * 创建一个List,并将数组中的值 作为list中对象的属性填充进去
	 * 常常用于基于 id数组 创建一组对象
	 * @param arrays 数组值  比如一个 Long[]
	 * @param propertyName list对象中的属性名称 常常是  id
	 * @param clazz 集合中对象的 Class类型
	 * @return
	 */
	public static <T> List<T> fetchElementPropertyToObjectList(final Object[] arrays, final String propertyName,
			Class<T> clazz) {
		if (ArrayUtils.isEmpty(arrays)) {
			return null;
		}
		List<T> list = Lists.newArrayList();
		try {
			for (int i = 0; i < arrays.length; i++) {
				if (arrays[i] != null) {
					T v = clazz.newInstance();
					PropertyUtils.setProperty(v, propertyName, arrays[i]);
					list.add(v);
				}
			}
		} catch (Exception e) {
			convertToUncheckedException(e);
		}
		return list;
	}

	/**
	 * 将反射时的checked exception转换为unchecked exception.
	 */
	public static IllegalArgumentException convertToUncheckedException(Exception e) {
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException)
			return new IllegalArgumentException("Refelction Exception.", e);
		else
			return new IllegalArgumentException(e);
	}

	/**
	 * 设置一天的最大时刻 23:59:59 999
	 * @param date 需要设置的日期
	 * @return 追加了 最大时刻的日期 23:59:59 999
	 */
	public static Date biggestHourDate(Date date) {
		Date big = DateUtils.setHours(date, 23);
		big = DateUtils.setMinutes(big, 59);
		big = DateUtils.setSeconds(big, 59);
		big = DateUtils.setMilliseconds(big, 999);
		return big;
	}
	
	/**
	 * 设置一天的最大时刻 23:59:59 999
	 * @param date 需要设置的日期
	 * @return 追加了 最大时刻的日期 23:59:59 999
	 */
	public static Date minHourDate(Date date) {
		Date min = DateUtils.setHours(date, 0);
		min = DateUtils.setMinutes(min, 0);
		min = DateUtils.setSeconds(min, 0);
		min = DateUtils.setMilliseconds(min, 0);
		return min;
	}

	/**
	 * 格式化日期
	 * @param date
	 * @param style
	 * @return
	 */
	public static String formatDate(Date date, int style) {
		if (date == null)
			return "";
		switch (style) {
		case 6:
			return formatz.format(date);
		case 5:
			return formatd.format(date);
		case 4:
			return formats.format(date);
		case 3:
			return formatm.format(date);
		case 2:
			return format.format(date);
		default:
			return formatw.format(date);
		}
	}

	public static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat formatd = new SimpleDateFormat("yyyyMMdd");
	public static final DateFormat formatw = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat formatm = new SimpleDateFormat("MM-dd HH:mm");
	public static final DateFormat formats = new SimpleDateFormat("MM-dd");
	public static final DateFormat formatz = new SimpleDateFormat("yyyy年MM月dd日");
	public static FilenameFilter DIR_FILE_FILTER = new FilenameFilter() {
		public boolean accept(File dir, String name) {
			if (dir.isDirectory())
				return true;
			else
				return false;
		}
	};

	/**
	 * 将" " "&" "\r" "\n" 转换为 "&amp" "&nbsp;" "" "<br/>"
	 * @param str
	 * @return 
	 */
	public static String encode2HtmlStr(String str) {
		str = str.replaceAll("&", "&amp");
		str = str.replaceAll(" ", "&nbsp;");
		str = str.replaceAll("\r", "");
		str = str.replaceAll("\n", "<br/>");
		return str;
	}

	/**
	 * 是否开放管理员数据查看权限
	 * @return
	 */
	public static boolean isOpenDataView() {
		Subject subject = SecurityUtils.getSubject();
		return subject.hasRole("超级管理员") || subject.hasRole("客户");
	}

	public static String createSerialNumber() {

		return DateFormatUtils.format(new Date(), "yyyyMMddHHmm")
				+ String.format("%04d", Math.round(Math.random() * 9999));

	}
	
	/**
	 * 获取某一个字符串指定后N位
	 * @param index
	 * @return
	 */
	public static String getIndexStr(String str, int index){
		return str.substring(str.length() - index,str.length());
	}
	
	/**
	 * 生成默认密码
	 * 1、str为空：123456
	 * 2、str位数小于6位：123456
	 * 3、str最后一位为字母,则用小写字母表示
	 * 4、str后六位
	 * @param str
	 * @return
	 */
	public static String creteDefaultPwd(String str){
		String defaltStr = "123TjcT";
		if (null != str) {
			if (str.length() < 6) {
				return defaltStr;
			}else {
				return getIndexStr(str, 6).toLowerCase();
			}
		}else {
			return defaltStr;
		}
	};
	
	public static boolean getBoolean(String str){
		
		//离职(07)、开除(08)、解聘(081)为停用状态
		boolean defaultBool = false;
		if (null != str) {
			if("081".equals(str) || "08".equals(str) || "07".equals(str)){
				return defaultBool;
			}else {
				defaultBool = true;
				return defaultBool;
			}
		}else {
			return defaultBool;
		}
	} 
}
