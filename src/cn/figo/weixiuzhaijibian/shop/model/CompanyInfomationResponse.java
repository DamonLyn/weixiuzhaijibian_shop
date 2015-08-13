package cn.figo.weixiuzhaijibian.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyInfomationResponse extends BaseResponse{
	
	@JsonProperty("data")
	private  CompanyInfomation data;

	public CompanyInfomation getData() {
		return data;
	}

	public void setData(CompanyInfomation data) {
		this.data = data;
	}








	
}
