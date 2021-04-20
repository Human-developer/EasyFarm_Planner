package easyfarm.domain;

public class TaxPayCate {
	 private String	taxPayCateCode;
	 private String taxPayCateName;
	 private String useStatus;
	 private String regMemberId;
	 private String regDate;
	public String getTaxPayCateCode() {
		return taxPayCateCode;
	}
	public void setTaxPayCateCode(String taxPayCateCode) {
		this.taxPayCateCode = taxPayCateCode;
	}
	public String getTaxPayCateName() {
		return taxPayCateName;
	}
	public void setTaxPayCateName(String taxPayCateName) {
		this.taxPayCateName = taxPayCateName;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TaxPayCate [taxPayCateCode=");
		builder.append(taxPayCateCode);
		builder.append(", taxPayCateName=");
		builder.append(taxPayCateName);
		builder.append(", useStatus=");
		builder.append(useStatus);
		builder.append(", regMemberId=");
		builder.append(regMemberId);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append("]");
		return builder.toString();
	}
	 
} 
