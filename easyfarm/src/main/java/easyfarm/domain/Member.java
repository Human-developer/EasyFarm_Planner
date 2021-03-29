package easyfarm.domain;

/*import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;*/

public class Member {

	private String memberId;			//회원 아이디
	private String memberPw;			//회원 비밀번호
	private String memberName;			//회원 이름
	private String levelCode;			//회원 등급
	private String memberGender;		//회원 성별 
	private String memberAddress;		//회원 주소
	private String memberEmail;			//회원 이메일
	private String memberPhone;			//회원 휴대폰번호
	private String memberIdenNum;		//회원 주민등록번호
	private String memberIdenNum2;		//회원 주민등록번호
	private String memberRegDate;		//회원 등록 일자
	private String memberStatus;		//회원 상태
	private String levelName;			//권한 이름
	private String useStatus;			//권한 사용 상태
	private String cancelMemberReason;	//탈퇴사유
	
	
	public String getCancelMemberReason() {
		return cancelMemberReason;
	}
	public void setCancelMemberReason(String cancelMemberReason) {
		this.cancelMemberReason = cancelMemberReason;
	}
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
	
		this.memberId = memberId;
	}
	public String getMemberPw() {	
		
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberIdenNum() {
		return memberIdenNum;
	}
	public void setMemberIdenNum(String memberIdenNum) {
		this.memberIdenNum = memberIdenNum;
	}
	public String getMemberIdenNum2() {
		return memberIdenNum2;
	}
	public void setMemberIdenNum2(String memberIdenNum2) {
		this.memberIdenNum2 = memberIdenNum2;
	}
	public String getMemberRegDate() {
		return memberRegDate;
	}
	public void setMemberRegDate(String memberRegDate) {
		this.memberRegDate = memberRegDate;
	}
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [memberId=");
		builder.append(memberId);
		builder.append(", memberPw=");
		builder.append(memberPw);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", levelCode=");
		builder.append(levelCode);
		builder.append(", memberGender=");
		builder.append(memberGender);
		builder.append(", memberAddress=");
		builder.append(memberAddress);
		builder.append(", memberEmail=");
		builder.append(memberEmail);
		builder.append(", memberPhone=");
		builder.append(memberPhone);
		builder.append(", memberIdenNum=");
		builder.append(memberIdenNum);
		builder.append(", memberIdenNum2=");
		builder.append(memberIdenNum2);
		builder.append(", memberRegDate=");
		builder.append(memberRegDate);
		builder.append(", memberStatus=");
		builder.append(memberStatus);
		builder.append(", levelName=");
		builder.append(levelName);
		builder.append(", useStatus=");
		builder.append(useStatus);
		builder.append(", cancelMemberReason=");
		builder.append(cancelMemberReason);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

	/*
	 * public void setMemberPwEncrypt() { StandardPBEStringEncryptor stringPBEConfig
	 * = new StandardPBEStringEncryptor(); stringPBEConfig.setPassword("easyfarm");
	 * stringPBEConfig.setAlgorithm("PBEWithMD5AndDES"); this.memberPw =
	 * stringPBEConfig.encrypt(this.memberPw);
	 * 
	 * }
	 * 
	 * public void getMemberPwDecrypt() { StandardPBEStringEncryptor stringPBEConfig
	 * = new StandardPBEStringEncryptor(); stringPBEConfig.setPassword("easyfarm");
	 * stringPBEConfig.setAlgorithm("PBEWithMD5AndDES");
	 * 
	 * this.memberPw = stringPBEConfig.decrypt(this.memberPw); }
	 */
	
	
	
	
}
