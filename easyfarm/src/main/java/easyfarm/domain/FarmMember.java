package easyfarm.domain;

public class FarmMember {
	private String farmMemberCode;
	private String farmCode;
	private String farmName;
	private String farmMemberId;
	private String farmLevelCode;
	private String farmLevelName;
	private String farmJoinApprovalDate;
	private String farmMemberStatus;
	private Member member;	
	
	public String getFarmMemberCode() {
		return farmMemberCode;
	}
	public void setFarmMemberCode(String farmMemberCode) {
		this.farmMemberCode = farmMemberCode;
	}
	public String getFarmCode() {
		return farmCode;
	}
	public void setFarmCode(String farmCode) {
		this.farmCode = farmCode;
	}
	public String getFarmName() {
		return farmName;
	}
	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}
	public String getFarmMemberId() {
		return farmMemberId;
	}
	public void setFarmMemberId(String farmMemberId) {
		this.farmMemberId = farmMemberId;
	}
	public String getFarmLevelCode() {
		return farmLevelCode;
	}
	public void setFarmLevelCode(String farmLevelCode) {
		this.farmLevelCode = farmLevelCode;
	}
	public String getFarmLevelName() {
		return farmLevelName;
	}
	public void setFarmLevelName(String farmLevelName) {
		this.farmLevelName = farmLevelName;
	}
	public String getFarmJoinApprovalDate() {
		return farmJoinApprovalDate;
	}
	public void setFarmJoinApprovalDate(String farmJoinApprovalDate) {
		this.farmJoinApprovalDate = farmJoinApprovalDate;
	}
	public String getFarmMemberStatus() {
		return farmMemberStatus;
	}
	public void setFarmMemberStatus(String farmMemberStatus) {
		this.farmMemberStatus = farmMemberStatus;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FarmMember [farmMemberCode=");
		builder.append(farmMemberCode);
		builder.append(", farmCode=");
		builder.append(farmCode);
		builder.append(", farmName=");
		builder.append(farmName);
		builder.append(", farmMemberId=");
		builder.append(farmMemberId);
		builder.append(", farmLevelCode=");
		builder.append(farmLevelCode);
		builder.append(", farmLevelName=");
		builder.append(farmLevelName);
		builder.append(", farmJoinApprovalDate=");
		builder.append(farmJoinApprovalDate);
		builder.append(", farmMemberStatus=");
		builder.append(farmMemberStatus);
		builder.append(", member=");
		builder.append(member);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
