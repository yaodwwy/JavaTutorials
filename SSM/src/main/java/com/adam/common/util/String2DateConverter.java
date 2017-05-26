package com.adam.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * 
 *
 * 类名称：String2DateConverter
 * 类描述：
 * 创建人：chenbo
 * 修改人：chenbo
 * 修改时间：2014年10月28日 下午4:49:26
 * 修改备注：
 * @version 1.0.0
 *
 */
public class String2DateConverter implements Converter<String, Date>{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Date convert(String source) {
		logger.debug("=====================String2DateConverter InIt===============");
		if(StringUtils.isBlank(source)){
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
	    dateFormat.setLenient(false);   
	    try {   
	        return dateFormat.parse(source);   
	    } catch (ParseException e) {   
	    	logger.error("String2DateConverter Error",e);   
	    }          
	    return null;
	}

}
