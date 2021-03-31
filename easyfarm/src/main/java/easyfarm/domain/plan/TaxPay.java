package easyfarm.domain.plan;


public class TaxPay {
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

    // 해당월
    private String taxPayWhatmonth;

    // 납부금액
    private String taxPay;

    // 추가메모
    private String taxPayDetail;

    // 작성자농가회원코드

    private String regMemberId;

    // 작성일
    private String regDate;


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

    public String getTaxPayWhatmonth() {
        return taxPayWhatmonth;
    }

    public void setTaxPayWhatmonth(String taxPayWhatmonth) {
        this.taxPayWhatmonth = taxPayWhatmonth;
    }

    public String getTaxPay() {
        return taxPay;
    }

    public void setTaxPay(String taxPay) {
        this.taxPay = taxPay;
    }

    public String getTaxPayDetail() {
        return taxPayDetail;
    }

    public void setTaxPayDetail(String taxPayDetail) {
        this.taxPayDetail = taxPayDetail;
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


}
