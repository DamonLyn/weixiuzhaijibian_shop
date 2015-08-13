package cn.figo.weixiuzhaijibian.shop.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MasterLicense implements Serializable{
    @JsonProperty("MASTER_LICENSE_ID")
	private String MASTER_LICENSE_ID;

    @JsonProperty("m_ID")
    private String m_ID;

    @JsonProperty("ML_IMG")
    private String ML_IMG;

    @JsonProperty("ML_SORT")
    private Integer ML_SORT;

    @JsonProperty("ML_ADD_TIME")
    private String ML_ADD_TIME;

    @JsonProperty("ML_EXT1")
    private String ML_EXT1;

    @JsonProperty("ML_EXT2")
    private String ML_EXT2;

    public String getMASTER_LICENSE_ID() {
        return MASTER_LICENSE_ID;
    }

    public void setMASTER_LICENSE_ID(String MASTER_LICENSE_ID) {
        this.MASTER_LICENSE_ID = MASTER_LICENSE_ID == null ? null : MASTER_LICENSE_ID.trim();
    }

    public String getM_ID() {
        return m_ID;
    }

    public void setM_ID(String m_ID) {
        this.m_ID = m_ID == null ? null : m_ID.trim();
    }

    public String getML_IMG() {
        return ML_IMG;
    }

    public void setML_IMG(String ML_IMG) {
        this.ML_IMG = ML_IMG == null ? null : ML_IMG.trim();
    }

    public Integer getML_SORT() {
        return ML_SORT;
    }

    public void setML_SORT(Integer ML_SORT) {
        this.ML_SORT = ML_SORT;
    }

    public String getML_ADD_TIME() {
        return ML_ADD_TIME;
    }

    public void setML_ADD_TIME(String ML_ADD_TIME) {
        this.ML_ADD_TIME = ML_ADD_TIME == null ? null : ML_ADD_TIME.trim();
    }

    public String getML_EXT1() {
        return ML_EXT1;
    }

    public void setML_EXT1(String ML_EXT1) {
        this.ML_EXT1 = ML_EXT1 == null ? null : ML_EXT1.trim();
    }

    public String getML_EXT2() {
        return ML_EXT2;
    }

    public void setML_EXT2(String ML_EXT2) {
        this.ML_EXT2 = ML_EXT2 == null ? null : ML_EXT2.trim();
    }
}