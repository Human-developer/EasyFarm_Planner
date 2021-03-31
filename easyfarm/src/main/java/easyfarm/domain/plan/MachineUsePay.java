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
    private String regFarmMemberCode;

    // 작성일
    private String regDate;

    // 수정자농가회원코드
    private String modFarmMemberCode;

    // 수정일
    private String modDate;

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
	
}
