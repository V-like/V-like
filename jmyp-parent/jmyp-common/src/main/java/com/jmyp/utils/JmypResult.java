package com.jmyp.utils;

import java.io.Serializable;

/**
 * Created by on 2018/12/12.
 */
public class JmypResult implements Serializable {
    //0:成功 1：失败
    private Integer errno;
    //成功，或失败信息
    private String errmsg;

    public JmypResult(Integer errno, String errmsg) {
        this.errno = errno;
        this.errmsg = errmsg;
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
