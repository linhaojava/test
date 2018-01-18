package cn.tedu.cloudnote.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


/**
 * 用户信息加密工具类
 * 
 * @author soft01
 *
 */
public class NoteUtil {
	/**
	 * MD5加密：摘要算法。 1：任意长度字节处理成等长的结果； 2：不可逆 base64:a-z A-Z 0-9 = +
	 */
	public static String md5(String src) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] output = md.digest(src.getBytes());
			// return new String(output);
			String ret = Base64.encodeBase64String(output);
			return ret;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return src;
	}

	/**
	 * 利用UUID生成 数据库主键
	 * 
	 * @return
	 */
	public static String createId() {
		return UUID.randomUUID().toString();
	}

	public static void main(String[] args) {
		System.out.println(md5("123"));
		System.out.println(md5("123456"));
		System.out.println(md5("123").length());
		System.out.println(md5("123456").length());
		System.out.println(createId());

	}
}
