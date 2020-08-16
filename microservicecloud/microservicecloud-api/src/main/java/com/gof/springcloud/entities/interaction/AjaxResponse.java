package com.gof.springcloud.entities.interaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AjaxResponse {

	private boolean isok; //flag for whether it succeed
	private int code; //response code (200、400、500)
	private String message;  // response message
	private Object data; //response result for query

	private AjaxResponse() {
	}

	public static AjaxResponse success() {
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setIsok(true);
		ajaxResponse.setCode(200);
		ajaxResponse.setMessage("request succeed");
		return ajaxResponse;
	}

	public static AjaxResponse success(Object obj) {
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setIsok(true);
		ajaxResponse.setCode(200);
		ajaxResponse.setMessage("request succeed");
		ajaxResponse.setData(obj);
		return ajaxResponse;
	}

	public static AjaxResponse success(Object obj, String message) {
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setIsok(true);
		ajaxResponse.setCode(200);
		ajaxResponse.setMessage(message);
		ajaxResponse.setData(obj);
		return ajaxResponse;
	}

	public boolean isIsok() {
		return isok;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public void setIsok(boolean isok) {
		this.isok = isok;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(Object data) {
		this.data = data;
	}

}