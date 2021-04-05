package easyfarm.domain.plan;

public class PlanWorkphase {

    // 기간별작업단계계획코드 
    private String planWorkphaseCode;

    // 프로젝트별계획관리코드 
    private String projectPlanCode;

    // 프로젝트별작업단계코드 
    private String projectWorkphaseCode;

    // 작업단계계획시작일 
    private String planWorkphaseBegin;

    // 작업단계계획종료일 
    private String planWorkphaseEnd;
    
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

    public String getPlanWorkphaseCode() {
        return planWorkphaseCode;
    }

    public void setPlanWorkphaseCode(String planWorkphaseCode) {
        this.planWorkphaseCode = planWorkphaseCode;
    }

    public String getProjectPlanCode() {
        return projectPlanCode;
    }

    public void setProjectPlanCode(String projectPlanCode) {
        this.projectPlanCode = projectPlanCode;
    }

    public String getProjectWorkphaseCode() {
        return projectWorkphaseCode;
    }

    public void setProjectWorkphaseCode(String projectWorkphaseCode) {
        this.projectWorkphaseCode = projectWorkphaseCode;
    }

    public String getPlanWorkphaseBegin() {
        return planWorkphaseBegin;
    }

    public void setPlanWorkphaseBegin(String planWorkphaseBegin) {
        this.planWorkphaseBegin = planWorkphaseBegin;
    }

    public String getPlanWorkphaseEnd() {
        return planWorkphaseEnd;
    }

    public void setPlanWorkphaseEnd(String planWorkphaseEnd) {
        this.planWorkphaseEnd = planWorkphaseEnd;
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

	// PlanWorkphase 모델 복사
    public void CopyData(PlanWorkphase param)
    {
        this.planWorkphaseCode = param.getPlanWorkphaseCode();
        this.projectPlanCode = param.getProjectPlanCode();
        this.projectWorkphaseCode = param.getProjectWorkphaseCode();
        this.planWorkphaseBegin = param.getPlanWorkphaseBegin();
        this.planWorkphaseEnd = param.getPlanWorkphaseEnd();
        this.regMemberId = param.getRegMemberId();
        this.regDate = param.getRegDate();
        this.runStatus = param.getRunStatus();
    }
	
}
