package easyfarm.domain;

public class ProjectWorkphase {
	private String projectWorkphaseCode;
	private String projectCode;
	private String cropCode;
	private String cropPhaseInfoCode;
	private String regMemberId;
	private String regDate;
	private String useStatus;
	private CropPhaseInfo cropPhaseInfo;
	
	public String getProjectWorkphaseCode() {
		return projectWorkphaseCode;
	}
	public void setProjectWorkphaseCode(String projectWorkphaseCode) {
		this.projectWorkphaseCode = projectWorkphaseCode;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getCropCode() {
		return cropCode;
	}
	public void setCropCode(String cropCode) {
		this.cropCode = cropCode;
	}
	public String getCropPhaseInfoCode() {
		return cropPhaseInfoCode;
	}
	public void setCropPhaseInfoCode(String cropPhaseInfoCode) {
		this.cropPhaseInfoCode = cropPhaseInfoCode;
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
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	public CropPhaseInfo getCropPhaseInfo() {
		return cropPhaseInfo;
	}
	public void setCropPhaseInfo(CropPhaseInfo cropPhaseInfo) {
		this.cropPhaseInfo = cropPhaseInfo;
	}

		
}
