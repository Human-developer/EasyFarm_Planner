package easyfarm.domain.result;

import easyfarm.domain.plan.MachineUsePay;

public class MachineUsePayResult {
	// 농기계사용계획대비실행코드
    private String machineUseRunCode;

    // 농기계사용지출계획
    private String machineUseCode;

    // 사용일
    private String machineUseRunDate;

    // 농가별보유농기계코드
    private String farmRetainMachineCode;

    // 실행완료여부
    private String completeStatus;

    // 작성자농가회원코드
    private String regMemberId;

    // 작성일
    private String regDate;

    // 계획
    private MachineUsePay machineUsePayPlan;
    

    public String getMachineUseRunCode() {
        return machineUseRunCode;
    }

    public void setMachineUseRunCode(String machineUseRunCode) {
        this.machineUseRunCode = machineUseRunCode;
    }

    public String getMachineUseCode() {
        return machineUseCode;
    }

    public void setMachineUseCode(String machineUseCode) {
        this.machineUseCode = machineUseCode;
    }

    public String getMachineUseRunDate() {
        return machineUseRunDate;
    }

    public void setMachineUseRunDate(String machineUseRunDate) {
        this.machineUseRunDate = machineUseRunDate;
    }

    public String getFarmRetainMachineCode() {
        return farmRetainMachineCode;
    }

    public void setFarmRetainMachineCode(String farmRetainMachineCode) {
        this.farmRetainMachineCode = farmRetainMachineCode;
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

	public MachineUsePay getMachineUsePayPlan() {
		return machineUsePayPlan;
	}

	public void setMachineUsePayPlan(MachineUsePay machineUsePayPlan) {
		this.machineUsePayPlan = machineUsePayPlan;
	}

	
}
