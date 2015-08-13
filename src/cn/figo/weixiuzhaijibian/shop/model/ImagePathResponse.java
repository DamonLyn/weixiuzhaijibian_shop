package cn.figo.weixiuzhaijibian.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 服务器返回的图片路径（用于图片上传接口）
 *
 */
public class ImagePathResponse extends BaseResponse{
	@JsonProperty("data")
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
