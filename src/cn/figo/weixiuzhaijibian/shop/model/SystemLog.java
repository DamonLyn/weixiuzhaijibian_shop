package cn.figo.weixiuzhaijibian.shop.model;

public class SystemLog {
    private String SYSTEM_LOG_ID;

    private String SL_TITLE;

    private String SL_CONTENT;

    private Integer SL_TYPE;

    private String SL_ADMINID;

    private String SL_ADMIN_IP;

    private String SL_ADD_TIME;

    private String SL_EXT1;

    private String SL_EXT2;

    public String getSYSTEM_LOG_ID() {
        return SYSTEM_LOG_ID;
    }

    public void setSYSTEM_LOG_ID(String SYSTEM_LOG_ID) {
        this.SYSTEM_LOG_ID = SYSTEM_LOG_ID == null ? null : SYSTEM_LOG_ID.trim();
    }

    public String getSL_TITLE() {
        return SL_TITLE;
    }

    public void setSL_TITLE(String SL_TITLE) {
        this.SL_TITLE = SL_TITLE == null ? null : SL_TITLE.trim();
    }

    public String getSL_CONTENT() {
        return SL_CONTENT;
    }

    public void setSL_CONTENT(String SL_CONTENT) {
        this.SL_CONTENT = SL_CONTENT == null ? null : SL_CONTENT.trim();
    }

    public Integer getSL_TYPE() {
        return SL_TYPE;
    }

    public void setSL_TYPE(Integer SL_TYPE) {
        this.SL_TYPE = SL_TYPE;
    }

    public String getSL_ADMINID() {
        return SL_ADMINID;
    }

    public void setSL_ADMINID(String SL_ADMINID) {
        this.SL_ADMINID = SL_ADMINID == null ? null : SL_ADMINID.trim();
    }

    public String getSL_ADMIN_IP() {
        return SL_ADMIN_IP;
    }

    public void setSL_ADMIN_IP(String SL_ADMIN_IP) {
        this.SL_ADMIN_IP = SL_ADMIN_IP == null ? null : SL_ADMIN_IP.trim();
    }

    public String getSL_ADD_TIME() {
        return SL_ADD_TIME;
    }

    public void setSL_ADD_TIME(String SL_ADD_TIME) {
        this.SL_ADD_TIME = SL_ADD_TIME == null ? null : SL_ADD_TIME.trim();
    }

    public String getSL_EXT1() {
        return SL_EXT1;
    }

    public void setSL_EXT1(String SL_EXT1) {
        this.SL_EXT1 = SL_EXT1 == null ? null : SL_EXT1.trim();
    }

    public String getSL_EXT2() {
        return SL_EXT2;
    }

    public void setSL_EXT2(String SL_EXT2) {
        this.SL_EXT2 = SL_EXT2 == null ? null : SL_EXT2.trim();
    }
}