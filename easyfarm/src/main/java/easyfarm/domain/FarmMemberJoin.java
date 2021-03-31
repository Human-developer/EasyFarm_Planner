package easyfarm.domain;

public class FarmMemberJoin {
	private String farmJoinCode;
	private String farmCode;
	private String farmJoinRequestMemberId;
	private String farmJoinRequestDate;
	private String farmJoinPurpose;
	private String farmJoinApproval;
	private String farmJoinApprovalMemberId;
	
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FarmMemberJoin [farmJoinCode=");
		builder.append(farmJoinCode);
		builder.append(", farmCode=");
		builder.append(farmCode);
		builder.append(", farmJoinRequestMemberId=");
		builder.append(farmJoinRequestMemberId);
		builder.append(", farmJoinRequestDate=");
		builder.append(farmJoinRequestDate);
		builder.append(", farmJoinPurpose=");
		builder.append(farmJoinPurpose);
		builder.append(", farmJoinApproval=");
		builder.append(farmJoinApproval);
		builder.append(", farmJoinApprovalMemberId=");
		builder.append(farmJoinApprovalMemberId);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
