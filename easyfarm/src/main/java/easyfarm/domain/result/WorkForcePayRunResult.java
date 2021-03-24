package easyfarm.domain.result;


public class WorkForcePayRunResult {
	// 인건비지출계획대비실행코드
    private String workforcePayRunCode;

    // 인건비지출계획코드
    private String workforcePayCode;

    // 근무일
    private String workforceRunDate;

    // 근무인원
    private String workforceRunQuantity;

    // 근무시작시간
    private String workforceRunBegin;

    // 근무종료시간
    private String workforceRunEnd;

    // 일일인건비
    private String workforcePayRunDay;

    // 실행완료여부
    private String completeStatus;

    // 인건비합계
    private String workforcePayRunTotal;

    // 작성자농가회원코드
    private String regFarmMemberCode;

    // 작성일
    private String regDate;

    // 수정자농가회원코드
    private String modFarmMemberCode;

    // 수정일
    private String modDate;

    public String getWorkforcePayRunCode() {
        return workforcePayRunCode;
    }

    public void setWorkforcePayRunCode(String workforcePayRunCode) {
        this.workforcePayRunCode = workforcePayRunCode;
    }

    public String getWorkforcePayCode() {
        return workforcePayCode;
    }

    public void setWorkforcePayCode(String workforcePayCode) {
        this.workforcePayCode = workforcePayCode;
    }

    public String getWorkforceRunDate() {
        return workforceRunDate;
    }

    public void setWorkforceRunDate(String workforceRunDate) {
        this.workforceRunDate = workforceRunDate;
    }

    public String getWorkforceRunQuantity() {
        return workforceRunQuantity;
    }

    public void setWorkforceRunQuantity(String workforceRunQuantity) {
        this.workforceRunQuantity = workforceRunQuantity;
    }

    public String getWorkforceRunBegin() {
        return workforceRunBegin;
    }

    public void setWorkforceRunBegin(String workforceRunBegin) {
        this.workforceRunBegin = workforceRunBegin;
    }

    public String getWorkforceRunEnd() {
        return workforceRunEnd;
    }

    public void setWorkforceRunEnd(String workforceRunEnd) {
        this.workforceRunEnd = workforceRunEnd;
    }

    public String getWorkforcePayRunDay() {
        return workforcePayRunDay;
    }

    public void setWorkforcePayRunDay(String workforcePayRunDay) {
        this.workforcePayRunDay = workforcePayRunDay;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getWorkforcePayRunTotal() {
        return workforcePayRunTotal;
    }

    public void setWorkforcePayRunTotal(String workforcePayRunTotal) {
        this.workforcePayRunTotal = workforcePayRunTotal;
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
