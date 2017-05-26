package com.adam.common.util;

public class ProvinceCodeUtil {
	public static boolean isProvinceCode(String code) {
		int num = Integer.parseInt(code);
		return num % 10000 == 0 ? true : false;
	}

	public static String getProvinceCode(String code) {
		return code.substring(0, 2) + "0000";
	}
}
