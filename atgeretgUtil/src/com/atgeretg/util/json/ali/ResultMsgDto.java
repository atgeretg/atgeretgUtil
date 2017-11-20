package com.atgeretg.util.json.ali;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * jackson的中toCollection方法的使用例子
 * @author atgeretg
 *
 * @param <E>
 * @param <F>
 * @param <G>
 */
public class ResultMsgDto<E,F,G> implements Serializable {

    private static final long serialVersionUID = 123L;

    private int resultCode;
    private E resultObject;
    private String resultMsg;
    
    private int error;
    private String sms;
    private E order_goods;
    @JsonProperty("dj_goods")
    private F djGoods;
    
    
    
    
	public F getDjGoods() {
		return djGoods;
	}
	public void setDjGoods(F djGoods) {
		this.djGoods = djGoods;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public E getResultObject() {
		return resultObject;
	}
	public void setResultObject(E resultObject) {
		this.resultObject = resultObject;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getSms() {
		return sms;
	}
	public void setSms(String sms) {
		this.sms = sms;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public E getOrder_goods() {
		return order_goods;
	}
	public void setOrder_goods(E order_goods) {
		this.order_goods = order_goods;
	}
    
    
    // get set

}
