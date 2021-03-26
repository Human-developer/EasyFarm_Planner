package easyfarm.domain;

import java.sql.Timestamp;

public class CropDetailCategory {
	 // 공통상세작업항목코드
    private String commonWorkphaseCateCode;

    // 공통상세작업항목명
    private String commonWorkphaseCateName;

    // 작성자아이디
    private String regMemberId;

    // 작성일
    private Timestamp regDate;

    // 수정자아이디
    private String modMemberId;

    // 수정일
    private Timestamp modDate;
    
    private String memberId;
    

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCommonWorkphaseCateCode() {
        return commonWorkphaseCateCode;
    }

    public void setCommonWorkphaseCateCode(String commonWorkphaseCateCode) {
        this.commonWorkphaseCateCode = commonWorkphaseCateCode;
    }

    public String getCommonWorkphaseCateName() {
        return commonWorkphaseCateName;
    }

    public void setCommonWorkphaseCateName(String commonWorkphaseCateName) {
        this.commonWorkphaseCateName = commonWorkphaseCateName;
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

    public String getModMemberId() {
        return modMemberId;
    }

    public void setModMemberId(String modMemberId) {
        this.modMemberId = modMemberId;
    }

    public Timestamp getModDate() {
        return modDate;
    }

    public void setModDate(Timestamp modDate) {
        this.modDate = modDate;
    }

    // CropDetailCategory 모델 복사
    public void CopyData(CropDetailCategory param)
    {
        this.commonWorkphaseCateCode = param.getCommonWorkphaseCateCode();
        this.commonWorkphaseCateName = param.getCommonWorkphaseCateName();
        this.regMemberId = param.getRegMemberId();
        this.regDate = param.getRegDate();
        this.modMemberId = param.getModMemberId();
        this.modDate = param.getModDate();
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CropDetailCategory [commonWorkphaseCateCode=");
		builder.append(commonWorkphaseCateCode);
		builder.append(", commonWorkphaseCateName=");
		builder.append(commonWorkphaseCateName);
		builder.append(", regMemberId=");
		builder.append(regMemberId);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", modMemberId=");
		builder.append(modMemberId);
		builder.append(", modDate=");
		builder.append(modDate);
		builder.append("]");
		return builder.toString();
	}
    
}
