package com.jmyp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by on 2019/1/4.
 */
@ConfigurationProperties("wx.config")
public class WxPayConfig {

    //唯一标识
    private String appid;

    //商户号
    private String partner;

    //生成二维码支付链接接口地址
    private String payUrl;

    private String notifyurl;

    //秘钥
    private String partnerkey;

    public String getNotifyurl() {
        return notifyurl;
    }

    public void setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getPartnerkey() {
        return partnerkey;
    }

    public void setPartnerkey(String partnerkey) {
        this.partnerkey = partnerkey;
    }
}
