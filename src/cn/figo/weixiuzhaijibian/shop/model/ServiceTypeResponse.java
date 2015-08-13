package cn.figo.weixiuzhaijibian.shop.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceTypeResponse extends BaseResponse {
	@JsonProperty("data")
	private List<ServiceType> data;

	public List<ServiceType> getData() {
		return data;
	}

	public void setData(List<ServiceType> data) {
		this.data = data;
	}
}
