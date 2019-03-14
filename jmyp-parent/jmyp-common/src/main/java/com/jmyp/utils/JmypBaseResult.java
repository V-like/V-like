package com.jmyp.utils;

import java.io.Serializable;

/**
 * Created by on 2018/12/12.
 */
public class JmypBaseResult implements Serializable {
    //0:成功 1：失败
    private Integer errno;
    //成功，或失败信息
    private String errmsg;

    private String token;

    public JmypBaseResult(Integer errno, String errmsg, String token) {
        this.errno = errno;
        this.errmsg = errmsg;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
