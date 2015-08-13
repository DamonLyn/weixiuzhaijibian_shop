package cn.figo.weixiuzhaijibian.shop.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Area implements Serializable{
	@JsonProperty("AREA_ID")
    private String AREA_ID;

	@JsonProperty("AREA_NAME")
    private String AREA_NAME;

	@JsonProperty("AREA_IS_SHOW")
    private Integer AREA_IS_SHOW;

	@JsonProperty("AREA_SORT")
    private Integer AREA_SORT;

	@JsonProperty("AREA_ADD_TIME")
    private String AREA_ADD_TIME;

	@JsonProperty("AREA_MOD_TIME")
    private String AREA_MOD_TIME;

	@JsonProperty("AREA_MOD_ADMINID")
    private String AREA_MOD_ADMINID;

	@JsonProperty("AREA_EXT1")
    private String AREA_EXT1;

	@JsonProperty("AREA_EXT2")
    private String AREA_EXT2;

    public String getAREA_ID() {
        return AREA_ID;
    }

    public void setAREA_ID(String AREA_ID) {
        this.AREA_ID = AREA_ID == null ? null : AREA_ID.trim();
    }

    public String getAREA_NAME() {
        return AREA_NAME;
    }

    public void setAREA_NAME(String AREA_NAME) {
        this.AREA_NAME = AREA_NAME == null ? null : AREA_NAME.trim();
    }

    public Integer getAREA_IS_SHOW() {
        return AREA_IS_SHOW;
    }

    public void setAREA_IS_SHOW(Integer AREA_IS_SHOW) {
        this.AREA_IS_SHOW = AREA_IS_SHOW;
    }

    public Integer getAREA_SORT() {
        return AREA_SORT;
    }

    public void setAREA_SORT(Integer AREA_SORT) {
        this.AREA_SORT = AREA_SORT;
    }

    public String getAREA_ADD_TIME() {
        return AREA_ADD_TIME;
    }

    public void setAREA_ADD_TIME(String AREA_ADD_TIME) {
        this.AREA_ADD_TIME = AREA_ADD_TIME == null ? null : AREA_ADD_TIME.trim();
    }

    public String getAREA_MOD_TIME() {
        return AREA_MOD_TIME;
    }

    public void setAREA_MOD_TIME(String AREA_MOD_TIME) {
        this.AREA_MOD_TIME = AREA_MOD_TIME == null ? null : AREA_MOD_TIME.trim();
    }

    public String getAREA_MOD_ADMINID() {
        return AREA_MOD_ADMINID;
    }

    public void setAREA_MOD_ADMINID(String AREA_MOD_ADMINID) {
        this.AREA_MOD_ADMINID = AREA_MOD_ADMINID == null ? null : AREA_MOD_ADMINID.trim();
    }

    public String getAREA_EXT1() {
        return AREA_EXT1;
    }

    public void setAREA_EXT1(String AREA_EXT1) {
        this.AREA_EXT1 = AREA_EXT1 == null ? null : AREA_EXT1.trim();
    }

    public String getAREA_EXT2() {
        return AREA_EXT2;
    }

    public void setAREA_EXT2(String AREA_EXT2) {
        this.AREA_EXT2 = AREA_EXT2 == null ? null : AREA_EXT2.trim();
    }
}