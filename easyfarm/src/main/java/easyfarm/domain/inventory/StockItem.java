package easyfarm.domain.inventory;

public class StockItem {
	private String stockItemCode;
	private String farmCode;
	private String stockCateCode;
	private String stockItemName;
	private String stockItemQuantity;
	private String stockItemQuantityUnit;
	private String stockItemQuantityCapacity;
	private String stockItemQuantityCapacityUnit;
	private String regMemberId;
	private String regDate;
	private StockCate stockCate;
	public String getStockItemCode() {
		return stockItemCode;
	}
	public void setStockItemCode(String stockItemCode) {
		this.stockItemCode = stockItemCode;
	}
	public String getFarmCode() {
		return farmCode;
	}
	public void setFarmCode(String farmCode) {
		this.farmCode = farmCode;
	}
	public String getStockCateCode() {
		return stockCateCode;
	}
	public void setStockCateCode(String stockCateCode) {
		this.stockCateCode = stockCateCode;
	}
	public String getStockItemName() {
		return stockItemName;
	}
	public void setStockItemName(String stockItemName) {
		this.stockItemName = stockItemName;
	}
	public String getStockItemQuantity() {
		return stockItemQuantity;
	}
	public void setStockItemQuantity(String stockItemQuantity) {
		this.stockItemQuantity = stockItemQuantity;
	}
	public String getStockItemQuantityUnit() {
		return stockItemQuantityUnit;
	}
	public void setStockItemQuantityUnit(String stockItemQuantityUnit) {
		this.stockItemQuantityUnit = stockItemQuantityUnit;
	}
	public String getStockItemQuantityCapacity() {
		return stockItemQuantityCapacity;
	}
	public void setStockItemQuantityCapacity(String stockItemQuantityCapacity) {
		this.stockItemQuantityCapacity = stockItemQuantityCapacity;
	}
	public String getStockItemQuantityCapacityUnit() {
		return stockItemQuantityCapacityUnit;
	}
	public void setStockItemQuantityCapacityUnit(String stockItemQuantityCapacityUnit) {
		this.stockItemQuantityCapacityUnit = stockItemQuantityCapacityUnit;
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
	public StockCate getStockCate() {
		return stockCate;
	}
	public void setStockCate(StockCate stockCate) {
		this.stockCate = stockCate;
	}
	
}
