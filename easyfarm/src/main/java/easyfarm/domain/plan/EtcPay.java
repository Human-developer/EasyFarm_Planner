package easyfarm.domain.plan;

public class EtcPay {
	// 기타비용지출계획코드
    private String etcPayCode;

    // 기간별작업단계계획코드
    private String planWorkphaseCode;

    // 작업단계별상세항목코드
    private String planWorkphaseCateCode;

    // 거래처코드
    private String clientCode;

    // 지출일
    private String etcPayDate;

    // 지출금액
    private String etcPay;

    // 추가메모
    private String etcPayDetail;

    // 작성자농가회원코드

    private String regMemberId;

    // 작성일
    private String regDate;


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

    public String getEtcPayDate() {
        return etcPayDate;
    }

    public void setEtcPayDate(String etcPayDate) {
        this.etcPayDate = etcPayDate;
    }

    public String getEtcPay() {
        return etcPay;
    }

    public void setEtcPay(String etcPay) {
        this.etcPay = etcPay;
    }

    public String getEtcPayDetail() {
        return etcPayDetail;
    }

    public void setEtcPayDetail(String etcPayDetail) {
        this.etcPayDetail = etcPayDetail;
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


  
}
