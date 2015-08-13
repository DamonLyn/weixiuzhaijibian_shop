package cn.figo.weixiuzhaijibian.shop.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderSTPriceResponse extends BaseResponse{
	@JsonProperty("data")
	private List<Order6ServiceTypePrice> data;
	
	@JsonProperty("totalPage")
	private int totalPage;

	public List<Order6ServiceTypePrice> getData() {
		return data;
	}

	public void setData(List<Order6ServiceTypePrice> data) {
		this.data = data;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
