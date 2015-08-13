package cn.figo.weixiuzhaijibian.shop.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppUpdate implements Serializable{
	@JsonProperty("APP_UPDATE_ID")
	private String APP_UPDATE_ID;

	@JsonProperty("AU_APP_NAME")
    private String AU_APP_NAME;

	@JsonProperty("AU_APP_TYPE")
    private Integer AU_APP_TYPE;

	@JsonProperty("AU_VERSION_CODE")
    private Integer AU_VERSION_CODE;

	@JsonProperty("AU_VERSION_NAME")
    private String AU_VERSION_NAME;

	@JsonProperty("AU_DOWNLOAD_URL")
    private String AU_DOWNLOAD_URL;

	@JsonProperty("AU_UPDATE_NOTE")
    private String AU_UPDATE_NOTE;

	@JsonProperty("AU_ADD_TIME")
    private String AU_ADD_TIME;

	@JsonProperty("AU_MOD_TIME")
    private String AU_MOD_TIME;

	@JsonProperty("AU_MOD_ADMINID")
    private String AU_MOD_ADMINID;

	@JsonProperty("AU_EXT1")
    private String AU_EXT1;

	@JsonProperty("AU_EXT2")
    private String AU_EXT2;

    public String getAPP_UPDATE_ID() {
        return APP_UPDATE_ID;
    }

    public void setAPP_UPDATE_ID(String APP_UPDATE_ID) {
        this.APP_UPDATE_ID = APP_UPDATE_ID == null ? null : APP_UPDATE_ID.trim();
    }

    public String getAU_APP_NAME() {
        return AU_APP_NAME;
    }

    public void setAU_APP_NAME(String AU_APP_NAME) {
        this.AU_APP_NAME = AU_APP_NAME == null ? null : AU_APP_NAME.trim();
    }

    public Integer getAU_APP_TYPE() {
        return AU_APP_TYPE;
    }

    public void setAU_APP_TYPE(Integer AU_APP_TYPE) {
        this.AU_APP_TYPE = AU_APP_TYPE;
    }

    public Integer getAU_VERSION_CODE() {
        return AU_VERSION_CODE;
    }

    public void setAU_VERSION_CODE(Integer AU_VERSION_CODE) {
        this.AU_VERSION_CODE = AU_VERSION_CODE;
    }


    public String getAU_VERSION_NAME() {
		return AU_VERSION_NAME;
	}

	public void setAU_VERSION_NAME(String aU_VERSION_NAME) {
		AU_VERSION_NAME = aU_VERSION_NAME;
	}

	public String getAU_DOWNLOAD_URL() {
        return AU_DOWNLOAD_URL;
    }

    public void setAU_DOWNLOAD_URL(String AU_DOWNLOAD_URL) {
        this.AU_DOWNLOAD_URL = AU_DOWNLOAD_URL == null ? null : AU_DOWNLOAD_URL.trim();
    }

    public String getAU_UPDATE_NOTE() {
        return AU_UPDATE_NOTE;
    }

    public void setAU_UPDATE_NOTE(String AU_UPDATE_NOTE) {
        this.AU_UPDATE_NOTE = AU_UPDATE_NOTE == null ? null : AU_UPDATE_NOTE.trim();
    }

    public String getAU_ADD_TIME() {
        return AU_ADD_TIME;
    }

    public void setAU_ADD_TIME(String AU_ADD_TIME) {
        this.AU_ADD_TIME = AU_ADD_TIME == null ? null : AU_ADD_TIME.trim();
    }

    public String getAU_MOD_TIME() {
        return AU_MOD_TIME;
    }

    public void setAU_MOD_TIME(String AU_MOD_TIME) {
        this.AU_MOD_TIME = AU_MOD_TIME == null ? null : AU_MOD_TIME.trim();
    }

    public String getAU_MOD_ADMINID() {
        return AU_MOD_ADMINID;
    }

    public void setAU_MOD_ADMINID(String AU_MOD_ADMINID) {
        this.AU_MOD_ADMINID = AU_MOD_ADMINID == null ? null : AU_MOD_ADMINID.trim();
    }

    public String getAU_EXT1() {
        return AU_EXT1;
    }

    public void setAU_EXT1(String AU_EXT1) {
        this.AU_EXT1 = AU_EXT1 == null ? null : AU_EXT1.trim();
    }

    public String getAU_EXT2() {
        return AU_EXT2;
    }

    public void setAU_EXT2(String AU_EXT2) {
        this.AU_EXT2 = AU_EXT2 == null ? null : AU_EXT2.trim();
    }
}