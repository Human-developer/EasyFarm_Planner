package easyfarm.domain.plan;

public class ResourceUsecapacity {

    // 농자재소모현황코드 
    private String resourceUsecapacityCode;

    // 농가별농자재매입지출계획코드 
    private String resourcePayCode;

    // 잔여수량 
    private String resourceRetainQuantity;

    // 잔여용량소계 
    private String resourceRetainQuantityCapacityExtra;

    // 사용가능여부 
    private String availableStatus;

    // 작성자아이디 
    private String regMemberId;

    // 작성일 
    private String regDate;

    public String getResourceUsecapacityCode() {
        return resourceUsecapacityCode;
    }

    public void setResourceUsecapacityCode(String resourceUsecapacityCode) {
        this.resourceUsecapacityCode = resourceUsecapacityCode;
    }

    public String getResourcePayCode() {
        return resourcePayCode;
    }

    public void setResourcePayCode(String resourcePayCode) {
        this.resourcePayCode = resourcePayCode;
    }

    public String getResourceRetainQuantity() {
        return resourceRetainQuantity;
    }

    public void setResourceRetainQuantity(String resourceRetainQuantity) {
        this.resourceRetainQuantity = resourceRetainQuantity;
    }

    public String getResourceRetainQuantityCapacityExtra() {
        return resourceRetainQuantityCapacityExtra;
    }

    public void setResourceRetainQuantityCapacityExtra(String resourceRetainQuantityCapacityExtra) {
        this.resourceRetainQuantityCapacityExtra = resourceRetainQuantityCapacityExtra;
    }

    public String getAvailableStatus() {
        return availableStatus;
    }

    public void setAvailableStatus(String availableStatus) {
        this.availableStatus = availableStatus;
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
