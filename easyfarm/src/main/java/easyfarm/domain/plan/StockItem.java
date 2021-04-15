package easyfarm.domain.plan;

public class StockItem {
	
    // 품목코드 
    private String stockItemCode;

    // 농가코드 
    private String farmCode;

    // 품목카테고리코드 
    private String stockCateCode;

    // 품목명 
    private String stockItemName;

    // 품목수량 
    private String stockItemQuantity;

    // 수량단위 
    private String stockItemQuantityUnit;

    // 품목용량 
    private String stockItemQuantityCapacity;

    // 용량단위 
    private String stockItemQuantityCapacityUnit;
    
    //사용유무
  	private String useStatus;

    // 작성자아이디 
    private String regMemberId;

    // 작성일 
    private String regDate;
    
    //품목카테고리 domain
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
    
    public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
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

	// TbStockItem 모델 복사
    public void CopyData(StockItem param)
    {
        this.stockItemCode = param.getStockItemCode();
        this.farmCode = param.getFarmCode();
        this.stockCateCode = param.getStockCateCode();
        this.stockItemName = param.getStockItemName();
        this.stockItemQuantity = param.getStockItemQuantity();
        this.stockItemQuantityUnit = param.getStockItemQuantityUnit();
        this.stockItemQuantityCapacity = param.getStockItemQuantityCapacity();
        this.stockItemQuantityCapacityUnit = param.getStockItemQuantityCapacityUnit();
        this.regMemberId = param.getRegMemberId();
        this.regDate = param.getRegDate();
    }
	
}
