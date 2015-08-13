package cn.figo.weixiuzhaijibian.shop.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemMsg implements Serializable{
	@JsonProperty("SYSTEM_MSG_ID")
    private String SYSTEM_MSG_ID;

	@JsonProperty("SM_TYPE")
    private Integer SM_TYPE;

	@JsonProperty("SM_TITLE")
    private String SM_TITLE;

	@JsonProperty("SM_CONTENT")
    private String SM_CONTENT;

	@JsonProperty("SM_PUBLISHER")
    private String SM_PUBLISHER;

	@JsonProperty("SM_ADD_TIME")
    private String SM_ADD_TIME;

	@JsonProperty("SM_MOD_TIME")
    private String SM_MOD_TIME;

	@JsonProperty("SM_MOD_ADMINID")
    private String SM_MOD_ADMINID;

	@JsonProperty("SM_EXT1")
    private String SM_EXT1;

	@JsonProperty("SM_EXT2")
    private String SM_EXT2;

    public String getSYSTEM_MSG_ID() {
        return SYSTEM_MSG_ID;
    }

    public void setSYSTEM_MSG_ID(String SYSTEM_MSG_ID) {
        this.SYSTEM_MSG_ID = SYSTEM_MSG_ID == null ? null : SYSTEM_MSG_ID.trim();
    }


    public Integer getSM_TYPE() {
		return SM_TYPE;
	}

	public void setSM_TYPE(Integer sM_TYPE) {
		SM_TYPE = sM_TYPE;
	}

	public String getSM_TITLE() {
        return SM_TITLE;
    }

    public void setSM_TITLE(String SM_TITLE) {
        this.SM_TITLE = SM_TITLE == null ? null : SM_TITLE.trim();
    }

    public String getSM_CONTENT() {
        return SM_CONTENT;
    }

    public void setSM_CONTENT(String SM_CONTENT) {
        this.SM_CONTENT = SM_CONTENT == null ? null : SM_CONTENT.trim();
    }

    public String getSM_PUBLISHER() {
        return SM_PUBLISHER;
    }

    public void setSM_PUBLISHER(String SM_PUBLISHER) {
        this.SM_PUBLISHER = SM_PUBLISHER == null ? null : SM_PUBLISHER.trim();
    }

    public String getSM_ADD_TIME() {
        return SM_ADD_TIME;
    }

    public void setSM_ADD_TIME(String SM_ADD_TIME) {
        this.SM_ADD_TIME = SM_ADD_TIME == null ? null : SM_ADD_TIME.trim();
    }

    public String getSM_MOD_TIME() {
        return SM_MOD_TIME;
    }

    public void setSM_MOD_TIME(String SM_MOD_TIME) {
        this.SM_MOD_TIME = SM_MOD_TIME == null ? null : SM_MOD_TIME.trim();
    }

    public String getSM_MOD_ADMINID() {
        return SM_MOD_ADMINID;
    }

    public void setSM_MOD_ADMINID(String SM_MOD_ADMINID) {
        this.SM_MOD_ADMINID = SM_MOD_ADMINID == null ? null : SM_MOD_ADMINID.trim();
    }

    public String getSM_EXT1() {
        return SM_EXT1;
    }

    public void setSM_EXT1(String SM_EXT1) {
        this.SM_EXT1 = SM_EXT1 == null ? null : SM_EXT1.trim();
    }

    public String getSM_EXT2() {
        return SM_EXT2;
    }

    public void setSM_EXT2(String SM_EXT2) {
        this.SM_EXT2 = SM_EXT2 == null ? null : SM_EXT2.trim();
    }
}