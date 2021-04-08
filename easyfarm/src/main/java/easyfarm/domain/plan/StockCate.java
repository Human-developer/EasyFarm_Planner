package easyfarm.domain.plan;

public class StockCate {

    // 품목카테고리코드 
    private String stockCateCode;

    // 품목카테고리명 
    private String stockCateName;

    // 작성자아이디 
    private String regMemberId;

    // 작성일 
    private String regDate;

    public String getStockCateCode() {
        return stockCateCode;
    }

    public void setStockCateCode(String stockCateCode) {
        this.stockCateCode = stockCateCode;
    }

    public String getStockCateName() {
        return stockCateName;
    }

    public void setStockCateName(String stockCateName) {
        this.stockCateName = stockCateName;
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

    // TbStockCate 모델 복사
    public void CopyData(StockCate param)
    {
        this.stockCateCode = param.getStockCateCode();
        this.stockCateName = param.getStockCateName();
        this.regMemberId = param.getRegMemberId();
        this.regDate = param.getRegDate();
    }
	
}
