package easyfarm.domain.result;

import easyfarm.domain.plan.ResourceUsePlan;

public class ResourceUsePlanResult {
	// 농자재사용계획대비실행코드
    private String resourceUseplanRunCode;

    // 농자재사용계획코드
    private String resourceUseplanCode;

    // 재고현황코드
    private String stockStatusCode;

    // 사용수량
    private String stockItemUseQuantity;

    // 사용용량소계
    private String stockItemUseQuantityTotal;

    // 사용량금액환산
    private String stockItemUseQuantityConversionPay;

    // 사용일
    private String stockItemUseDate;

    // 실행완료여부
    private String completeStatus;

    // 작성자농가회원코드
    private String regMemberId;

    // 작성일
    private String regDate;
    
    // 계획
    private ResourceUsePlan resourceUsePlan;
    
    
	public String getResourceUseplanRunCode() {
		return resourceUseplanRunCode;
	}

	public void setResourceUseplanRunCode(String resourceUseplanRunCode) {
		this.resourceUseplanRunCode = resourceUseplanRunCode;
	}

	public String getResourceUseplanCode() {
		return resourceUseplanCode;
	}

	public void setResourceUseplanCode(String resourceUseplanCode) {
		this.resourceUseplanCode = resourceUseplanCode;
	}

	public String getStockStatusCode() {
		return stockStatusCode;
	}

	public void setStockStatusCode(String stockStatusCode) {
		this.stockStatusCode = stockStatusCode;
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

	public String getCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(String completeStatus) {
		this.completeStatus = completeStatus;
	}

	public String getregMemberId() {
		return regMemberId;
	}

	public void setregMemberId(String regMemberId) {
		this.regMemberId = regMemberId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRegMemberId() {
		return regMemberId;
	}

	public void setRegMemberId(String regMemberId) {
		this.regMemberId = regMemberId;
	}

	public ResourceUsePlan getResourceUsePlan() {
		return resourceUsePlan;
	}

	public void setResourceUsePlan(ResourceUsePlan resourceUsePlan) {
		this.resourceUsePlan = resourceUsePlan;
	}

    
	
}
