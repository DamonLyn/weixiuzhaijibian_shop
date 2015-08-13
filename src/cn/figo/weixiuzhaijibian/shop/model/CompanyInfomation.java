package cn.figo.weixiuzhaijibian.shop.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公司信息实体类
 *
 */
public class CompanyInfomation implements Serializable {
	@JsonProperty("CI_COMPANY_INTRO")
	private String CI_COMPANY_INTRO;
	
	@JsonProperty("CI_SERVICE_TERMS")
	private String CI_SERVICE_TERMS;
	
	@JsonProperty("CI_COMPANY_ADDR")
	private String CI_COMPANY_ADDR;
	
	@JsonProperty("CI_COMPANY_POSTCODE")
	private String CI_COMPANY_POSTCODE;	
	
	@JsonProperty("CI_COMPANY_TEL")
	private String CI_COMPANY_TEL;
	
	@JsonProperty("CI_COMPANY_MAIL")
	private String CI_COMPANY_MAIL;
	
	@JsonProperty("CI_COMPANY_WORK_TIME")
	private String CI_COMPANY_WORK_TIME;
	
	@JsonProperty("CI_ADD_TIME")
	private String CI_ADD_TIME;
	
	@JsonProperty("CI_MOD_TIME")
	private String CI_MOD_TIME;
	
	@JsonProperty("CI_MOD_ADMINID")
	private String CI_MOD_ADMINID;
	
	@JsonProperty("COMPANY_INFO_ID")
	private String COMPANY_INFO_ID;
	
	@JsonProperty("ci_COMPANY_NAME")
	private String ci_COMPANY_NAME;
	
	public String getCI_COMPANY_INTRO() {
		return CI_COMPANY_INTRO;
	}
	public void setCI_COMPANY_INTRO(String cI_COMPANY_INTRO) {
		CI_COMPANY_INTRO = cI_COMPANY_INTRO;
	}
	public String getCI_SERVICE_TERMS() {
		return CI_SERVICE_TERMS;
	}
	public void setCI_SERVICE_TERMS(String cI_SERVICE_TERMS) {
		CI_SERVICE_TERMS = cI_SERVICE_TERMS;
	}
	public String getCI_COMPANY_ADDR() {
		return CI_COMPANY_ADDR;
	}
	public void setCI_COMPANY_ADDR(String cI_COMPANY_ADDR) {
		CI_COMPANY_ADDR = cI_COMPANY_ADDR;
	}
	public String getCI_COMPANY_POSTCODE() {
		return CI_COMPANY_POSTCODE;
	}
	public void setCI_COMPANY_POSTCODE(String cI_COMPANY_POSTCODE) {
		CI_COMPANY_POSTCODE = cI_COMPANY_POSTCODE;
	}
	public String getCI_COMPANY_TEL() {
		return CI_COMPANY_TEL;
	}
	public void setCI_COMPANY_TEL(String cI_COMPANY_TEL) {
		CI_COMPANY_TEL = cI_COMPANY_TEL;
	}
	public String getCI_COMPANY_MAIL() {
		return CI_COMPANY_MAIL;
	}
	public void setCI_COMPANY_MAIL(String cI_COMPANY_MAIL) {
		CI_COMPANY_MAIL = cI_COMPANY_MAIL;
	}
	public String getCI_COMPANY_WORK_TIME() {
		return CI_COMPANY_WORK_TIME;
	}
	public void setCI_COMPANY_WORK_TIME(String cI_COMPANY_WORK_TIME) {
		CI_COMPANY_WORK_TIME = cI_COMPANY_WORK_TIME;
	}
	public String getCI_ADD_TIME() {
		return CI_ADD_TIME;
	}
	public void setCI_ADD_TIME(String cI_ADD_TIME) {
		CI_ADD_TIME = cI_ADD_TIME;
	}
	public String getCI_MOD_TIME() {
		return CI_MOD_TIME;
	}
	public void setCI_MOD_TIME(String cI_MOD_TIME) {
		CI_MOD_TIME = cI_MOD_TIME;
	}
	public String getCI_MOD_ADMINID() {
		return CI_MOD_ADMINID;
	}
	public void setCI_MOD_ADMINID(String cI_MOD_ADMINID) {
		CI_MOD_ADMINID = cI_MOD_ADMINID;
	}
	public String getCOMPANY_INFO_ID() {
		return COMPANY_INFO_ID;
	}
	public void setCOMPANY_INFO_ID(String cOMPANY_INFO_ID) {
		COMPANY_INFO_ID = cOMPANY_INFO_ID;
	}
	public String getCi_COMPANY_NAME() {
		return ci_COMPANY_NAME;
	}
	public void setCi_COMPANY_NAME(String ci_COMPANY_NAME) {
		this.ci_COMPANY_NAME = ci_COMPANY_NAME;
	}

	
	
}
