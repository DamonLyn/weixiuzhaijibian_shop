package cn.figo.weixiuzhaijibian.shop.model;

public class ReadSysMsg {
    private String READ_SYSMSG_ID;

    private String SM_ID;

    private String RSM_USER_ID;

    private Boolean RSM_USER_TYPE;

    private String RSM_ADD_TIME;

    private String RSM_EXT1;

    private String RSM_EXT2;

    public String getREAD_SYSMSG_ID() {
        return READ_SYSMSG_ID;
    }

    public void setREAD_SYSMSG_ID(String READ_SYSMSG_ID) {
        this.READ_SYSMSG_ID = READ_SYSMSG_ID == null ? null : READ_SYSMSG_ID.trim();
    }

    public String getSM_ID() {
        return SM_ID;
    }

    public void setSM_ID(String SM_ID) {
        this.SM_ID = SM_ID == null ? null : SM_ID.trim();
    }

    public String getRSM_USER_ID() {
        return RSM_USER_ID;
    }

    public void setRSM_USER_ID(String RSM_USER_ID) {
        this.RSM_USER_ID = RSM_USER_ID == null ? null : RSM_USER_ID.trim();
    }

    public Boolean getRSM_USER_TYPE() {
        return RSM_USER_TYPE;
    }

    public void setRSM_USER_TYPE(Boolean RSM_USER_TYPE) {
        this.RSM_USER_TYPE = RSM_USER_TYPE;
    }

    public String getRSM_ADD_TIME() {
        return RSM_ADD_TIME;
    }

    public void setRSM_ADD_TIME(String RSM_ADD_TIME) {
        this.RSM_ADD_TIME = RSM_ADD_TIME == null ? null : RSM_ADD_TIME.trim();
    }

    public String getRSM_EXT1() {
        return RSM_EXT1;
    }

    public void setRSM_EXT1(String RSM_EXT1) {
        this.RSM_EXT1 = RSM_EXT1 == null ? null : RSM_EXT1.trim();
    }

    public String getRSM_EXT2() {
        return RSM_EXT2;
    }

    public void setRSM_EXT2(String RSM_EXT2) {
        this.RSM_EXT2 = RSM_EXT2 == null ? null : RSM_EXT2.trim();
    }
}