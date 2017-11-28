package com.vsc.modules.spring.mvc.convert;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

public class StringToDateTimeConverter implements Converter<String, Date>{
	//private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final String DATETIMEMM_PATTERN = "yyyy-MM-dd HH:mm";
	private static final String MONTH_PATTERN = "yyyy-MM";
	private static final String YEAR_PATTERN = "yyyy";
	
	public Date convert(String source) {
		String date = source;
		if (StringUtils.isEmpty(source)) {
			return null;
		} else {
			try { 
				return DateUtils.parseDate(date, new String[] { DATE_PATTERN, DATETIME_PATTERN, MONTH_PATTERN,
						DATETIMEMM_PATTERN,YEAR_PATTERN });
			} catch (ParseException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}
}
