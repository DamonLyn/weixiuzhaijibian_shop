package cn.figo.weixiuzhaijibian.shop.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostMsg implements Serializable{
	@JsonProperty("psot_MSG_ID")
    private String psot_MSG_ID;

	@JsonProperty("PM_TYPE")
    private String PM_TYPE;

	@JsonProperty("PM_TITLE")
    private String PM_TITLE;

	@JsonProperty("PM_CONTENT")
    private String PM_CONTENT;

	@JsonProperty("PM_RECEIVER_ID")
    private String PM_RECEIVER_ID;

	@JsonProperty("PM_RECEIVER_TYPE")
    private Integer PM_RECEIVER_TYPE;

	@JsonProperty("PM_ADD_TIME")
    private String PM_ADD_TIME;

	@JsonProperty("PM_MSG_STATUS")
    private Integer PM_MSG_STATUS;

	@JsonProperty("PM_READ_TIME")
    private String PM_READ_TIME;

	@JsonProperty("PM_EXT1")
    private String PM_EXT1;

	@JsonProperty("PM_EXT2")
    private String PM_EXT2;

	@JsonProperty("hasRead")
	private Integer hasRead;
	
 
    public String getPsot_MSG_ID() {
		return psot_MSG_ID;
	}

	public void setPsot_MSG_ID(String psot_MSG_ID) {
		this.psot_MSG_ID = psot_MSG_ID;
	}

	public String getPM_TYPE() {
        return PM_TYPE;
    }

    public void setPM_TYPE(String PM_TYPE) {
        this.PM_TYPE = PM_TYPE == null ? null : PM_TYPE.trim();
    }

    public String getPM_TITLE() {
        return PM_TITLE;
    }

    public void setPM_TITLE(String PM_TITLE) {
        this.PM_TITLE = PM_TITLE == null ? null : PM_TITLE.trim();
    }

    public String getPM_CONTENT() {
        return PM_CONTENT;
    }

    public void setPM_CONTENT(String PM_CONTENT) {
        this.PM_CONTENT = PM_CONTENT == null ? null : PM_CONTENT.trim();
    }

    public String getPM_RECEIVER_ID() {
        return PM_RECEIVER_ID;
    }

    public void setPM_RECEIVER_ID(String PM_RECEIVER_ID) {
        this.PM_RECEIVER_ID = PM_RECEIVER_ID == null ? null : PM_RECEIVER_ID.trim();
    }

    public Integer getPM_RECEIVER_TYPE() {
        return PM_RECEIVER_TYPE;
    }

    public void setPM_RECEIVER_TYPE(Integer PM_RECEIVER_TYPE) {
        this.PM_RECEIVER_TYPE = PM_RECEIVER_TYPE;
    }

    public String getPM_ADD_TIME() {
        return PM_ADD_TIME;
    }

    public void setPM_ADD_TIME(String PM_ADD_TIME) {
        this.PM_ADD_TIME = PM_ADD_TIME == null ? null : PM_ADD_TIME.trim();
    }

    public Integer getPM_MSG_STATUS() {
        return PM_MSG_STATUS;
    }

    public void setPM_MSG_STATUS(Integer PM_MSG_STATUS) {
        this.PM_MSG_STATUS = PM_MSG_STATUS;
    }

    public String getPM_READ_TIME() {
        return PM_READ_TIME;
    }

    public void setPM_READ_TIME(String PM_READ_TIME) {
        this.PM_READ_TIME = PM_READ_TIME == null ? null : PM_READ_TIME.trim();
    }

    public String getPM_EXT1() {
        return PM_EXT1;
    }

    public void setPM_EXT1(String PM_EXT1) {
        this.PM_EXT1 = PM_EXT1 == null ? null : PM_EXT1.trim();
    }

    public String getPM_EXT2() {
        return PM_EXT2;
    }

    public void setPM_EXT2(String PM_EXT2) {
        this.PM_EXT2 = PM_EXT2 == null ? null : PM_EXT2.trim();
    }

	public Integer getHasRead() {
		return hasRead;
	}

	public void setHasRead(Integer hasRead) {
		this.hasRead = hasRead;
	}
    
    
}