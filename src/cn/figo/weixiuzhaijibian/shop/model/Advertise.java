package cn.figo.weixiuzhaijibian.shop.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 广告实体类
 *
 */
public class Advertise implements Serializable{
	@JsonProperty("ADVERTISE_ID")
    private String ADVERTISE_ID;

	@JsonProperty("AD_NAME")
    private String AD_NAME;
	
	@JsonProperty("AD_IMG")
    private String AD_IMG;

	@JsonProperty("AD_BELONG")
    private Integer AD_BELONG;

	@JsonProperty("AD_TYPE")
    private Integer AD_TYPE;

	@JsonProperty("AD_JUMP_TARGET")
    private String AD_JUMP_TARGET;

	
	@JsonProperty("AD_END_TIME")
    private String AD_END_TIME;

	@JsonProperty("AD_IS_SHOW")
    private Integer AD_IS_SHOW;

	@JsonProperty("AD_SORT")
    private Integer AD_SORT;

	@JsonProperty("AD_ADD_TIME")
    private String AD_ADD_TIME;

	@JsonProperty("AD_MOD_TIME")
    private String AD_MOD_TIME;

	@JsonProperty("AD_MOD_ADMINID")
    private String AD_MOD_ADMINID;

	@JsonProperty("AD_EXT1")
    private String AD_EXT1;

	@JsonProperty("AD_EXT2")
    private String AD_EXT2;

    public String getADVERTISE_ID() {
        return ADVERTISE_ID;
    }

    public void setADVERTISE_ID(String ADVERTISE_ID) {
        this.ADVERTISE_ID = ADVERTISE_ID == null ? null : ADVERTISE_ID.trim();
    }

    public String getAD_NAME() {
        return AD_NAME;
    }

    public void setAD_NAME(String AD_NAME) {
        this.AD_NAME = AD_NAME == null ? null : AD_NAME.trim();
    }

    public String getAD_IMG() {
        return AD_IMG;
    }

    public void setAD_IMG(String AD_IMG) {
        this.AD_IMG = AD_IMG == null ? null : AD_IMG.trim();
    }

    public Integer getAD_BELONG() {
        return AD_BELONG;
    }

    public void setAD_BELONG(Integer AD_BELONG) {
        this.AD_BELONG = AD_BELONG;
    }

    public Integer getAD_TYPE() {
        return AD_TYPE;
    }

    public void setAD_TYPE(Integer AD_TYPE) {
        this.AD_TYPE = AD_TYPE;
    }

    public String getAD_JUMP_TARGET() {
        return AD_JUMP_TARGET;
    }

    public void setAD_JUMP_TARGET(String AD_JUMP_TARGET) {
        this.AD_JUMP_TARGET = AD_JUMP_TARGET == null ? null : AD_JUMP_TARGET.trim();
    }

    public String getAD_END_TIME() {
        return AD_END_TIME;
    }

    public void setAD_END_TIME(String AD_END_TIME) {
        this.AD_END_TIME = AD_END_TIME == null ? null : AD_END_TIME.trim();
    }

    public Integer getAD_IS_SHOW() {
        return AD_IS_SHOW;
    }

    public void setAD_IS_SHOW(Integer AD_IS_SHOW) {
        this.AD_IS_SHOW = AD_IS_SHOW;
    }

    public Integer getAD_SORT() {
        return AD_SORT;
    }

    public void setAD_SORT(Integer AD_SORT) {
        this.AD_SORT = AD_SORT;
    }

    public String getAD_ADD_TIME() {
        return AD_ADD_TIME;
    }

    public void setAD_ADD_TIME(String AD_ADD_TIME) {
        this.AD_ADD_TIME = AD_ADD_TIME == null ? null : AD_ADD_TIME.trim();
    }

    public String getAD_MOD_TIME() {
        return AD_MOD_TIME;
    }

    public void setAD_MOD_TIME(String AD_MOD_TIME) {
        this.AD_MOD_TIME = AD_MOD_TIME == null ? null : AD_MOD_TIME.trim();
    }

    public String getAD_MOD_ADMINID() {
        return AD_MOD_ADMINID;
    }

    public void setAD_MOD_ADMINID(String AD_MOD_ADMINID) {
        this.AD_MOD_ADMINID = AD_MOD_ADMINID == null ? null : AD_MOD_ADMINID.trim();
    }

    public String getAD_EXT1() {
        return AD_EXT1;
    }

    public void setAD_EXT1(String AD_EXT1) {
        this.AD_EXT1 = AD_EXT1 == null ? null : AD_EXT1.trim();
    }

    public String getAD_EXT2() {
        return AD_EXT2;
    }

    public void setAD_EXT2(String AD_EXT2) {
        this.AD_EXT2 = AD_EXT2 == null ? null : AD_EXT2.trim();
    }
}