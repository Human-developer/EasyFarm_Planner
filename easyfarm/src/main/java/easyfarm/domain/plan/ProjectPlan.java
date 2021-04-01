package easyfarm.domain.plan;

public class ProjectPlan {
	// 프로젝트별계획관리코드 
    private String projectPlanCode;

    // 프로젝트코드 
    private String projectCode;

    // 계획차수 
    private String projectPlanN;

    // 작성자아이디 
    private String regMemberId;

    // 작성일 
    private String regDate;

    public String getProjectPlanCode() {
        return projectPlanCode;
    }

    public void setProjectPlanCode(String projectPlanCode) {
        this.projectPlanCode = projectPlanCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectPlanN() {
        return projectPlanN;
    }

    public void setProjectPlanN(String projectPlanN) {
        this.projectPlanN = projectPlanN;
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

    // TbProjectPlan 모델 복사
    public void CopyData(ProjectPlan param)
    {
        this.projectPlanCode = param.getProjectPlanCode();
        this.projectCode = param.getProjectCode();
        this.projectPlanN = param.getProjectPlanN();
        this.regMemberId = param.getRegMemberId();
        this.regDate = param.getRegDate();
    }
}
