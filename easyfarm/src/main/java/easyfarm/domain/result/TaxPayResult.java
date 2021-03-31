package easyfarm.domain.result;

import easyfarm.domain.plan.TaxPay;

public class TaxPayResult {
	// 공과금지출계획대비실행코드
    private String taxPayRunCode;

    // 공과금지출계획코드
    private String taxPayCode;

    // 기간별작업단계계획코드
    private String planWorkphaseCode;

    // 작업단계별상세항목코드
    private String planWorkphaseCateCode;

    // 거래처코드
    private String clientCode;

    // 공과금항목코드
    private String taxPayCateCode;

    // 납부일
    private String taxPayRunDate;

    // 해당월
    private String taxPayRunCorrMonth;

    // 납부금액
    private String taxPayRunPay;

    // 추가메모
    private String taxPayRunMemo;

    // 실행완료여부
    private String completeStatus;

    // 작성자농가회원코드
    private String regMemberId;

    // 작성일
    private String regDate;
    
    private TaxPay taxPayPlan;

    public String getTaxPayRunCode() {
        return taxPayRunCode;
    }

    public void setTaxPayRunCode(String taxPayRunCode) {
        this.taxPayRunCode = taxPayRunCode;
    }

    public String getTaxPayCode() {
        return taxPayCode;
    }

    public void setTaxPayCode(String taxPayCode) {
        this.taxPayCode = taxPayCode;
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

    public String getTaxPayCateCode() {
        return taxPayCateCode;
    }

    public void setTaxPayCateCode(String taxPayCateCode) {
        this.taxPayCateCode = taxPayCateCode;
    }

    public String getTaxPayRunDate() {
        return taxPayRunDate;
    }

    public void setTaxPayRunDate(String taxPayRunDate) {
        this.taxPayRunDate = taxPayRunDate;
    }

    public String getTaxPayRunCorrMonth() {
        return taxPayRunCorrMonth;
    }

    public void setTaxPayRunCorrMonth(String taxPayRunCorrMonth) {
        this.taxPayRunCorrMonth = taxPayRunCorrMonth;
    }

    public String getTaxPayRunPay() {
        return taxPayRunPay;
    }

    public void setTaxPayRunPay(String taxPayRunPay) {
        this.taxPayRunPay = taxPayRunPay;
    }

    public String getTaxPayRunMemo() {
        return taxPayRunMemo;
    }

    public void setTaxPayRunMemo(String taxPayRunMemo) {
        this.taxPayRunMemo = taxPayRunMemo;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getregMemberId() {
        return regMemberId;
    }

    public void setregMemberId(String regMemberId) {
        this.regMemberId = regMemberId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

	public String getRegMemberId() {
		return regMemberId;
	}

	public void setRegMemberId(String regMemberId) {
		this.regMemberId = regMemberId;
	}

	public TaxPay getTaxPayPlan() {
		return taxPayPlan;
	}

	public void setTaxPayPlan(TaxPay taxPayPlan) {
		this.taxPayPlan = taxPayPlan;
	}

	
}