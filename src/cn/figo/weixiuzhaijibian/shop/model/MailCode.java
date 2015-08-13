package cn.figo.weixiuzhaijibian.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 验证码实体类
 *
 */
public class MailCode extends BaseResponse{
	@JsonProperty("data")
	private String data;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
