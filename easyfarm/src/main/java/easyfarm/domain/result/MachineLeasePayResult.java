package easyfarm.domain.result;

public class MachineLeasePayResult {
	// 농기계대여계획대비실행코드
    private String machineLeaseRunCode;

    // 농기계대여료지출계획코드
    private String machineLeaseCode;

    // 거래처코드
    private String clientCode;

    // 공통농기계코드
    private String commonMachineCode;

    // 대여일
    private String machineLeaseRunDate;

    // 대여료
    private String machineLeaseRunPay;

    // 실행완료여부
    private String completeStatus;

    // 작성자농가회원코드
    private String regFarmMemberCode;

    // 작성일
    private String regDate;

    // 수정자농가회원코드
    private String modFarmMemberCode;

    // 수정일
    private String modDate;

    public String getMachineLeaseRunCode() {
        return machineLeaseRunCode;
    }

    public void setMachineLeaseRunCode(String machineLeaseRunCode) {
        this.machineLeaseRunCode = machineLeaseRunCode;
    }

    public String getMachineLeaseCode() {
        return machineLeaseCode;
    }

    public void setMachineLeaseCode(String machineLeaseCode) {
        this.machineLeaseCode = machineLeaseCode;
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

    public String getMachineLeaseRunDate() {
        return machineLeaseRunDate;
    }

    public void setMachineLeaseRunDate(String machineLeaseRunDate) {
        this.machineLeaseRunDate = machineLeaseRunDate;
    }

    public String getMachineLeaseRunPay() {
        return machineLeaseRunPay;
    }

    public void setMachineLeaseRunPay(String machineLeaseRunPay) {
        this.machineLeaseRunPay = machineLeaseRunPay;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
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
