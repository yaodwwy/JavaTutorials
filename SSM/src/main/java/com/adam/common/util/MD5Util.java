package com.adam.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * 类名称：MD5Util
 * 类描述：
 * 创建人：chenbo
 * 修改人：chenbo
 * 修改时间：2015年2月9日 下午1:52:06
 * 修改备注：
 * @version 1.0.0
 *
 */
public class MD5Util {
	
	public static void main(String[] args) {
		String str = encryption("123123");
		System.out.println("一次加密Md5(VOD_MOVE)结果：" + str);
		String string = encryption("secondMD5" + str);
		System.out.println("二次加密Md5(secondMD5" + str + ")结果：" + string);
	}

	/**
	 * 
	 * @param plain  明文
	 * @return 32位小写密文
	 */
	public static String encryption(String plain) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plain.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}
}
