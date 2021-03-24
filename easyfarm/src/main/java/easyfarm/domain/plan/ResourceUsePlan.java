package easyfarm.domain.plan;


public class ResourceUsePlan {
	// 농자재사용계획코드
    private String resourceUseplanCode;

    // 농자재소모현황코드
    private String resourceUsecapacityCode;

    // 기간별작업단계계획코드
    private String planWorkphaseCode;

    // 작업단계별상세항목코드
    private String planWorkphaseCateCode;

    // 사용수량
    private String stockItemUseQuantity;

    // 사용용량소계
    private String stockItemUseQuantityTotal;

    // 사용량금액환산
    private String stockItemUseQuantityConversionPay;

    // 사용일
    private String stockItemUseDate;

    // 작성자농가회원코드
    private String regFarmMemberCode;

    // 작성일
    private String regDate;

    // 수정자농가회원코드
    private String modFarmMemberCode;

    // 수정일
    private String modDate;

    public String getResourceUseplanCode() {
        return resourceUseplanCode;
    }

    public void setResourceUseplanCode(String resourceUseplanCode) {
        this.resourceUseplanCode = resourceUseplanCode;
    }

    public String getResourceUsecapacityCode() {
        return resourceUsecapacityCode;
    }

    public void setResourceUsecapacityCode(String resourceUsecapacityCode) {
        this.resourceUsecapacityCode = resourceUsecapacityCode;
    }

    public String getPlanWorkphaseCode() {
        return planWorkphaseCode;
    }

    public void setPlanWorkphaseCode(String planWorkphaseCode) {
        this.planWorkphaseCode = planWorkphaseCode;
    }

    public String getPlanWorkphaseCateCode() {
        return planWorkphaseCateCode;
    }

    public void setPlanWorkphaseCateCode(String planWorkphaseCateCode) {
        this.planWorkphaseCateCode = planWorkphaseCateCode;
    }

    public String getStockItemUseQuantity() {
        return stockItemUseQuantity;
    }

    public void setStockItemUseQuantity(String stockItemUseQuantity) {
        this.stockItemUseQuantity = stockItemUseQuantity;
    }

    public String getStockItemUseQuantityTotal() {
        return stockItemUseQuantityTotal;
    }

    public void setStockItemUseQuantityTotal(String stockItemUseQuantityTotal) {
        this.stockItemUseQuantityTotal = stockItemUseQuantityTotal;
    }

    public String getStockItemUseQuantityConversionPay() {
        return stockItemUseQuantityConversionPay;
    }

    public void setStockItemUseQuantityConversionPay(String stockItemUseQuantityConversionPay) {
        this.stockItemUseQuantityConversionPay = stockItemUseQuantityConversionPay;
    }

    public String getStockItemUseDate() {
        return stockItemUseDate;
    }

    public void setStockItemUseDate(String stockItemUseDate) {
        this.stockItemUseDate = stockItemUseDate;
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
