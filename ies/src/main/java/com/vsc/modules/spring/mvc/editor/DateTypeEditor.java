package com.vsc.modules.spring.mvc.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期编辑器
 * 
 * 根据日期字符串长度判断是长日期还是短日期。只支持yyyy-MM-dd，yyyy-MM-dd HH:mm:ss两种格式。
 * 扩展支持yyyy,yyyy-MM日期格式
 * @author 付翀
 * 
 */
public class DateTypeEditor extends PropertyEditorSupport {
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final String DATETIMEMM_PATTERN = "yyyy-MM-dd HH:mm";
	private static final String MONTH_PATTERN = "yyyy-MM";
	private static final String YEAR_PATTERN = "yyyy";

	public void setAsText(String text) throws IllegalArgumentException {

		String date = text;
		if (StringUtils.isEmpty(date)) {
			setValue(null);
		} else {
			try { 
				setValue(DateUtils.parseDate(date, new String[] { DATE_PATTERN, DATETIME_PATTERN, MONTH_PATTERN,
						DATETIMEMM_PATTERN ,YEAR_PATTERN}));
			} catch (ParseException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	public String getAsText() {
		Date date = (Date) getValue();
		if (date == null)
			return "";
		return format.format(date);
	}
}