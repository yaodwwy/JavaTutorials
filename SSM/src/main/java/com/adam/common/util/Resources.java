package com.adam.common.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Resources {
	private static Log log = LogFactory.getLog(Resources.class);
	private static Resources resources;
	private static Properties properties = new Properties();

	private static String defaultFileName = "resource";

	private static String fileName = "";

	private static ResourceBundle resourceBundle = null;

	private static String language = "zh";
	private static String country = "CN";

	public static Resources instance() {
		return instance(defaultFileName);
	}

	public static Resources instance(String sFileName) {
		return instance(sFileName, null);
	}

	public static Resources instance(String sFileName, Locale locale) {
		try {
			if ((sFileName == null) || ("".equals(sFileName.trim()))) {
				sFileName = defaultFileName;
			}
			if (resources == null) {
				resources = new Resources();
			}
			resourceBundle = ResourceBundle.getBundle(sFileName,
					locale != null ? locale : new Locale(language, country));
		} catch (Exception e) {
			log.debug("系统中没有资源文件：" + sFileName + "，自动匹配默认资源文件");
			sFileName = "default";
			try {
				resourceBundle = ResourceBundle.getBundle(sFileName,
						new Locale(language, country));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		setResourceBundle(resourceBundle);
		return resources;
	}

	public String getAttributeValue(String key) {
		return getAttributeValue(key, null);
	}

	public Map<String, String> getArributes() {
		Map result = Collections.EMPTY_MAP;
		Enumeration enumeration = resourceBundle.getKeys();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			result = result.isEmpty() ? Collections
					.synchronizedMap(new HashMap()) : result;
			result.put(key, getAttributeValue(key));
		}
		return result;
	}

	public List<String> getArributeValues() {
		List result = Collections.EMPTY_LIST;
		Enumeration enumeration = resourceBundle.getKeys();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			result = result.isEmpty() ? Collections
					.synchronizedList(new ArrayList()) : result;
			result.add(getAttributeValue(key));
		}
		return result;
	}

	public String getAttributeValue(String key, Object[] params) {
		String result = "";
		try {
			result = resourceBundle.getString(key);
		} catch (Exception e) {
		} finally {
			if (params == null)
				return result;
		}
		if (params.length > 0) {
			result = MessageFormat.format(result, params);
		}
		return result;
	}

	public static ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public static ResourceBundle getResourceBundle(String filename) {
		instance(filename);
		return getResourceBundle();
	}

	public static void setResourceBundle(ResourceBundle resourceBundle) {
		Resources.resourceBundle = resourceBundle;
	}

	public String getLanguage() {
		return language;
	}

	public void setLocale(String language, String country) {
		Resources.language = language;
		Resources.country = country;
	}

	public static String getCountry() {
		return country;
	}

	public static void main(String[] args) {
	}
}