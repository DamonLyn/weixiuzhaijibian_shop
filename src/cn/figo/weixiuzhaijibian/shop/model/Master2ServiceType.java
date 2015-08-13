package cn.figo.weixiuzhaijibian.shop.model;

public class Master2ServiceType {
    private String MASTER_2SERVICETYPE_ID;

    private String m_ID;

    private String ST_ID;

    private String MST_ADD_TIME;

    private String MST_EXT1;

    private String MST_EXT2;

    public String getMASTER_2SERVICETYPE_ID() {
        return MASTER_2SERVICETYPE_ID;
    }

    public void setMASTER_2SERVICETYPE_ID(String MASTER_2SERVICETYPE_ID) {
        this.MASTER_2SERVICETYPE_ID = MASTER_2SERVICETYPE_ID == null ? null : MASTER_2SERVICETYPE_ID.trim();
    }

    public String getM_ID() {
        return m_ID;
    }

    public void setM_ID(String m_ID) {
        this.m_ID = m_ID == null ? null : m_ID.trim();
    }

    public String getST_ID() {
        return ST_ID;
    }

    public void setST_ID(String ST_ID) {
        this.ST_ID = ST_ID == null ? null : ST_ID.trim();
    }

    public String getMST_ADD_TIME() {
        return MST_ADD_TIME;
    }

    public void setMST_ADD_TIME(String MST_ADD_TIME) {
        this.MST_ADD_TIME = MST_ADD_TIME == null ? null : MST_ADD_TIME.trim();
    }

    public String getMST_EXT1() {
        return MST_EXT1;
    }

    public void setMST_EXT1(String MST_EXT1) {
        this.MST_EXT1 = MST_EXT1 == null ? null : MST_EXT1.trim();
    }

    public String getMST_EXT2() {
        return MST_EXT2;
    }

    public void setMST_EXT2(String MST_EXT2) {
        this.MST_EXT2 = MST_EXT2 == null ? null : MST_EXT2.trim();
    }
}