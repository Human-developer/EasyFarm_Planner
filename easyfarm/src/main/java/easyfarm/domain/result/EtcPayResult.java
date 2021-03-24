package easyfarm.domain.result;

public class EtcPayResult {
	private String etcPayRunCode;
	private String etcPayCode;
	private String planWorkphaseCode;
	private String planWorkphaseCateCode;
	private String clientCode;
	private String etcPayRunDate;
	private String etcPayRunPay;
	private String etcPayRunMemo;
	private String complete_status;
	private String regFarmMemberCode;
	private String regDate;
	private String modFarmMemberCode;
	private String modDate;
	
	public String getEtcPayRunCode() {
		return etcPayRunCode;
	}
	public void setEtcPayRunCode(String etcPayRunCode) {
		this.etcPayRunCode = etcPayRunCode;
	}
	public String getEtcPayCode() {
		return etcPayCode;
	}
	public void setEtcPayCode(String etcPayCode) {
		this.etcPayCode = etcPayCode;
	}
	public String getPlanWorkphaseCode() {
		return planWorkphaseCode;
	}
	public void setPlanWorkphaseCode(String planWorkphaseCode) {
		this.planWorkphaseCode = planWorkphaseCode;
	}
	public String getPlanWorkphaseCateCode() {
		return planWorkphaseCateCode;
	}
	public void setPlanWorkphaseCateCode(String planWorkphaseCateCode) {
		this.planWorkphaseCateCode = planWorkphaseCateCode;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getEtcPayRunDate() {
		return etcPayRunDate;
	}
	public void setEtcPayRunDate(String etcPayRunDate) {
		this.etcPayRunDate = etcPayRunDate;
	}
	public String getEtcPayRunPay() {
		return etcPayRunPay;
	}
	public void setEtcPayRunPay(String etcPayRunPay) {
		this.etcPayRunPay = etcPayRunPay;
	}
	public String getEtcPayRunMemo() {
		return etcPayRunMemo;
	}
	public void setEtcPayRunMemo(String etcPayRunMemo) {
		this.etcPayRunMemo = etcPayRunMemo;
	}
	public String getComplete_status() {
		return complete_status;
	}
	public void setComplete_status(String complete_status) {
		this.complete_status = complete_status;
	}
	public String getRegFarmMemberCode() {
		return regFarmMemberCode;
	}
	public void setRegFarmMemberCode(String regFarmMemberCode) {
		this.regFarmMemberCode = regFarmMemberCode;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getModFarmMemberCode() {
		return modFarmMemberCode;
	}
	public void setModFarmMemberCode(String modFarmMemberCode) {
		this.modFarmMemberCode = modFarmMemberCode;
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
		builder.append("EtcPay [etcPayRunCode=");
		builder.append(etcPayRunCode);
		builder.append(", etcPayCode=");
		builder.append(etcPayCode);
		builder.append(", planWorkphaseCode=");
		builder.append(planWorkphaseCode);
		builder.append(", planWorkphaseCateCode=");
		builder.append(planWorkphaseCateCode);
		builder.append(", clientCode=");
		builder.append(clientCode);
		builder.append(", etcPayRunDate=");
		builder.append(etcPayRunDate);
		builder.append(", etcPayRunPay=");
		builder.append(etcPayRunPay);
		builder.append(", etcPayRunMemo=");
		builder.append(etcPayRunMemo);
		builder.append(", complete_status=");
		builder.append(complete_status);
		builder.append(", regFarmMemberCode=");
		builder.append(regFarmMemberCode);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", modFarmMemberCode=");
		builder.append(modFarmMemberCode);
		builder.append(", modDate=");
		builder.append(modDate);
		builder.append("]");
		return builder.toString();
	}
}
