package com.cpw.util;

/**
 * MD5加密工具类
 */
import java.security.MessageDigest;

/**
 * MD5加密工具类
 * 
 * @author pengwei_chen
 * @date 2014-2-25 下午2:49:53
 */
public class MD5Util {
	/**
	 * MD5加密实现方法
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String MD5(String str) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = str.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char tempStr[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				tempStr[k++] = hexDigits[byte0 >>> 4 & 0xf];
				tempStr[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(tempStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		System.out.println(MD5("admin"));
	}
}
