package easyfarm.domain.plan;

public class MachineUsePay {
	// 농기계사용지출계획
    private String machineUseCode;

    // 기간별작업단계계획코드
    private String planWorkphaseCode;

    // 작업단계별상세항목코드
    private String planWorkphaseCateCode;

    // 농가별보유농기계코드
    private String farmRetainMachineCode;

    // 작성자농가회원코드

    private String regMemberId;

    // 작성일
    private String regDate;

    public String getMachineUseCode() {
        return machineUseCode;
    }

    public void setMachineUseCode(String machineUseCode) {
        this.machineUseCode = machineUseCode;
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

    public String getFarmRetainMachineCode() {
        return farmRetainMachineCode;
    }

    public void setFarmRetainMachineCode(String farmRetainMachineCode) {
        this.farmRetainMachineCode = farmRetainMachineCode;
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
