package com.lovo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5������
 * @author ���
 *
 */
public final class MD5Util {
	private static final char[] hexDigits = { 
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' 
	};

	private static MessageDigest md = null;

	static {
		try {
			md = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private MD5Util() {
		throw new AssertionError();
	}

	/**
	 * ����
	 * @param orginStr ԭʼ�ַ���
	 * @return ���ܺ���ַ���
	 */
	public static String encode(String orginStr) {
		byte[] result = md.digest(orginStr.getBytes());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			sb.append(hexDigits[(result[i] >> 4) & 0x0f]);
			sb.append(hexDigits[result[i] & 0x0f]);
		}
		return sb.toString();
	}
}
