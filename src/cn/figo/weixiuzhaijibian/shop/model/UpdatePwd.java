package cn.figo.weixiuzhaijibian.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePwd extends BaseResponse {
	@JsonProperty("data")
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
