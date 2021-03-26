package easyfarm.domain;

import java.sql.Timestamp;

// 회원명
public class CropWorkStage {

    // 작업단계코드
    private String cropPhaseInfoCode;
    // 작물코드
    private String cropCode;
    // 작업단계
    private String workphase;
    // 작업단계명
    private String workphaseName;
    // 사용여부
    private String useStatus;
    // 작성자아이디
    private String regMemberId;
    // 작성일
    private Timestamp regDate;
    // 수정자아이디
    private String modMemberId;
    // 수정일
    private Timestamp modDate;
    
    private CropDetailCategory cropDetailCategory;
    private Crop crop;
    
    
    public Crop getCrop() {
		return crop;
	}

	public void setCrop(Crop crop) {
		this.crop = crop;
	}

	public CropDetailCategory getCropDetailCategory() {
		return cropDetailCategory;
	}

	public void setCropDetailCategory(CropDetailCategory cropDetailCategory) {
		this.cropDetailCategory = cropDetailCategory;
	}

	public String getCropPhaseInfoCode() {
        return cropPhaseInfoCode;
    }

    public void setCropPhaseInfoCode(String cropPhaseInfoCode) {
        this.cropPhaseInfoCode = cropPhaseInfoCode;
    }

    public String getCropCode() {
        return cropCode;
    }

    public void setCropCode(String cropCode) {
        this.cropCode = cropCode;
    }

    public String getWorkphase() {
        return workphase;
    }

    public void setWorkphase(String workphase) {
        this.workphase = workphase;
    }

    public String getWorkphaseName() {
        return workphaseName;
    }

    public void setWorkphaseName(String workphaseName) {
        this.workphaseName = workphaseName;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
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

    // CropCate 모델 복사
    public void CopyData(CropWorkStage param)
    {
        this.cropPhaseInfoCode = param.getCropPhaseInfoCode();
        this.cropCode = param.getCropCode();
        this.workphase = param.getWorkphase();
        this.workphaseName = param.getWorkphaseName();
        this.useStatus = param.getUseStatus();
        this.regMemberId = param.getRegMemberId();
        this.regDate = param.getRegDate();
        this.modMemberId = param.getModMemberId();
		this.modDate = param.getModDate();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CropWorkStage [cropPhaseInfoCode=");
		builder.append(cropPhaseInfoCode);
		builder.append(", cropCode=");
		builder.append(cropCode);
		builder.append(", workphase=");
		builder.append(workphase);
		builder.append(", workphaseName=");
		builder.append(workphaseName);
		builder.append(", useStatus=");
		builder.append(useStatus);
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