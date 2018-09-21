package com.vsc.modules.tcp.entity;

import org.apache.commons.lang3.StringUtils;

import com.vsc.util.HexUtils;

/**
 * tcp消息类
 * 
 * @author XiangXiaoLin
 *
 */
public class TcpMsg {

	/**
	 * 字节消息
	 */
	private byte[] reds;
	
	/**
	 * 16进制消息
	 */
	private String hexMsg;

	public TcpMsg() {

	}

	public TcpMsg(byte[] reds) {
		this.reds = reds;
		this.hexMsg = HexUtils.getHexStr(reds);
	}

	public boolean getFlagCRC() {
		return CRC16M.checkCRC(hexMsg, 9);
	}

	public String getHexMsg() {
		return hexMsg;
	}

	public byte[] getReds() {
		return reds;
	}

	public String getIpAddress() {
		return hexMsg.substring(4, 8);
	}

	public String getEventType() {
		return hexMsg.substring(8, 10);
	}

	public String getLockNum() {
		return hexMsg.substring(10, 14);
	}

	public String getState(){
		return hexMsg.substring(14,16);
	}
	
	public String getMcOpen(){
		return hexMsg.substring(16,18);
	}
	
	/**
	 * 状态码转换，补足9位
	 * @return
	 */
	public String getStateString(){	
		String dlState=Integer.toBinaryString(Integer.valueOf(HexUtils.HexToDec(getState(),0,2)));		
		
		return StringUtils.leftPad(dlState, 8, "0");
	}
	
	/**
	 * 地锁开关状态
	 */
	public Boolean getIsOpen(){
		return  "0".equals(StringUtils.substring(this.getStateString(),4,5))?true:false;
	}
	
	/**
	 * 超声波检测是否有车
	 */
	public Boolean getIsCarOn(){
		return  "0".equals(StringUtils.substring(this.getStateString(),3,4))?false:true;
	}

	/**
	 * 电量解析
	 */
	public String getDianLiang(){
		String dlState=this.getStateString();
		return StringUtils.substring(dlState,dlState.length()-3);
	}
	
	/**
	 * 获取电量
	 */
	public double getPower(){
		double returnD=0;
		String s=this.getDianLiang();
		switch (s) {
		case "000":
			returnD=4.5;
			break;
		case "001":
			returnD=4.8;
			break;
		case "011":
			returnD=5.1;
			break;
		case "010":
			returnD=5.4;
			break;
		case "100":
			returnD=5.7;
			break;
		case "101":
			returnD=6.0;
			break;
		case "110":
			returnD=6.3;
			break;
		case "111":
			returnD=6.6;
			break;
		default:
			break;
		}
		return returnD;
	}

	
}
