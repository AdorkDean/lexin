package com.caipiao.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JiaMi
{
	public static String md5(String inputText)
	{
		return encrypt(inputText, "md5");
	}
	public static String md5_2(String inputText) {
		String encrypt = encrypt(inputText, "md5");
		return reverse(encrypt);
	}

	public static String sha(String inputText) {
		return encrypt(inputText, "sha-1");
	}
	private static String reverse(String s) {
		int length = s.length();
		String reverse = "";
		for (int i = 0; i < length; i++)
			reverse = s.charAt(i) + reverse;
		return reverse;
	}

	private static String encrypt(String inputText, String algorithmName)
	{
		if ((inputText == null) || ("".equals(inputText.trim()))) {
			throw new IllegalArgumentException("请输入要加密的内容");
		}
		if ((algorithmName == null) || ("".equals(algorithmName.trim()))) {
			algorithmName = "md5";
		}
		String encryptText = null;
		try {
			MessageDigest m = MessageDigest.getInstance(algorithmName);
			m.update(inputText.getBytes("UTF8"));
			byte[] s = m.digest();

			return hex(s);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encryptText;
	}

	private static String hex(byte[] arr)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append(Integer.toHexString(arr[i] & 0xFF | 0x100).substring(1,
					3));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String md5_1 = md5("123");
		String md5_2 = md5_2("123");
		System.out.println(md5_1 + "\n" + md5_2);
		System.out.println("md5 length: " + md5_1.length());

		String sha_1 = sha("123");
		String sha_2 = sha("abc");
		System.out.println(sha_1 + "\n" + sha_2);
		System.out.println("sha length: " + sha_1.length());
	}
}