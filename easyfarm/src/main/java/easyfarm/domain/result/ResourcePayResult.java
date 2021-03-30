package easyfarm.domain.result;

import easyfarm.domain.plan.ResourcePay;

public class ResourcePayResult {
	// 농가별농자재매입지출계획대비실행코드
    private String farmResourcePayRunCode;

    // 농가별농자재매입지출계획코드
    private String farmResourcePayCode;

    // 농가코드
    private String farmCode;

    // 거래처코드
    private String clientCode;

    // 품목코드
    private String stockItemCode;

    // 입고수량
    private String stockItemIncomeQuantity;

    // 입고용량소계
    private String stockItemIncomeQuantityTotal;

    // 입고단가
    private String stockItemIncomePerPay;

    // 총구매비
    private String stockItemTotalPay;

    // 입고일
    private String stockItemIncomeDate;

    // 실행완료여부
    private String completeStatus;

    // 작성자농가회원코드
    private String regMemberId;

    // 작성일
    private String regDate;

    //계획
    private ResourcePay resourcePayPlan;

    public String getFarmResourcePayRunCode() {
        return farmResourcePayRunCode;
    }

    public void setFarmResourcePayRunCode(String farmResourcePayRunCode) {
        this.farmResourcePayRunCode = farmResourcePayRunCode;
    }

    public String getFarmResourcePayCode() {
        return farmResourcePayCode;
    }

    public void setFarmResourcePayCode(String farmResourcePayCode) {
        this.farmResourcePayCode = farmResourcePayCode;
    }

    public String getFarmCode() {
        return farmCode;
    }

    public void setFarmCode(String farmCode) {
        this.farmCode = farmCode;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getStockItemCode() {
        return stockItemCode;
    }

    public void setStockItemCode(String stockItemCode) {
        this.stockItemCode = stockItemCode;
    }

    public String getStockItemIncomeQuantity() {
        return stockItemIncomeQuantity;
    }

    public void setStockItemIncomeQuantity(String stockItemIncomeQuantity) {
        this.stockItemIncomeQuantity = stockItemIncomeQuantity;
    }

    public String getStockItemIncomeQuantityTotal() {
        return stockItemIncomeQuantityTotal;
    }

    public void setStockItemIncomeQuantityTotal(String stockItemIncomeQuantityTotal) {
        this.stockItemIncomeQuantityTotal = stockItemIncomeQuantityTotal;
    }

    public String getStockItemIncomePerPay() {
        return stockItemIncomePerPay;
    }

    public void setStockItemIncomePerPay(String stockItemIncomePerPay) {
        this.stockItemIncomePerPay = stockItemIncomePerPay;
    }

    public String getStockItemTotalPay() {
        return stockItemTotalPay;
    }

    public void setStockItemTotalPay(String stockItemTotalPay) {
        this.stockItemTotalPay = stockItemTotalPay;
    }

    public String getStockItemIncomeDate() {
        return stockItemIncomeDate;
    }

    public void setStockItemIncomeDate(String stockItemIncomeDate) {
        this.stockItemIncomeDate = stockItemIncomeDate;
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


	public ResourcePay getResourcePay() {
		return resourcePayPlan;
	}

	public void setResourcePay(ResourcePay resourcePay) {
		this.resourcePayPlan = resourcePay;
	}

	public String getRegMemberId() {
		return regMemberId;
	}

	public void setRegMemberId(String regMemberId) {
		this.regMemberId = regMemberId;
	}

	public ResourcePay getResourcePayPlan() {
		return resourcePayPlan;
	}

	public void setResourcePayPlan(ResourcePay resourcePayPlan) {
		this.resourcePayPlan = resourcePayPlan;
	}
	
}
