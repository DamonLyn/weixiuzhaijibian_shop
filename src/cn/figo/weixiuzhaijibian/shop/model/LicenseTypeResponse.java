package cn.figo.weixiuzhaijibian.shop.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LicenseTypeResponse extends BaseResponse {
	@JsonProperty("data")
	private List<LicenseType> data;

	public List<LicenseType> getData() {
		return data;
	}

	public void setData(List<LicenseType> data) {
		this.data = data;
	}
}
