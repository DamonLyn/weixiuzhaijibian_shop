package cn.figo.weixiuzhaijibian.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Master implements Serializable{
	@JsonProperty("MASTER_ID")
	private String MASTER_ID;

	@JsonProperty("m_LOGIN_MAIL")
    private String m_LOGIN_MAIL;

	@JsonProperty("m_PWD")
    private String m_PWD;

	@JsonProperty("m_NAME")
    private String m_NAME;

	@JsonProperty("m_TEL")
    private String m_TEL;

	@JsonProperty("m_SEX")
    private Integer m_SEX;

	@JsonProperty("m_AGE")
    private Integer m_AGE;

	@JsonProperty("m_SCORE")
    private BigDecimal m_SCORE;

	@JsonProperty("m_ATTITUDE_SCORE")
    private BigDecimal m_ATTITUDE_SCORE;

	@JsonProperty("m_PRICE_SCORE")
    private BigDecimal m_PRICE_SCORE;

	@JsonProperty("m_ZAN")
    private Integer m_ZAN;

	@JsonProperty("m_WOK_YEAR")
    private Integer m_WOK_YEAR;

	@JsonProperty("m_HEAD_IMG")
    private String m_HEAD_IMG;

	@JsonProperty("m_INTRO")
    private String m_INTRO;

	@JsonProperty("m_REGIST_TIME")
    private String m_REGIST_TIME;

	@JsonProperty("m_LASTLOGIN_TIME")
    private String m_LASTLOGIN_TIME;

	@JsonProperty("MOD_TIME")
    private String MOD_TIME;

	@JsonProperty("m_AUDIT_STATUS")
    private Integer m_AUDIT_STATUS;

	@JsonProperty("m_AUDIT_NOTE")
    private String m_AUDIT_NOTE;

	@JsonProperty("m_AUDIT_TIME")
    private String m_AUDIT_TIME;

	@JsonProperty("m_EXT1")
    private String m_EXT1;

	@JsonProperty("m_EXT2")
    private String m_EXT2;

	public String getMASTER_ID() {
		return MASTER_ID;
	}

	public void setMASTER_ID(String mASTER_ID) {
		MASTER_ID = mASTER_ID;
	}

	public String getM_LOGIN_MAIL() {
		return m_LOGIN_MAIL;
	}

	public void setM_LOGIN_MAIL(String m_LOGIN_MAIL) {
		this.m_LOGIN_MAIL = m_LOGIN_MAIL;
	}

	public String getM_PWD() {
		return m_PWD;
	}

	public void setM_PWD(String m_PWD) {
		this.m_PWD = m_PWD;
	}

	public String getM_NAME() {
		return m_NAME;
	}

	public void setM_NAME(String m_NAME) {
		this.m_NAME = m_NAME;
	}

	public String getM_TEL() {
		return m_TEL;
	}

	public void setM_TEL(String m_TEL) {
		this.m_TEL = m_TEL;
	}

	public Integer getM_SEX() {
		return m_SEX;
	}

	public void setM_SEX(Integer m_SEX) {
		this.m_SEX = m_SEX;
	}

	public Integer getM_AGE() {
		return m_AGE;
	}

	public void setM_AGE(Integer m_AGE) {
		this.m_AGE = m_AGE;
	}



	public BigDecimal getM_SCORE() {
		return m_SCORE;
	}

	public void setM_SCORE(BigDecimal m_SCORE) {
		this.m_SCORE = m_SCORE;
	}


	public BigDecimal getM_ATTITUDE_SCORE() {
		return m_ATTITUDE_SCORE;
	}

	public void setM_ATTITUDE_SCORE(BigDecimal m_ATTITUDE_SCORE) {
		this.m_ATTITUDE_SCORE = m_ATTITUDE_SCORE;
	}

	
	public BigDecimal getM_PRICE_SCORE() {
		return m_PRICE_SCORE;
	}

	public void setM_PRICE_SCORE(BigDecimal m_PRICE_SCORE) {
		this.m_PRICE_SCORE = m_PRICE_SCORE;
	}

	public Integer getM_ZAN() {
		return m_ZAN;
	}

	public void setM_ZAN(Integer m_ZAN) {
		this.m_ZAN = m_ZAN;
	}

	public Integer getM_WOK_YEAR() {
		return m_WOK_YEAR;
	}

	public void setM_WOK_YEAR(Integer m_WOK_YEAR) {
		this.m_WOK_YEAR = m_WOK_YEAR;
	}

	public String getM_HEAD_IMG() {
		return m_HEAD_IMG;
	}

	public void setM_HEAD_IMG(String m_HEAD_IMG) {
		this.m_HEAD_IMG = m_HEAD_IMG;
	}

	public String getM_INTRO() {
		return m_INTRO;
	}

	public void setM_INTRO(String m_INTRO) {
		this.m_INTRO = m_INTRO;
	}

	public String getM_REGIST_TIME() {
		return m_REGIST_TIME;
	}

	public void setM_REGIST_TIME(String m_REGIST_TIME) {
		this.m_REGIST_TIME = m_REGIST_TIME;
	}

	public String getM_LASTLOGIN_TIME() {
		return m_LASTLOGIN_TIME;
	}

	public void setM_LASTLOGIN_TIME(String m_LASTLOGIN_TIME) {
		this.m_LASTLOGIN_TIME = m_LASTLOGIN_TIME;
	}

	public String getMOD_TIME() {
		return MOD_TIME;
	}

	public void setMOD_TIME(String mOD_TIME) {
		MOD_TIME = mOD_TIME;
	}

	public Integer getM_AUDIT_STATUS() {
		return m_AUDIT_STATUS;
	}

	public void setM_AUDIT_STATUS(Integer m_AUDIT_STATUS) {
		this.m_AUDIT_STATUS = m_AUDIT_STATUS;
	}

	public String getM_AUDIT_NOTE() {
		return m_AUDIT_NOTE;
	}

	public void setM_AUDIT_NOTE(String m_AUDIT_NOTE) {
		this.m_AUDIT_NOTE = m_AUDIT_NOTE;
	}

	public String getM_AUDIT_TIME() {
		return m_AUDIT_TIME;
	}

	public void setM_AUDIT_TIME(String m_AUDIT_TIME) {
		this.m_AUDIT_TIME = m_AUDIT_TIME;
	}

	public String getM_EXT1() {
		return m_EXT1;
	}

	public void setM_EXT1(String m_EXT1) {
		this.m_EXT1 = m_EXT1;
	}

	public String getM_EXT2() {
		return m_EXT2;
	}

	public void setM_EXT2(String m_EXT2) {
		this.m_EXT2 = m_EXT2;
	}
}