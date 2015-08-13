package cn.figo.weixiuzhaijibian.shop.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdvertiseResponse extends BaseResponse{
	@JsonProperty("data")
	private List<Advertise> data;

	public List<Advertise> getData() {
		return data;
	}

	public void setData(List<Advertise> data) {
		this.data = data;
	}

	
}
