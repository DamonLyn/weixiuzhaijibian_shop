package cn.figo.weixiuzhaijibian.shop.model;

import java.math.BigDecimal;

public class OrderAppraise {
    private String ORDER_APPRAISE_ID;

    private String o_ID;

    private String m_ID;

    private BigDecimal OA_ATTITUDE_SCORE;

    private BigDecimal OA_PRICE_SCORE;

    private Boolean OA_ZAN;

    private Boolean OA_IS_ADDED;

    private String OA_ADD_TIME;

    private String OA_EXT1;

    private String OA_EXT2;

    public String getORDER_APPRAISE_ID() {
        return ORDER_APPRAISE_ID;
    }

    public void setORDER_APPRAISE_ID(String ORDER_APPRAISE_ID) {
        this.ORDER_APPRAISE_ID = ORDER_APPRAISE_ID == null ? null : ORDER_APPRAISE_ID.trim();
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

    public BigDecimal getOA_ATTITUDE_SCORE() {
        return OA_ATTITUDE_SCORE;
    }

    public void setOA_ATTITUDE_SCORE(BigDecimal OA_ATTITUDE_SCORE) {
        this.OA_ATTITUDE_SCORE = OA_ATTITUDE_SCORE;
    }

    public BigDecimal getOA_PRICE_SCORE() {
        return OA_PRICE_SCORE;
    }

    public void setOA_PRICE_SCORE(BigDecimal OA_PRICE_SCORE) {
        this.OA_PRICE_SCORE = OA_PRICE_SCORE;
    }

    public Boolean getOA_ZAN() {
        return OA_ZAN;
    }

    public void setOA_ZAN(Boolean OA_ZAN) {
        this.OA_ZAN = OA_ZAN;
    }

    public Boolean getOA_IS_ADDED() {
        return OA_IS_ADDED;
    }

    public void setOA_IS_ADDED(Boolean OA_IS_ADDED) {
        this.OA_IS_ADDED = OA_IS_ADDED;
    }

    public String getOA_ADD_TIME() {
        return OA_ADD_TIME;
    }

    public void setOA_ADD_TIME(String OA_ADD_TIME) {
        this.OA_ADD_TIME = OA_ADD_TIME == null ? null : OA_ADD_TIME.trim();
    }

    public String getOA_EXT1() {
        return OA_EXT1;
    }

    public void setOA_EXT1(String OA_EXT1) {
        this.OA_EXT1 = OA_EXT1 == null ? null : OA_EXT1.trim();
    }

    public String getOA_EXT2() {
        return OA_EXT2;
    }

    public void setOA_EXT2(String OA_EXT2) {
        this.OA_EXT2 = OA_EXT2 == null ? null : OA_EXT2.trim();
    }
}