package easyfarm.domain.plan;

public class ResourcePay {
	 // 농가별농자재매입지출계획코드
    private String resourcePayCode;

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

    // 작성자농가회원코드

    private String regMemberId;

    // 작성일
    private String regDate;


    public String getResourcePayCode() {
        return resourcePayCode;
    }

    public void setResourcePayCode(String resourcePayCode) {
        this.resourcePayCode = resourcePayCode;
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


}
