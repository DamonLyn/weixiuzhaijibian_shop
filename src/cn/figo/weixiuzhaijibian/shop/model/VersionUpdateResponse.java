package cn.figo.weixiuzhaijibian.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VersionUpdateResponse extends BaseResponse {
	@JsonProperty("data")	
	private AppUpdate data;

	public AppUpdate getData() {
		return data;
	}

	public void setData(AppUpdate data) {
		this.data = data;
	}



	
}
