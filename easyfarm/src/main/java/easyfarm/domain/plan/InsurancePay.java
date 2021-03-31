package easyfarm.domain.plan;

public class InsurancePay {
	// 보험료지출계획코드
    private String insurePayCode;

    // 프로젝트코드
    private String projectCode;

    // 보험사
    private String insureCompany;

    // 보험명
    private String insureName;

    // 보장내용
    private String insureDatail;

    // 가입면적
    private String insureArea;

    // 예상총보험비
    private String insurePayTotal;

    // 작성자농가회원코드
    private String regFarmMemberCode;

    // 작성일
    private String regDate;

    // 수정자농가회원코드
    private String modFarmMemberCode;

    // 수정일
    private String modDate;

    public String getInsurePayCode() {
        return insurePayCode;
    }

    public void setInsurePayCode(String insurePayCode) {
        this.insurePayCode = insurePayCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getInsureCompany() {
        return insureCompany;
    }

    public void setInsureCompany(String insureCompany) {
        this.insureCompany = insureCompany;
    }

    public String getInsureName() {
        return insureName;
    }

    public void setInsureName(String insureName) {
        this.insureName = insureName;
    }

    public String getInsureDatail() {
        return insureDatail;
    }

    public void setInsureDatail(String insureDatail) {
        this.insureDatail = insureDatail;
    }

    public String getInsureArea() {
        return insureArea;
    }

    public void setInsureArea(String insureArea) {
        this.insureArea = insureArea;
    }

    public String getInsurePayTotal() {
        return insurePayTotal;
    }

    public void setInsurePayTotal(String insurePayTotal) {
        this.insurePayTotal = insurePayTotal;
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
