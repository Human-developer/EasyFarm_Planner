package easyfarm.domain.inventory;

public class StockStatus {
	private String stockStatusCode;
	private String farmResourcePayRunCode;
	private String stockRemainQuantity;
	private String stockRemainQuantityCapacityExtra;
	private String availableStatus;
	private String regMemberId;
	private String regDate;
	private FarmResourcePayRun farmResourcePayRun;
	public String getStockStatusCode() {
		return stockStatusCode;
	}
	public void setStockStatusCode(String stockStatusCode) {
		this.stockStatusCode = stockStatusCode;
	}
	public String getFarmResourcePayRunCode() {
		return farmResourcePayRunCode;
	}
	public void setFarmResourcePayRunCode(String farmResourcePayRunCode) {
		this.farmResourcePayRunCode = farmResourcePayRunCode;
	}
	public String getStockRemainQuantity() {
		return stockRemainQuantity;
	}
	public void setStockRemainQuantity(String stockRemainQuantity) {
		this.stockRemainQuantity = stockRemainQuantity;
	}
	public String getStockRemainQuantityCapacityExtra() {
		return stockRemainQuantityCapacityExtra;
	}
	public void setStockRemainQuantityCapacityExtra(String stockRemainQuantityCapacityExtra) {
		this.stockRemainQuantityCapacityExtra = stockRemainQuantityCapacityExtra;
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
	public FarmResourcePayRun getFarmResourcePayRun() {
		return farmResourcePayRun;
	}
	public void setFarmResourcePayRun(FarmResourcePayRun farmResourcePayRun) {
		this.farmResourcePayRun = farmResourcePayRun;
	}
	
}
