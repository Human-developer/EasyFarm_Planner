package easyfarm.domain.inventory;

import easyfarm.domain.plan.StockItem;

public class FarmResourcePayRun {
	private String farmResourcePayRunCode;
	private String farmResourcePayCode;
	private String farmCode;
	private String clientCode;
	private String stockItemCode;
	private String stockItemIncomeQuantity;
	private String stockItemIncomeQuantityTotal;
	private String stockItemIncomePerPay;
	private String stockItemTotalPay;
	private String stockItemIncomeDate;
	private String completeStatus;
	private String regMemberId;
	private String regDate;
	private StockItem stockItem;
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
	public StockItem getStockItem() {
		return stockItem;
	}
	public void setStockItem(StockItem stockItem) {
		this.stockItem = stockItem;
	}
	
}
