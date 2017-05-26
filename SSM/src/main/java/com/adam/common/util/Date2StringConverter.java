package com.adam.common.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * 
 *
 * 类名称：Date2StringConverter
 * 类描述：
 * 创建人：chenbo
 * 修改人：chenbo
 * 修改时间：2014年10月28日 下午4:49:20
 * 修改备注：
 * @version 1.0.0
 *
 */
public class Date2StringConverter implements Converter<Date,String>{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private static final FastDateFormat _formatter = FastDateFormat.getInstance("yyyy-MM-dd");
	@Override
	public String convert(Date source) {
		logger.debug("=====================Date2StringConverter InIt===============");
		if(source==null){
			return null;
		}
	    return _formatter.format(source) ;          
	}
	
	public static String dateToString(Date date){
		return _formatter.format(date);
	}
	
	public static String dateToString(long timeInMillseconds){
		return _formatter.format(timeInMillseconds);
	}
	
	public static String dateToString(Calendar date){
		return _formatter.format(date);
	}
	
}
