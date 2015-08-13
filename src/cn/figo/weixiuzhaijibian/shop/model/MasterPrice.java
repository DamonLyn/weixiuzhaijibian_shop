package cn.figo.weixiuzhaijibian.shop.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MasterPrice implements Serializable{
	@JsonProperty("MASTER_PRICE_ID")
    private String MASTER_PRICE_ID;

	@JsonProperty("MP_NO")
    private String MP_NO;

	@JsonProperty("o_ID")
    private String o_ID;

	@JsonProperty("m_ID")
    private String m_ID;

	@JsonProperty("MP_CONTENT")
    private String MP_CONTENT;

	@JsonProperty("MP_LOW_PRICE")
    private String MP_LOW_PRICE;

	@JsonProperty("MP_HEIGHT_PRICE")
    private String MP_HEIGHT_PRICE;

	@JsonProperty("MP_SERVICE_TYPE")
    private String MP_SERVICE_TYPE;

	@JsonProperty("MP_ADD_TIME")
    private String MP_ADD_TIME;

	@JsonProperty("MP_MOD_TIME")
    private String MP_MOD_TIME;

	@JsonProperty("MP_EXT1")
    private String MP_EXT1;

	@JsonProperty("MP_EXT2")
    private String MP_EXT2;

    public String getMASTER_PRICE_ID() {
        return MASTER_PRICE_ID;
    }

    public void setMASTER_PRICE_ID(String MASTER_PRICE_ID) {
        this.MASTER_PRICE_ID = MASTER_PRICE_ID == null ? null : MASTER_PRICE_ID.trim();
    }

    public String getMP_NO() {
        return MP_NO;
    }

    public void setMP_NO(String MP_NO) {
        this.MP_NO = MP_NO == null ? null : MP_NO.trim();
    }

    public String getO_ID() {
        return o_ID;
    }

    public void setO_ID(String o_ID) {
        this.o_ID = o_ID == null ? null : o_ID.trim();
    }

    public String getM_ID() {
        return m_ID;
    }

    public void setM_ID(String m_ID) {
        this.m_ID = m_ID == null ? null : m_ID.trim();
    }

    public String getMP_CONTENT() {
        return MP_CONTENT;
    }

    public void setMP_CONTENT(String MP_CONTENT) {
        this.MP_CONTENT = MP_CONTENT == null ? null : MP_CONTENT.trim();
    }

    public String getMP_LOW_PRICE() {
        return MP_LOW_PRICE;
    }

    public void setMP_LOW_PRICE(String MP_LOW_PRICE) {
        this.MP_LOW_PRICE = MP_LOW_PRICE == null ? null : MP_LOW_PRICE.trim();
    }

    public String getMP_HEIGHT_PRICE() {
        return MP_HEIGHT_PRICE;
    }

    public void setMP_HEIGHT_PRICE(String MP_HEIGHT_PRICE) {
        this.MP_HEIGHT_PRICE = MP_HEIGHT_PRICE == null ? null : MP_HEIGHT_PRICE.trim();
    }

    public String getMP_SERVICE_TYPE() {
        return MP_SERVICE_TYPE;
    }

    public void setMP_SERVICE_TYPE(String MP_SERVICE_TYPE) {
        this.MP_SERVICE_TYPE = MP_SERVICE_TYPE == null ? null : MP_SERVICE_TYPE.trim();
    }

    public String getMP_ADD_TIME() {
        return MP_ADD_TIME;
    }

    public void setMP_ADD_TIME(String MP_ADD_TIME) {
        this.MP_ADD_TIME = MP_ADD_TIME == null ? null : MP_ADD_TIME.trim();
    }

    public String getMP_MOD_TIME() {
        return MP_MOD_TIME;
    }

    public void setMP_MOD_TIME(String MP_MOD_TIME) {
        this.MP_MOD_TIME = MP_MOD_TIME == null ? null : MP_MOD_TIME.trim();
    }

    public String getMP_EXT1() {
        return MP_EXT1;
    }

    public void setMP_EXT1(String MP_EXT1) {
        this.MP_EXT1 = MP_EXT1 == null ? null : MP_EXT1.trim();
    }

    public String getMP_EXT2() {
        return MP_EXT2;
    }

    public void setMP_EXT2(String MP_EXT2) {
        this.MP_EXT2 = MP_EXT2 == null ? null : MP_EXT2.trim();
    }
}