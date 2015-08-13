package cn.figo.weixiuzhaijibian.shop.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MasterResponse extends BaseResponse{
	
	@JsonProperty("data")
	private  Master data;
	
	@JsonProperty("areas")
	private List<Area> areaList;
	
	@JsonProperty("licenses")
	private List<MasterLicense>  masterLicenseList;
	public Master getData() {
		return data;
	}

	public void setData(Master data) {
		this.data = data;
	}

	public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	public List<MasterLicense> getMasterLicenseList() {
		return masterLicenseList;
	}

	public void setMasterLicenseList(List<MasterLicense> masterLicenseList) {
		this.masterLicenseList = masterLicenseList;
	}


	
}
