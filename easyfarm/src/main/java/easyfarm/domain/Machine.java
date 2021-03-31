package easyfarm.domain;

import java.sql.Timestamp;

public class Machine {
	 // 공통농기계코드
    private String commonMachineCode;

    // 농기계명
    private String commonMachineName;

    // 등록자아이디
    private String regMemberId;

    // 등록일
    private Timestamp regDate;


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

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    // Machine 모델 복사
    public void CopyData(Machine param)
    {
        this.commonMachineCode = param.getCommonMachineCode();
        this.commonMachineName = param.getCommonMachineName();
        this.regMemberId = param.getRegMemberId();
        this.regDate = param.getRegDate();
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Machine [commonMachineCode=");
		builder.append(commonMachineCode);
		builder.append(", commonMachineName=");
		builder.append(commonMachineName);
		builder.append(", regMemberId=");
		builder.append(regMemberId);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append("]");
		return builder.toString();
	}
    
}
