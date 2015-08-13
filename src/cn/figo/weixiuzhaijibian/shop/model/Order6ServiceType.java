package cn.figo.weixiuzhaijibian.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order6ServiceType implements Serializable{
	@JsonProperty("ORDER_ID")
    private String ORDER_ID;

	@JsonProperty("o_NO")
    private String o_NO;

	@JsonProperty("ST_ID")
    private String ST_ID;

	@JsonProperty("ST_NAME")
    private String ST_NAME;

	@JsonProperty("u_ID")
    private String u_ID;

	@JsonProperty("u_LOGIN_MAIL")
    private String u_LOGIN_MAIL;

	@JsonProperty("o_USER_NAME")
    private String o_USER_NAME;

	@JsonProperty("o_USER_TEL")
    private String o_USER_TEL;

	@JsonProperty("AREA_ID")
    private String AREA_ID;

	@JsonProperty("AREA_NAME")
    private String AREA_NAME;

	@JsonProperty("o_ADDRESS")
    private String o_ADDRESS;

	@JsonProperty("o_REPAIR_CONTENT")
    private String o_REPAIR_CONTENT;

	@JsonProperty("o_TIME_START")
    private String o_TIME_START;

	@JsonProperty("o_TIME_END")
    private String o_TIME_END;

	@JsonProperty("o_SCENE_IMG")
    private String o_SCENE_IMG;

	@JsonProperty("o_VOICE")
    private String o_VOICE;

	@JsonProperty("o_STATUS")
    private Integer o_STATUS;

	@JsonProperty("o_ADD_TIME")
    private String o_ADD_TIME;

	@JsonProperty("o_MOD_TIME")
    private String o_MOD_TIME;

	@JsonProperty("o_EXT1")
    private String o_EXT1;

	@JsonProperty("o_EXT2")
    private String o_EXT2;

	@JsonProperty("MP_ID")
    private String MP_ID;

	@JsonProperty("m_ID")
    private String m_ID;

	@JsonProperty("o_USER_COMFIRM_TIME")
    private String o_USER_COMFIRM_TIME;

	@JsonProperty("o_MASTER_REPAIR_CONTENT")
    private String o_MASTER_REPAIR_CONTENT;

	@JsonProperty("o_FINAL_PRICE")
    private BigDecimal o_FINAL_PRICE;

	@JsonProperty("o_MAINTENANCE_CYCLE")
    private String o_MAINTENANCE_CYCLE;

	@JsonProperty("o_REPAIRED_IMG")
    private String o_REPAIRED_IMG;

	@JsonProperty("o_MASTER_COMFIRM_TIME")
    private String o_MASTER_COMFIRM_TIME;

	@JsonProperty("serviceType")
    private ServiceType serviceType;
	
	@JsonProperty("masterPrice")
	private MasterPrice masterPrice;
    
	
	
    public String getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID == null ? null : ORDER_ID.trim();
    }

    public String getO_NO() {
        return o_NO;
    }

    public void setO_NO(String o_NO) {
        this.o_NO = o_NO == null ? null : o_NO.trim();
    }

    public String getST_ID() {
        return ST_ID;
    }

    public void setST_ID(String ST_ID) {
        this.ST_ID = ST_ID == null ? null : ST_ID.trim();
    }

    public String getST_NAME() {
        return ST_NAME;
    }

    public void setST_NAME(String ST_NAME) {
        this.ST_NAME = ST_NAME == null ? null : ST_NAME.trim();
    }

    public String getU_ID() {
        return u_ID;
    }

    public void setU_ID(String u_ID) {
        this.u_ID = u_ID == null ? null : u_ID.trim();
    }

    public String getU_LOGIN_MAIL() {
        return u_LOGIN_MAIL;
    }

    public void setU_LOGIN_MAIL(String u_LOGIN_MAIL) {
        this.u_LOGIN_MAIL = u_LOGIN_MAIL == null ? null : u_LOGIN_MAIL.trim();
    }

    public String getO_USER_NAME() {
        return o_USER_NAME;
    }

    public void setO_USER_NAME(String o_USER_NAME) {
        this.o_USER_NAME = o_USER_NAME == null ? null : o_USER_NAME.trim();
    }

    public String getO_USER_TEL() {
        return o_USER_TEL;
    }

    public void setO_USER_TEL(String o_USER_TEL) {
        this.o_USER_TEL = o_USER_TEL == null ? null : o_USER_TEL.trim();
    }

    public String getAREA_ID() {
        return AREA_ID;
    }

    public void setAREA_ID(String AREA_ID) {
        this.AREA_ID = AREA_ID == null ? null : AREA_ID.trim();
    }

    public String getAREA_NAME() {
        return AREA_NAME;
    }

    public void setAREA_NAME(String AREA_NAME) {
        this.AREA_NAME = AREA_NAME == null ? null : AREA_NAME.trim();
    }

    public String getO_ADDRESS() {
        return o_ADDRESS;
    }

    public void setO_ADDRESS(String o_ADDRESS) {
        this.o_ADDRESS = o_ADDRESS == null ? null : o_ADDRESS.trim();
    }

    public String getO_REPAIR_CONTENT() {
        return o_REPAIR_CONTENT;
    }

    public void setO_REPAIR_CONTENT(String o_REPAIR_CONTENT) {
        this.o_REPAIR_CONTENT = o_REPAIR_CONTENT == null ? null : o_REPAIR_CONTENT.trim();
    }

    public String getO_TIME_START() {
        return o_TIME_START;
    }

    public void setO_TIME_START(String o_TIME_START) {
        this.o_TIME_START = o_TIME_START == null ? null : o_TIME_START.trim();
    }

    public String getO_TIME_END() {
        return o_TIME_END;
    }

    public void setO_TIME_END(String o_TIME_END) {
        this.o_TIME_END = o_TIME_END == null ? null : o_TIME_END.trim();
    }

    public String getO_SCENE_IMG() {
        return o_SCENE_IMG;
    }

    public void setO_SCENE_IMG(String o_SCENE_IMG) {
        this.o_SCENE_IMG = o_SCENE_IMG == null ? null : o_SCENE_IMG.trim();
    }

    public String getO_VOICE() {
        return o_VOICE;
    }

    public void setO_VOICE(String o_VOICE) {
        this.o_VOICE = o_VOICE == null ? null : o_VOICE.trim();
    }

    public Integer getO_STATUS() {
        return o_STATUS;
    }

    public void setO_STATUS(Integer o_STATUS) {
        this.o_STATUS = o_STATUS;
    }

    public String getO_ADD_TIME() {
        return o_ADD_TIME;
    }

    public void setO_ADD_TIME(String o_ADD_TIME) {
        this.o_ADD_TIME = o_ADD_TIME == null ? null : o_ADD_TIME.trim();
    }

    public String getO_MOD_TIME() {
        return o_MOD_TIME;
    }

    public void setO_MOD_TIME(String o_MOD_TIME) {
        this.o_MOD_TIME = o_MOD_TIME == null ? null : o_MOD_TIME.trim();
    }

    public String getO_EXT1() {
        return o_EXT1;
    }

    public void setO_EXT1(String o_EXT1) {
        this.o_EXT1 = o_EXT1 == null ? null : o_EXT1.trim();
    }

    public String getO_EXT2() {
        return o_EXT2;
    }

    public void setO_EXT2(String o_EXT2) {
        this.o_EXT2 = o_EXT2 == null ? null : o_EXT2.trim();
    }

    public String getMP_ID() {
        return MP_ID;
    }

    public void setMP_ID(String MP_ID) {
        this.MP_ID = MP_ID == null ? null : MP_ID.trim();
    }

    public String getM_ID() {
        return m_ID;
    }

    public void setM_ID(String m_ID) {
        this.m_ID = m_ID == null ? null : m_ID.trim();
    }

    public String getO_USER_COMFIRM_TIME() {
        return o_USER_COMFIRM_TIME;
    }

    public void setO_USER_COMFIRM_TIME(String o_USER_COMFIRM_TIME) {
        this.o_USER_COMFIRM_TIME = o_USER_COMFIRM_TIME == null ? null : o_USER_COMFIRM_TIME.trim();
    }

    public String getO_MASTER_REPAIR_CONTENT() {
        return o_MASTER_REPAIR_CONTENT;
    }

    public void setO_MASTER_REPAIR_CONTENT(String o_MASTER_REPAIR_CONTENT) {
        this.o_MASTER_REPAIR_CONTENT = o_MASTER_REPAIR_CONTENT == null ? null : o_MASTER_REPAIR_CONTENT.trim();
    }

    public BigDecimal getO_FINAL_PRICE() {
        return o_FINAL_PRICE;
    }

    public void setO_FINAL_PRICE(BigDecimal o_FINAL_PRICE) {
        this.o_FINAL_PRICE = o_FINAL_PRICE;
    }



    public String getO_MAINTENANCE_CYCLE() {
		return o_MAINTENANCE_CYCLE;
	}

	public void setO_MAINTENANCE_CYCLE(String o_MAINTENANCE_CYCLE) {
		this.o_MAINTENANCE_CYCLE = o_MAINTENANCE_CYCLE;
	}

	public String getO_REPAIRED_IMG() {
        return o_REPAIRED_IMG;
    }

    public void setO_REPAIRED_IMG(String o_REPAIRED_IMG) {
        this.o_REPAIRED_IMG = o_REPAIRED_IMG == null ? null : o_REPAIRED_IMG.trim();
    }

    public String getO_MASTER_COMFIRM_TIME() {
        return o_MASTER_COMFIRM_TIME;
    }

    public void setO_MASTER_COMFIRM_TIME(String o_MASTER_COMFIRM_TIME) {
        this.o_MASTER_COMFIRM_TIME = o_MASTER_COMFIRM_TIME == null ? null : o_MASTER_COMFIRM_TIME.trim();
    }

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public MasterPrice getMasterPrice() {
		return masterPrice;
	}

	public void setMasterPrice(MasterPrice masterPrice) {
		this.masterPrice = masterPrice;
	}
    
	
	
}