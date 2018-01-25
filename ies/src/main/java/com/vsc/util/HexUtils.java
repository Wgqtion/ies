package com.vsc.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class HexUtils {
	/**
	 * 十六进制转int数组
	 * 
	 * @param src
	 * @return
	 */
	public static int[] HexString2Int(String str) {
		int len = str.length();
		int[] ret = new int[len / 2 + 2];
		for (int i = 0; i < len; i += 2) {
			ret[i / 2] = HexUtils.HexToDec(str, i);
		}
		return ret;
	}

	/**
	 * 十六进制表示的字符串转换为字节数组
	 *
	 * @param s
	 *            十六进制表示的字符串
	 * @return byte[] 字节数组
	 */
	public static byte[] HexStringToByteArray(String s) {
		int len = s.length();
		byte[] b = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
			b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return b;
	}

	/**
	 * 十六进制转十进制
	 * 
	 * @param str
	 *            字符
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @return
	 */
	public static String HexToDec(String str, int start, int end) {
		if (str != null && str.length() >= end) {
			return addZeroForNum(Integer.parseInt(str.substring(start, end), 16) + "", end - start);
		}
		return null;
	}

	/**
	 * 十六进制转十进制
	 * 
	 * @param str
	 *            字符
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @return
	 */
	public static int HexToDec(String str, int start) {
		if (str != null && str.length() >= start + 2) {
			return Integer.parseInt(str.substring(start, start + 2), 16);
		}
		return 0;
	}

	/**
	 * 十进制转十六进制
	 * 
	 * @param num
	 *            10进制
	 * @param len
	 *            补零长度
	 * @return
	 */
	public static String DecToHex(int num, int len) {
		String hex = Integer.toHexString(num);
		return addZeroForNum(hex, len);
	}

	/**
	 * 十进制转十六进制
	 * 
	 * @param num
	 *            10进制
	 * @param len
	 *            补零长度
	 * @return
	 */
	public static String DecToHex(String num, int len) {
		String hex = Integer.toHexString(Integer.valueOf(num));
		return addZeroForNum(hex, len);
	}

	/**
	 * 补零
	 * 
	 * @param str
	 *            字符
	 * @param strLength
	 *            长度
	 * @return
	 */
	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		while (strLen < strLength) {
			StringBuffer sb = new StringBuffer();
			sb.append("0").append(str);// 左补0
			str = sb.toString();
			strLen = str.length();
		}
		return str;
	}

	/**
	 * 获取ByteBuf
	 * 
	 * @param userId
	 * @param ipAddress
	 * @param lockNum
	 * @param state
	 * @return
	 */
	public static ByteBuf getByteBuf(String userId, String ipAddress, String lockNum, String state) {
		StringBuffer sb = new StringBuffer();
		sb.append(HexUtils.DecToHex(userId, 8));
		sb.append(HexUtils.DecToHex(ipAddress, 4));
		sb.append(HexUtils.DecToHex(lockNum, 4));
		sb.append(HexUtils.DecToHex(state, 2));
		sb.append(HexUtils.DecToHex(0x0D, 2));
		sb.append(HexUtils.DecToHex(0x0A, 2));
		int[] msg = HexUtils.HexString2Int(sb.toString());
		byte[] bytes = new byte[msg.length];
		for (int i = 0; i < msg.length; i++) {
			bytes[i] = (byte) msg[i];
		}
		ByteBuf buf = Unpooled.buffer(bytes.length);
		buf.writeBytes(bytes);
		return buf;
	}
	/**
	 * 字节转16进制
	 * @param bytes
	 * @return
	 */
	public static String getHexStr(byte[] bytes) {
		String ret = "";
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toLowerCase();
			if(hex.length()>4&&"0d0a".equals(hex.substring(hex.length()-4))){
				break;
			}
		}
		return ret;
	}
}
