package easyfarm.domain;

public class Crop {
	private String cropCode     ;//작물코드
	private String cropCate     ;//작물카테고리
	private String cropName     ;//작물명
	private String useStatus    ;//사용상태
	private String regMemberId  ;//등록자 아이디
	private String regDate      ;//등록일자
	private String modMemberId  ;//수정자 아이디
	private String modDate      ;//수정일자
	private Member member;
	private String memberId;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getCropCode() {
		return cropCode;
	}
	public void setCropCode(String cropCode) {
		this.cropCode = cropCode;
	}
	public String getCropCate() {
		return cropCate;
	}
	public void setCropCate(String cropCate) {
		this.cropCate = cropCate;
	}
	public String getCropName() {
		return cropName;
	}
	public void setCropName(String cropName) {
		this.cropName = cropName;
	}
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
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
	public String getModMemberId() {
		return modMemberId;
	}
	public void setModMemberId(String modMemberId) {
		this.modMemberId = modMemberId;
	}
	public String getModDate() {
		return modDate;
	}
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Crop [cropCode=");
		builder.append(cropCode);
		builder.append(", cropCate=");
		builder.append(cropCate);
		builder.append(", cropName=");
		builder.append(cropName);
		builder.append(", useStatus=");
		builder.append(useStatus);
		builder.append(", regMemberId=");
		builder.append(regMemberId);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", modMemberId=");
		builder.append(modMemberId);
		builder.append(", modDate=");
		builder.append(modDate);
		builder.append("]");
		return builder.toString();
	}
	
}
