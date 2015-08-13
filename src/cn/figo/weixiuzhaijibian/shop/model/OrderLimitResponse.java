package cn.figo.weixiuzhaijibian.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderLimitResponse extends BaseResponse {
	@JsonProperty("data")
	private Integer data;
	
	@JsonProperty("max")
	private Integer max;

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
	
	
	
}
