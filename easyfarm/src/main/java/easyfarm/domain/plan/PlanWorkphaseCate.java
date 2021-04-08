package easyfarm.domain.plan;

public class PlanWorkphaseCate {

    // 작업단계별상세항목코드 
    private String planWorkphaseCateCode;

    // 프로젝트별계획관리코드 
    private String projectPlanCode;

    // 기간별작업단계계획코드 
    private String planWorkphaseCode;

    // 프로젝트별작업단계상세항목코드 
    private String farmWorkphaseCateCode;

    // 상세항목계획시작일 
    private String planWorkphaseCateBegin;

    // 상세항목계획종료일 
    private String planWorkphaseCateEnd;

    // 이벤트배경색상
    private String planWorkphaseColor;
    
    // 이벤트글자색상
    private String planWorkphaseTextColor;
    
    // 작성자아이디 
    private String regMemberId;

    // 작성일 
    private String regDate;

    // 실행여부 
    private String runStatus;

    public String getPlanWorkphaseCateCode() {
        return planWorkphaseCateCode;
    }

    public void setPlanWorkphaseCateCode(String planWorkphaseCateCode) {
        this.planWorkphaseCateCode = planWorkphaseCateCode;
    }

    public String getProjectPlanCode() {
        return projectPlanCode;
    }

    public void setProjectPlanCode(String projectPlanCode) {
        this.projectPlanCode = projectPlanCode;
    }

    public String getPlanWorkphaseCode() {
        return planWorkphaseCode;
    }

    public void setPlanWorkphaseCode(String planWorkphaseCode) {
        this.planWorkphaseCode = planWorkphaseCode;
    }

    public String getFarmWorkphaseCateCode() {
        return farmWorkphaseCateCode;
    }

    public void setFarmWorkphaseCateCode(String farmWorkphaseCateCode) {
        this.farmWorkphaseCateCode = farmWorkphaseCateCode;
    }

    public String getPlanWorkphaseCateBegin() {
		return planWorkphaseCateBegin;
	}

	public void setPlanWorkphaseCateBegin(String planWorkphaseCateBegin) {
		this.planWorkphaseCateBegin = planWorkphaseCateBegin;
	}

	public String getPlanWorkphaseCateEnd() {
		return planWorkphaseCateEnd;
	}

	public void setPlanWorkphaseCateEnd(String planWorkphaseCateEnd) {
		this.planWorkphaseCateEnd = planWorkphaseCateEnd;
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

    public String getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    public String getPlanWorkphaseColor() {
		return planWorkphaseColor;
	}

	public void setPlanWorkphaseColor(String planWorkphaseColor) {
		this.planWorkphaseColor = planWorkphaseColor;
	}

	public String getPlanWorkphaseTextColor() {
		return planWorkphaseTextColor;
	}

	public void setPlanWorkphaseTextColor(String planWorkphaseTextColor) {
		this.planWorkphaseTextColor = planWorkphaseTextColor;
	}

	// PlanWorkphaseCate 모델 복사
    public void CopyData(PlanWorkphaseCate param)
    {
        this.planWorkphaseCateCode = param.getPlanWorkphaseCateCode();
        this.projectPlanCode = param.getProjectPlanCode();
        this.planWorkphaseCode = param.getPlanWorkphaseCode();
        this.farmWorkphaseCateCode = param.getFarmWorkphaseCateCode();
        this.planWorkphaseCateBegin = param.getPlanWorkphaseCateBegin();
        this.planWorkphaseCateEnd = param.getPlanWorkphaseCateEnd();
        this.regMemberId = param.getRegMemberId();
        this.regDate = param.getRegDate();
        this.runStatus = param.getRunStatus();
    }
	
}
