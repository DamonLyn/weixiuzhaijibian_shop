package cn.figo.weixiuzhaijibian.shop.model;

public class Master2Area {
    private String MASTER_2AREA_ID;

    private String m_ID;

    private String AREA_ID;

    private String MA_ADD_TIME;

    private String MA_EXT1;

    private String MA_EXT2;

    public String getMASTER_2AREA_ID() {
        return MASTER_2AREA_ID;
    }

    public void setMASTER_2AREA_ID(String MASTER_2AREA_ID) {
        this.MASTER_2AREA_ID = MASTER_2AREA_ID == null ? null : MASTER_2AREA_ID.trim();
    }

    public String getM_ID() {
        return m_ID;
    }

    public void setM_ID(String m_ID) {
        this.m_ID = m_ID == null ? null : m_ID.trim();
    }

    public String getAREA_ID() {
        return AREA_ID;
    }

    public void setAREA_ID(String AREA_ID) {
        this.AREA_ID = AREA_ID == null ? null : AREA_ID.trim();
    }

    public String getMA_ADD_TIME() {
        return MA_ADD_TIME;
    }

    public void setMA_ADD_TIME(String MA_ADD_TIME) {
        this.MA_ADD_TIME = MA_ADD_TIME == null ? null : MA_ADD_TIME.trim();
    }

    public String getMA_EXT1() {
        return MA_EXT1;
    }

    public void setMA_EXT1(String MA_EXT1) {
        this.MA_EXT1 = MA_EXT1 == null ? null : MA_EXT1.trim();
    }

    public String getMA_EXT2() {
        return MA_EXT2;
    }

    public void setMA_EXT2(String MA_EXT2) {
        this.MA_EXT2 = MA_EXT2 == null ? null : MA_EXT2.trim();
    }
}