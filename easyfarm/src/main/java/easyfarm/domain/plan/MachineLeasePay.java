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
    private String commonMachineCode;

    // 대여일수
    private String machineLeaseHowlong;

    // 일일대여료
    private String machineLeasePayDay;

    // 대여료합계
    private String machineLeasePayTotal;

    // 작성자농가회원코드
    private String regFarmMemberCode;

    // 작성일
    private String regDate;

    // 수정자농가회원코드
    private String modFarmMemberCode;

    // 수정일
    private String modDate;

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

    public String getCommonMachineCode() {
        return commonMachineCode;
    }

    public void setCommonMachineCode(String commonMachineCode) {
        this.commonMachineCode = commonMachineCode;
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
