package easyfarm.domain.plan;


public class ProductGain {
	 // 생산물수확계획코드
    private String productGainCode;

    // 프로젝트코드
    private String projectCode;

    // 품목코드
    private String stockItemCode;

    // 수확수량
    private String gainQuantity;

    // 수확용량소계
    private String gainQuantityCapacity;

    // 수확일
    private String gainDate;

    // 작성자농가회원코드
    private String regFarmMemberCode;

    // 작성일
    private String regDate;

    // 수정자농가회원코드
    private String modFarmMemberCode;

    // 수정일
    private String modDate;

    public String getProductGainCode() {
        return productGainCode;
    }

    public void setProductGainCode(String productGainCode) {
        this.productGainCode = productGainCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getStockItemCode() {
        return stockItemCode;
    }

    public void setStockItemCode(String stockItemCode) {
        this.stockItemCode = stockItemCode;
    }

    public String getGainQuantity() {
        return gainQuantity;
    }

    public void setGainQuantity(String gainQuantity) {
        this.gainQuantity = gainQuantity;
    }

    public String getGainQuantityCapacity() {
        return gainQuantityCapacity;
    }

    public void setGainQuantityCapacity(String gainQuantityCapacity) {
        this.gainQuantityCapacity = gainQuantityCapacity;
    }

    public String getGainDate() {
        return gainDate;
    }

    public void setGainDate(String gainDate) {
        this.gainDate = gainDate;
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
