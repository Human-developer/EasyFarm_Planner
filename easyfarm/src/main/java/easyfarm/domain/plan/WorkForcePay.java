package easyfarm.domain.plan;


public class WorkForcePay {
	// 인건비지출계획코드
    private String workforcePayCode;

    // 기간별작업단계계획코드
    private String planWorkphaseCode;

    // 작업단계별상세항목코드
    private String planWorkphaseCateCode;

    // 근무인원
    private String workforceCount;

    // 근무일수
    private String workforceHowlong;

    // 맨데이
    private String workforceRequireDay;

    // 일일인건비
    private String workforcePayDay;

    // 인건비합계
    private String workforcePayTotal;

    // 작성자농가회원코드

    private String regMemberId;

    // 작성일
    private String regDate;


    public String getWorkforcePayCode() {
        return workforcePayCode;
    }

    public void setWorkforcePayCode(String workforcePayCode) {
        this.workforcePayCode = workforcePayCode;
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

    public String getWorkforceCount() {
        return workforceCount;
    }

    public void setWorkforceCount(String workforceCount) {
        this.workforceCount = workforceCount;
    }

    public String getWorkforceHowlong() {
        return workforceHowlong;
    }

    public void setWorkforceHowlong(String workforceHowlong) {
        this.workforceHowlong = workforceHowlong;
    }

    public String getWorkforceRequireDay() {
        return workforceRequireDay;
    }

    public void setWorkforceRequireDay(String workforceRequireDay) {
        this.workforceRequireDay = workforceRequireDay;
    }

    public String getWorkforcePayDay() {
        return workforcePayDay;
    }

    public void setWorkforcePayDay(String workforcePayDay) {
        this.workforcePayDay = workforcePayDay;
    }

    public String getWorkforcePayTotal() {
        return workforcePayTotal;
    }

    public void setWorkforcePayTotal(String workforcePayTotal) {
        this.workforcePayTotal = workforcePayTotal;
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
