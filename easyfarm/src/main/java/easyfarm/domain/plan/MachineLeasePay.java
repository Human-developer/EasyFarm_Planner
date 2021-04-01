package easyfarm.domain.plan;

public class MachineLeasePay {
	// 농기계대여료지출계획코드
    private String machineLeaseCode;

    // 기간별작업단계계획코드
    private String planWorkphaseCode;

    // 작업단계별상세항목코드
    private String planWorkphaseCateCode;

    // 거래처코드
    private String clientCode;

    // 공통농기계코드

    private String farmBookmarkMachineCode;

    // 대여일수
    private String machineLeaseHowlong;

    // 일일대여료
    private String machineLeasePayDay;

    // 대여료합계
    private String machineLeasePayTotal;

    // 작성자농가회원코드
    private String regMemberId;

    // 작성일
    private String regDate;


    public String getMachineLeaseCode() {
        return machineLeaseCode;
    }

    public void setMachineLeaseCode(String machineLeaseCode) {
        this.machineLeaseCode = machineLeaseCode;
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


    public String getMachineLeaseHowlong() {
        return machineLeaseHowlong;
    }

    public void setMachineLeaseHowlong(String machineLeaseHowlong) {
        this.machineLeaseHowlong = machineLeaseHowlong;
    }

    public String getMachineLeasePayDay() {
        return machineLeasePayDay;
    }

    public void setMachineLeasePayDay(String machineLeasePayDay) {
        this.machineLeasePayDay = machineLeasePayDay;
    }

    public String getMachineLeasePayTotal() {
        return machineLeasePayTotal;
    }

    public void setMachineLeasePayTotal(String machineLeasePayTotal) {
        this.machineLeasePayTotal = machineLeasePayTotal;
    }


    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }


	public String getFarmBookmarkMachineCode() {
		return farmBookmarkMachineCode;
	}

	public void setFarmBookmarkMachineCode(String farmBookmarkMachineCode) {
		this.farmBookmarkMachineCode = farmBookmarkMachineCode;
	}

	public String getRegMemberId() {
		return regMemberId;
	}

	public void setRegMemberId(String regMemberId) {
		this.regMemberId = regMemberId;
	}

	
}
