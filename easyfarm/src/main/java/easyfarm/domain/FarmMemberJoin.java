package easyfarm.domain;

public class FarmMemberJoin {
	private String farmJoinCode;
	private String farmCode;
	private String farmJoinRequestMemberId;
	private String memberName;
	private String memberAge;
	private String farmJoinRequestDate;
	private String farmJoinPurpose;
	private String farmJoinApproval;
	private String farmJoinApprovalMemberId;
	private Farm farm;
	
	public String getFarmJoinCode() {
		return farmJoinCode;
	}
	public void setFarmJoinCode(String farmJoinCode) {
		this.farmJoinCode = farmJoinCode;
	}
	public String getFarmCode() {
		return farmCode;
	}
	public void setFarmCode(String farmCode) {
		this.farmCode = farmCode;
	}
	public String getFarmJoinRequestMemberId() {
		return farmJoinRequestMemberId;
	}
	public void setFarmJoinRequestMemberId(String farmJoinRequestMemberId) {
		this.farmJoinRequestMemberId = farmJoinRequestMemberId;
	}
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberAge() {
		return memberAge;
	}
	public void setMemberAge(String memberAge) {
		this.memberAge = memberAge;
	}
	public String getFarmJoinRequestDate() {
		return farmJoinRequestDate;
	}
	public void setFarmJoinRequestDate(String farmJoinRequestDate) {
		this.farmJoinRequestDate = farmJoinRequestDate;
	}
	public String getFarmJoinPurpose() {
		return farmJoinPurpose;
	}
	public void setFarmJoinPurpose(String farmJoinPurpose) {
		this.farmJoinPurpose = farmJoinPurpose;
	}
	public String getFarmJoinApproval() {
		return farmJoinApproval;
	}
	public void setFarmJoinApproval(String farmJoinApproval) {
		this.farmJoinApproval = farmJoinApproval;
	}
	public String getFarmJoinApprovalMemberId() {
		return farmJoinApprovalMemberId;
	}
	public void setFarmJoinApprovalMemberId(String farmJoinApprovalMemberId) {
		this.farmJoinApprovalMemberId = farmJoinApprovalMemberId;
	}
	public Farm getFarm() {
		return farm;
	}
	public void setFarm(Farm farm) {
		this.farm = farm;
	}
	
	@Override
	public String toString() {
		return "FarmMemberJoin [farmJoinCode=" + farmJoinCode + ", farmCode=" + farmCode + ", farmJoinRequestMemberId="
				+ farmJoinRequestMemberId + ", memberName=" + memberName + ", memberAge=" + memberAge
				+ ", farmJoinRequestDate=" + farmJoinRequestDate + ", farmJoinPurpose=" + farmJoinPurpose
				+ ", farmJoinApproval=" + farmJoinApproval + ", farmJoinApprovalMemberId=" + farmJoinApprovalMemberId
				+ ", farm=" + farm + "]";
	}
	
	
	
	
	
	
	
}

