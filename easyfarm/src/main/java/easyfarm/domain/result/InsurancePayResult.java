package easyfarm.domain.result;

import easyfarm.domain.plan.InsurancePay;

public class InsurancePayResult {
	// 보험료지출계획대비실행코드
    private String insurePayRunCode;

    // 보험료지출계획코드
    private String insurePayCode;

    // 프로젝트코드
    private String projectCode;

    // 보험사
    private String insureCompany;

    // 보험명
    private String insureName;

    // 보장내용
    private String insureDatail;

    // 보험가입일
    private String insureBegin;

    // 가입면적
    private String insureArea;

    // 보험만료일
    private String insureEnd;

    // 총보험비
    private String insurePayTotal;

    // 예상월보험료
    private String insurePayPerMonth;

    // 실행완료여부
    private String completeStatus;

    // 작성자농가회원코드
    private String regMemberId;

    // 작성일
    private String regDate;
    
    //계획
    private InsurancePay insurancePayPlan;
    

    public String getInsurePayRunCode() {
        return insurePayRunCode;
    }

    public void setInsurePayRunCode(String insurePayRunCode) {
        this.insurePayRunCode = insurePayRunCode;
    }

    public String getInsurePayCode() {
        return insurePayCode;
    }

    public void setInsurePayCode(String insurePayCode) {
        this.insurePayCode = insurePayCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getInsureCompany() {
        return insureCompany;
    }

    public void setInsureCompany(String insureCompany) {
        this.insureCompany = insureCompany;
    }

    public String getInsureName() {
        return insureName;
    }

    public void setInsureName(String insureName) {
        this.insureName = insureName;
    }

    public String getInsureDatail() {
        return insureDatail;
    }

    public void setInsureDatail(String insureDatail) {
        this.insureDatail = insureDatail;
    }

    public String getInsureBegin() {
        return insureBegin;
    }

    public void setInsureBegin(String insureBegin) {
        this.insureBegin = insureBegin;
    }

    public String getInsureArea() {
        return insureArea;
    }

    public void setInsureArea(String insureArea) {
        this.insureArea = insureArea;
    }

    public String getInsureEnd() {
        return insureEnd;
    }

    public void setInsureEnd(String insureEnd) {
        this.insureEnd = insureEnd;
    }

    public String getInsurePayTotal() {
        return insurePayTotal;
    }

    public void setInsurePayTotal(String insurePayTotal) {
        this.insurePayTotal = insurePayTotal;
    }

    public String getInsurePayPerMonth() {
        return insurePayPerMonth;
    }

    public void setInsurePayPerMonth(String insurePayPerMonth) {
        this.insurePayPerMonth = insurePayPerMonth;
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

	public InsurancePay getInsurancePay() {
		return insurancePayPlan;
	}

	public void setInsurancePay(InsurancePay insurancePayPlan) {
		this.insurancePayPlan = insurancePayPlan;
	}

	public InsurancePay getInsurancePayPlan() {
		return insurancePayPlan;
	}

	public void setInsurancePayPlan(InsurancePay insurancePayPlan) {
		this.insurancePayPlan = insurancePayPlan;
	}

	
}
