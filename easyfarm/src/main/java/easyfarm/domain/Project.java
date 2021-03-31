package easyfarm.domain;

import java.util.List;

public class Project {
	private String projectCode;
	private String farmCode;
	private String cropCode;
	private String projectName;
	private String projectArea;
	private String projectBegin;
	private String projectEnd;
	private String projectEstimateIncome;
	private String projectEstimateSpending;
	private String regMemberId;
	private String regDate;
	private Crop crop;
	private Farm farm;
	private List<String> checkWorkphase;
	 
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getFarmCode() {
		return farmCode;
	}
	public void setFarmCode(String farmCode) {
		this.farmCode = farmCode;
	}
	public String getCropCode() {
		return cropCode;
	}
	public void setCropCode(String cropCode) {
		this.cropCode = cropCode;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectArea() {
		return projectArea;
	}
	public void setProjectArea(String projectArea) {
		this.projectArea = projectArea;
	}
	public String getProjectBegin() {
		return projectBegin;
	}
	public void setProjectBegin(String projectBegin) {
		this.projectBegin = projectBegin;
	}
	public String getProjectEnd() {
		return projectEnd;
	}
	public void setProjectEnd(String projectEnd) {
		this.projectEnd = projectEnd;
	}
	public String getProjectEstimateIncome() {
		return projectEstimateIncome;
	}
	public void setProjectEstimateIncome(String projectEstimateIncome) {
		this.projectEstimateIncome = projectEstimateIncome;
	}
	public String getProjectEstimateSpending() {
		return projectEstimateSpending;
	}
	public void setProjectEstimateSpending(String projectEstimateSpending) {
		this.projectEstimateSpending = projectEstimateSpending;
	}
	public String getRegMemberId() {
		return regMemberId;
	}
	public void setRegMemberId(String regMemberId) {
		this.regMemberId = regMemberId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Crop getCrop() {
		return crop;
	}
	public void setCrop(Crop crop) {
		this.crop = crop;
	}
	public Farm getFarm() {
		return farm;
	}
	public void setFarm(Farm farm) {
		this.farm = farm;
	}
	public List<String> getCheckWorkphase() {
		return checkWorkphase;
	}
	public void setCheckWorkphase(List<String> checkWorkphase) {
		this.checkWorkphase = checkWorkphase;
	}

}
