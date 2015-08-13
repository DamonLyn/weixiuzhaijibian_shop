package cn.figo.weixiuzhaijibian.shop.model;

import java.io.Serializable;

/**
 * 服务响应的基本业务码
 *
 */
public class BaseResponse implements Serializable {
	public String code;
	public String msg;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
