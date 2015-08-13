package cn.figo.weixiuzhaijibian.shop.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemMsgResponse extends BaseResponse {
	@JsonProperty("data")
	private List<PostMsg> data;

	@JsonProperty("totalPage")
	private int totalPage;
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<PostMsg> getData() {
		return data;
	}

	public void setData(List<PostMsg> data) {
		this.data = data;
	}
}
