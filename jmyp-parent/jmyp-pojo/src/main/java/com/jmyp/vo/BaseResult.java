package com.jmyp.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by on 2018/12/14.
 */
public class BaseResult implements Serializable {

    //成功失败标识： 0 成功 1 失败
    private Integer errno;

    //成功失败的信息
    private String errmsg;

    //总计记录数
    private Long total;

    //快报集合数据
    private List<?> data;


    public BaseResult(Integer errno, String errmsg, Long total, List<?> data) {
        this.errno = errno;
        this.errmsg = errmsg;
        this.total = total;
        this.data = data;
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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
