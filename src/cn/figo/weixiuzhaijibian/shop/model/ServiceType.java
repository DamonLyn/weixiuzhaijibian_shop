package cn.figo.weixiuzhaijibian.shop.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceType implements Serializable{
	
	@JsonProperty("SERVICE_TYPE_ID")
    private String SERVICE_TYPE_ID;

	@JsonProperty("ST_NAME")
    private String ST_NAME;

	@JsonProperty("ST_CODE")
    private String ST_CODE;

	@JsonProperty("ST_IMG_LOW")
    private String ST_IMG_LOW;

	@JsonProperty("ST_IMG_MIDLE")
    private String ST_IMG_MIDLE;

	@JsonProperty("ST_IMG_HEIGHT")
    private String ST_IMG_HEIGHT;

	@JsonProperty("ST_ICON_COLOR")
    private String ST_ICON_COLOR;

	@JsonProperty("ST_ICON_GRAY")
    private String ST_ICON_GRAY;

	@JsonProperty("ST_IS_SHOW")
    private Integer ST_IS_SHOW;

	@JsonProperty("ST_SORT")
    private Integer ST_SORT;

	@JsonProperty("ST_ADD_TIME")
    private String ST_ADD_TIME;

	@JsonProperty("ST_MOD_TIME")
    private String ST_MOD_TIME;

	@JsonProperty("ST_MOD_ADMINID")
    private String ST_MOD_ADMINID;

	@JsonProperty("ST_EXT1")
    private String ST_EXT1;

	@JsonProperty("ST_EXT2")
    private String ST_EXT2;

    public String getSERVICE_TYPE_ID() {
        return SERVICE_TYPE_ID;
    }

    public void setSERVICE_TYPE_ID(String SERVICE_TYPE_ID) {
        this.SERVICE_TYPE_ID = SERVICE_TYPE_ID == null ? null : SERVICE_TYPE_ID.trim();
    }

    public String getST_NAME() {
        return ST_NAME;
    }

    public void setST_NAME(String ST_NAME) {
        this.ST_NAME = ST_NAME == null ? null : ST_NAME.trim();
    }

    public String getST_CODE() {
        return ST_CODE;
    }

    public void setST_CODE(String ST_CODE) {
        this.ST_CODE = ST_CODE == null ? null : ST_CODE.trim();
    }

    public String getST_IMG_LOW() {
        return ST_IMG_LOW;
    }

    public void setST_IMG_LOW(String ST_IMG_LOW) {
        this.ST_IMG_LOW = ST_IMG_LOW == null ? null : ST_IMG_LOW.trim();
    }

    public String getST_IMG_MIDLE() {
        return ST_IMG_MIDLE;
    }

    public void setST_IMG_MIDLE(String ST_IMG_MIDLE) {
        this.ST_IMG_MIDLE = ST_IMG_MIDLE == null ? null : ST_IMG_MIDLE.trim();
    }

    public String getST_IMG_HEIGHT() {
        return ST_IMG_HEIGHT;
    }

    public void setST_IMG_HEIGHT(String ST_IMG_HEIGHT) {
        this.ST_IMG_HEIGHT = ST_IMG_HEIGHT == null ? null : ST_IMG_HEIGHT.trim();
    }

    public String getST_ICON_COLOR() {
        return ST_ICON_COLOR;
    }

    public void setST_ICON_COLOR(String ST_ICON_COLOR) {
        this.ST_ICON_COLOR = ST_ICON_COLOR == null ? null : ST_ICON_COLOR.trim();
    }

    public String getST_ICON_GRAY() {
        return ST_ICON_GRAY;
    }

    public void setST_ICON_GRAY(String ST_ICON_GRAY) {
        this.ST_ICON_GRAY = ST_ICON_GRAY == null ? null : ST_ICON_GRAY.trim();
    }


    public Integer getST_IS_SHOW() {
		return ST_IS_SHOW;
	}

	public void setST_IS_SHOW(Integer sT_IS_SHOW) {
		ST_IS_SHOW = sT_IS_SHOW;
	}

	public Integer getST_SORT() {
		return ST_SORT;
	}

	public void setST_SORT(Integer sT_SORT) {
		ST_SORT = sT_SORT;
	}

	public String getST_ADD_TIME() {
        return ST_ADD_TIME;
    }

    public void setST_ADD_TIME(String ST_ADD_TIME) {
        this.ST_ADD_TIME = ST_ADD_TIME == null ? null : ST_ADD_TIME.trim();
    }

    public String getST_MOD_TIME() {
        return ST_MOD_TIME;
    }

    public void setST_MOD_TIME(String ST_MOD_TIME) {
        this.ST_MOD_TIME = ST_MOD_TIME == null ? null : ST_MOD_TIME.trim();
    }

    public String getST_MOD_ADMINID() {
        return ST_MOD_ADMINID;
    }

    public void setST_MOD_ADMINID(String ST_MOD_ADMINID) {
        this.ST_MOD_ADMINID = ST_MOD_ADMINID == null ? null : ST_MOD_ADMINID.trim();
    }

    public String getST_EXT1() {
        return ST_EXT1;
    }

    public void setST_EXT1(String ST_EXT1) {
        this.ST_EXT1 = ST_EXT1 == null ? null : ST_EXT1.trim();
    }

    public String getST_EXT2() {
        return ST_EXT2;
    }

    public void setST_EXT2(String ST_EXT2) {
        this.ST_EXT2 = ST_EXT2 == null ? null : ST_EXT2.trim();
    }
}