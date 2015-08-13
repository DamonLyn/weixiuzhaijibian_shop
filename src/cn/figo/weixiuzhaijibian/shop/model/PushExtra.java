package cn.figo.weixiuzhaijibian.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PushExtra {

	@JsonProperty("msgID")
	private String msgID;
	@JsonProperty("key1")
	private String key1;
	@JsonProperty("key2")
	private String key2;
	@JsonProperty("key3")
	private String key3;
	@JsonProperty("key4")
	private String key4;
	@JsonProperty("key5")
	private String key5;

	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public String getKey3() {
		return key3;
	}

	public void setKey3(String key3) {
		this.key3 = key3;
	}

	public String getKey4() {
		return key4;
	}

	public void setKey4(String key4) {
		this.key4 = key4;
	}

	public String getKey5() {
		return key5;
	}

	public void setKey5(String key5) {
		this.key5 = key5;
	}
}
