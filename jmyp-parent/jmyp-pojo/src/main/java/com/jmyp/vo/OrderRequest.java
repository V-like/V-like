package com.jmyp.vo;

import org.omg.CORBA.INTERNAL;

import java.io.Serializable;

/**
 * Created by on 2019/1/3.
 */
public class OrderRequest implements Serializable{

    private Integer addressId;
    private Integer pay_method;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getPay_method() {
        return pay_method;
    }

    public void setPay_method(Integer pay_method) {
        this.pay_method = pay_method;
    }
}
