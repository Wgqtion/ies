package com.vsc.util;

import java.util.Random;

/**
 * 随机码工具类
 * @author XiangXiaoLin
 *
 */
public class RandomPassword {
	public static void main(String[] args) {

		String password = makeRandomPassword(8);
		System.out.println(password);
	}

	// 随机密码生成
	public static String makeRandomPassword(int len) {
		char charr[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
		// System.out.println("字符数组长度:" + charr.length); //可以看到调用此方法多少次
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (int x = 0; x < len; ++x) {
			sb.append(charr[r.nextInt(charr.length)]);
		}
		return sb.toString();
	}
}
