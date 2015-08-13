package cn.figo.weixiuzhaijibian.shop.model;

public class MailCertification {
    private String MAIL_CERTIFICATION_ID;

    private String MC_MAIL;

    private String MC_BELONG;

    private String MC_CODE;

    private String MC_SEND_TIME;

    private Byte MC_TIME;

    private Boolean MC_BEGLONG;

    private Boolean MC_SEND_STATUS;

    private Boolean MC_CODE_STATUS;

    private String MC_CODE_TIME;

    private String MC_EXT1;

    private String MC_EXT2;

    public String getMAIL_CERTIFICATION_ID() {
        return MAIL_CERTIFICATION_ID;
    }

    public void setMAIL_CERTIFICATION_ID(String MAIL_CERTIFICATION_ID) {
        this.MAIL_CERTIFICATION_ID = MAIL_CERTIFICATION_ID == null ? null : MAIL_CERTIFICATION_ID.trim();
    }

    public String getMC_MAIL() {
        return MC_MAIL;
    }

    public void setMC_MAIL(String MC_MAIL) {
        this.MC_MAIL = MC_MAIL == null ? null : MC_MAIL.trim();
    }

    public String getMC_BELONG() {
        return MC_BELONG;
    }

    public void setMC_BELONG(String MC_BELONG) {
        this.MC_BELONG = MC_BELONG == null ? null : MC_BELONG.trim();
    }

    public String getMC_CODE() {
        return MC_CODE;
    }

    public void setMC_CODE(String MC_CODE) {
        this.MC_CODE = MC_CODE == null ? null : MC_CODE.trim();
    }

    public String getMC_SEND_TIME() {
        return MC_SEND_TIME;
    }

    public void setMC_SEND_TIME(String MC_SEND_TIME) {
        this.MC_SEND_TIME = MC_SEND_TIME == null ? null : MC_SEND_TIME.trim();
    }

    public Byte getMC_TIME() {
        return MC_TIME;
    }

    public void setMC_TIME(Byte MC_TIME) {
        this.MC_TIME = MC_TIME;
    }

    public Boolean getMC_BEGLONG() {
        return MC_BEGLONG;
    }

    public void setMC_BEGLONG(Boolean MC_BEGLONG) {
        this.MC_BEGLONG = MC_BEGLONG;
    }

    public Boolean getMC_SEND_STATUS() {
        return MC_SEND_STATUS;
    }

    public void setMC_SEND_STATUS(Boolean MC_SEND_STATUS) {
        this.MC_SEND_STATUS = MC_SEND_STATUS;
    }

    public Boolean getMC_CODE_STATUS() {
        return MC_CODE_STATUS;
    }

    public void setMC_CODE_STATUS(Boolean MC_CODE_STATUS) {
        this.MC_CODE_STATUS = MC_CODE_STATUS;
    }

    public String getMC_CODE_TIME() {
        return MC_CODE_TIME;
    }

    public void setMC_CODE_TIME(String MC_CODE_TIME) {
        this.MC_CODE_TIME = MC_CODE_TIME == null ? null : MC_CODE_TIME.trim();
    }

    public String getMC_EXT1() {
        return MC_EXT1;
    }

    public void setMC_EXT1(String MC_EXT1) {
        this.MC_EXT1 = MC_EXT1 == null ? null : MC_EXT1.trim();
    }

    public String getMC_EXT2() {
        return MC_EXT2;
    }

    public void setMC_EXT2(String MC_EXT2) {
        this.MC_EXT2 = MC_EXT2 == null ? null : MC_EXT2.trim();
    }
}