package cn.figo.weixiuzhaijibian.shop.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AreaResponse extends BaseResponse {
	@JsonProperty("data")
	private List<Area> data;

	public List<Area> getData() {
		return data;
	}

	public void setData(List<Area> data) {
		this.data = data;
	}
}
