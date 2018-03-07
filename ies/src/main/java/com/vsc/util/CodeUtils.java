package com.vsc.util;

/**
 * 代码辅助类
 * @author XiangXiaoLin
 *
 */
public class CodeUtils {
	/**
	 * 根据数生成+1code
	 * @param n
	 * @param len
	 * @return
	 */
	public static String GenerateCode(int n,int len){
		String s=n+1+"";
		while(s.length()<len){
			s="0"+s;
		}
		return s;
	}
}
