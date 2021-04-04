package easyfarm.domain.plan;

public class CommonMachine {

    // 공통농기계코드 
    private String commonMachineCode;

    // 농기계명 
    private String commonMachineName;

    // 작성자아이디 
    private String regMemberId;

    // 작성일 
    private String regDate;

    public String getCommonMachineCode() {
        return commonMachineCode;
    }

    public void setCommonMachineCode(String commonMachineCode) {
        this.commonMachineCode = commonMachineCode;
    }

    public String getCommonMachineName() {
        return commonMachineName;
    }

    public void setCommonMachineName(String commonMachineName) {
        this.commonMachineName = commonMachineName;
    }

    public String getRegMemberId() {
        return regMemberId;
    }

    public void setRegMemberId(String regMemberId) {
        this.regMemberId = regMemberId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    // TbCommonMachine 모델 복사
    public void CopyData(CommonMachine param)
    {
        this.commonMachineCode = param.getCommonMachineCode();
        this.commonMachineName = param.getCommonMachineName();
        this.regMemberId = param.getRegMemberId();
        this.regDate = param.getRegDate();
    }
	
}
