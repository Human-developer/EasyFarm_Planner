package easyfarm.domain.plan;

public class FarmRetainMachine {
	
    // 농가별보유농기계코드 
    private String farmRetainMachineCode;

    // 농가별농기계즐겨찾기코드 
    private String farmBookmarkMachineCode;

    // 농가코드 
    private String farmCode;

    // 작성자아이디 
    private String regMemberId;

    // 작성일 
    private String regDate;

    public String getFarmRetainMachineCode() {
        return farmRetainMachineCode;
    }

    public void setFarmRetainMachineCode(String farmRetainMachineCode) {
        this.farmRetainMachineCode = farmRetainMachineCode;
    }

    public String getFarmBookmarkMachineCode() {
        return farmBookmarkMachineCode;
    }

    public void setFarmBookmarkMachineCode(String farmBookmarkMachineCode) {
        this.farmBookmarkMachineCode = farmBookmarkMachineCode;
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
	
}
