package com.vsc.modules.quartz.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSONObject;
import com.vsc.business.gerd.entity.work.WxCore;
import com.vsc.constants.Constants;
import com.vsc.util.CoreUtils;
import com.vsc.util.HttpRequestUtil;
import com.vsc.util.Log4jUtils;
import com.vsc.util.WxCoreServiceUtil;

/**
 * 超时自动取消预约
 * 
 * @author XiangXiaoLin
 *
 */
public class ReserveCancelJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Log4jUtils.reserveCancel.info("系统开始取消超时预约：");
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		if (dataMap != null) {
			String weixinId = (String) dataMap.get("data1");
			String formId = (String) dataMap.get("data2");
			if (weixinId != null) {
				WxCore wxCore = new WxCore();
				wxCore.setWeixinId(weixinId);
				wxCore.setIsCancel(true);
				wxCore.setType(Integer.valueOf(1));
				wxCore.setIsSystemCancel(true);
				wxCore = WxCoreServiceUtil.getWxCoreService().cancelReserve(wxCore, false);
				if (wxCore.getResultStatus() != 1) {
					try {
						String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7da35897ec0b8ac0&secret=ad07c9d890ddd4f292055b107d875e60";
						String result = HttpRequestUtil.sendHttpGet(url);
						Log4jUtils.reserveCancel.info("token:" + result);
						JSONObject jo = JSONObject.parseObject(result);
						String token1 = jo.getString("access_token");

						String body = "{\"touser\":\"" + weixinId
								+ "\",\"template_id\":\"2XQD4Wrx_0RP5jLRfFdh_gqbDcEbIKWFvWuHTPFF4V8\",\"page\":\"pages/index/index\",\"form_id\":\""
								+ formId + "\",\"data\":{\"keyword1\":{\"value\":\""
								+ CoreUtils.formatDate(wxCore.getStartTime(), 1)
								+ "\"},\"keyword2\":{\"value\":\"您预约的车位已超时自动取消，本次预约费用：" + wxCore.getAmount().doubleValue()
								+ "\"}}}";
						result = HttpRequestUtil.sendHttpPost(
								"https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + token1,
								body);
						Log4jUtils.reserveCancel.info("body:" + body);
						Log4jUtils.reserveCancel.info("template:" + result);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Log4jUtils.reserveCancel.info("系统取消超时预约：" + Constants.CANCEL_RESERVE_MESSAGE_STATUS[wxCore.getResultStatus()]);
			}
		}
	}
}
