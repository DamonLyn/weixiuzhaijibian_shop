package cn.figo.weixiuzhaijibian.shop.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LicenseType implements Serializable{
	
	@JsonProperty("LICENSE_TYPE_ID")
    private String LICENSE_TYPE_ID;

	@JsonProperty("LT_NAME")
    private String LT_NAME;

	@JsonProperty("LT_IS_SHOW")
    private Integer LT_IS_SHOW;

	@JsonProperty("LT_SORT")
    private Integer LT_SORT;

	@JsonProperty("LT_ADD_TIME")
    private String LT_ADD_TIME;

	@JsonProperty("LT_MOD_TIME")
    private String LT_MOD_TIME;

	@JsonProperty("LT_MOD_ADMINID")
    private String LT_MOD_ADMINID;

	@JsonProperty("LT_EXT1")
    private String LT_EXT1;
	
	@JsonProperty("LT_EXT2")
    private String LT_EXT2;

    public String getLICENSE_TYPE_ID() {
        return LICENSE_TYPE_ID;
    }

    public void setLICENSE_TYPE_ID(String LICENSE_TYPE_ID) {
        this.LICENSE_TYPE_ID = LICENSE_TYPE_ID == null ? null : LICENSE_TYPE_ID.trim();
    }

    public String getLT_NAME() {
        return LT_NAME;
    }

    public void setLT_NAME(String LT_NAME) {
        this.LT_NAME = LT_NAME == null ? null : LT_NAME.trim();
    }

    public Integer getLT_IS_SHOW() {
        return LT_IS_SHOW;
    }

    public void setLT_IS_SHOW(Integer LT_IS_SHOW) {
        this.LT_IS_SHOW = LT_IS_SHOW;
    }

    public Integer getLT_SORT() {
        return LT_SORT;
    }

    public void setLT_SORT(Integer LT_SORT) {
        this.LT_SORT = LT_SORT;
    }

    public String getLT_ADD_TIME() {
        return LT_ADD_TIME;
    }

    public void setLT_ADD_TIME(String LT_ADD_TIME) {
        this.LT_ADD_TIME = LT_ADD_TIME == null ? null : LT_ADD_TIME.trim();
    }

    public String getLT_MOD_TIME() {
        return LT_MOD_TIME;
    }

    public void setLT_MOD_TIME(String LT_MOD_TIME) {
        this.LT_MOD_TIME = LT_MOD_TIME == null ? null : LT_MOD_TIME.trim();
    }

    public String getLT_MOD_ADMINID() {
        return LT_MOD_ADMINID;
    }

    public void setLT_MOD_ADMINID(String LT_MOD_ADMINID) {
        this.LT_MOD_ADMINID = LT_MOD_ADMINID == null ? null : LT_MOD_ADMINID.trim();
    }

    public String getLT_EXT1() {
        return LT_EXT1;
    }

    public void setLT_EXT1(String LT_EXT1) {
        this.LT_EXT1 = LT_EXT1 == null ? null : LT_EXT1.trim();
    }

    public String getLT_EXT2() {
        return LT_EXT2;
    }

    public void setLT_EXT2(String LT_EXT2) {
        this.LT_EXT2 = LT_EXT2 == null ? null : LT_EXT2.trim();
    }
}