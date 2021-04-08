package easyfarm.domain.plan;

public class FarmBookmarkMachine {

    // 농가별농기계즐겨찾기코드 
    private String farmBookmarkMachineCode;

    // 공통농기계코드 
    private String commonMachineCode;

    // 농가코드 
    private String farmCode;

    // 작성자아이디 
    private String regMemberId;

    // 작성일 
    private String regDate;

    public String getFarmBookmarkMachineCode() {
        return farmBookmarkMachineCode;
    }

    public void setFarmBookmarkMachineCode(String farmBookmarkMachineCode) {
        this.farmBookmarkMachineCode = farmBookmarkMachineCode;
    }

    public String getCommonMachineCode() {
        return commonMachineCode;
    }

    public void setCommonMachineCode(String commonMachineCode) {
        this.commonMachineCode = commonMachineCode;
    }

    public String getFarmCode() {
        return farmCode;
    }

    public void setFarmCode(String farmCode) {
        this.farmCode = farmCode;
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

    // TbFarmBookmarkMachine 모델 복사
    public void CopyData(FarmBookmarkMachine param)
    {
        this.farmBookmarkMachineCode = param.getFarmBookmarkMachineCode();
        this.commonMachineCode = param.getCommonMachineCode();
        this.farmCode = param.getFarmCode();
        this.regMemberId = param.getRegMemberId();
        this.regDate = param.getRegDate();
    }
	
}
