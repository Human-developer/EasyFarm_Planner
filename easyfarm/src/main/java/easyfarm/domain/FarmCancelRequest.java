package easyfarm.domain;

public class FarmCancelRequest {
	private String cancelRequestCode; 
	private String cancelRequestMemberCode; 
	private String farmCode;
	private String cancelRequestReason; 
	private String cancelRequestDate; 
	private String cancelApproval; 
	private String cancelApprovalReason; 
	private String cancelApprovalDate; 
	private String cancelApprovalMemberId;
	private FarmMember farmMember;
	private Member member;
	
	public String getCancelRequestCode() {
		return cancelRequestCode;
	}
	public void setCancelRequestCode(String cancelRequestCode) {
		this.cancelRequestCode = cancelRequestCode;
	}
	public String getCancelRequestMemberCode() {
		return cancelRequestMemberCode;
	}
	public void setCancelRequestMemberCode(String cancelRequestMemberCode) {
		this.cancelRequestMemberCode = cancelRequestMemberCode;
	}
	public String getFarmCode() {
		return farmCode;
	}
	public void setFarmCode(String farmCode) {
		this.farmCode = farmCode;
	}
	public String getCancelRequestReason() {
		return cancelRequestReason;
	}
	public void setCancelRequestReason(String cancelRequestReason) {
		this.cancelRequestReason = cancelRequestReason;
	}
	public String getCancelRequestDate() {
		return cancelRequestDate;
	}
	public void setCancelRequestDate(String cancelRequestDate) {
		this.cancelRequestDate = cancelRequestDate;
	}
	public String getCancelApproval() {
		return cancelApproval;
	}
	public void setCancelApproval(String cancelApproval) {
		this.cancelApproval = cancelApproval;
	}
	public String getCancelApprovalReason() {
		return cancelApprovalReason;
	}
	public void setCancelApprovalReason(String cancelApprovalReason) {
		this.cancelApprovalReason = cancelApprovalReason;
	}
	public String getCancelApprovalDate() {
		return cancelApprovalDate;
	}
	public void setCancelApprovalDate(String cancelApprovalDate) {
		this.cancelApprovalDate = cancelApprovalDate;
	}
	public String getCancelApprovalMemberId() {
		return cancelApprovalMemberId;
	}
	public void setCancelApprovalMemberId(String cancelApprovalMemberId) {
		this.cancelApprovalMemberId = cancelApprovalMemberId;
	}
	public FarmMember getFarmMember() {
		return farmMember;
	}
	public void setFarmMember(FarmMember farmMember) {
		this.farmMember = farmMember;
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
		builder.append("FarmCancelRequest [cancelRequestCode=");
		builder.append(cancelRequestCode);
		builder.append(", cancelRequestMemberCode=");
		builder.append(cancelRequestMemberCode);
		builder.append(", farmCode=");
		builder.append(farmCode);
		builder.append(", cancelRequestReason=");
		builder.append(cancelRequestReason);
		builder.append(", cancelRequestDate=");
		builder.append(cancelRequestDate);
		builder.append(", cancelApproval=");
		builder.append(cancelApproval);
		builder.append(", cancelApprovalReason=");
		builder.append(cancelApprovalReason);
		builder.append(", cancelApprovalDate=");
		builder.append(cancelApprovalDate);
		builder.append(", cancelApprovalMemberId=");
		builder.append(cancelApprovalMemberId);
		builder.append(", farmMember=");
		builder.append(farmMember);
		builder.append(", member=");
		builder.append(member);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
	
	
}
