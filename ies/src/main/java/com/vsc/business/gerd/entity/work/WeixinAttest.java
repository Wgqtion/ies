package com.vsc.business.gerd.entity.work;


/**
 * 微信验证信息
 * @author wgqki
 */
public class WeixinAttest {
	
	private String session_key;
        private String expires_in;
        private String openid;

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
        
}
