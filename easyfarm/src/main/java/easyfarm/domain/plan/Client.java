package easyfarm.domain.plan;

public class Client {

	// 거래처코드 
	private String clientCode;
	
	// 농가코드 
	private String farmCode;
	
	// 거래처명 
	private String clientName;
	
	// 거래처 전화번호 
	private String clientPhone;
	
	// 거래처 주소 
	private String clientAddress;
	
	// 거래처 계좌은행 
	private String clientAccountBank;
	
	// 거래처 계좌 
	private String clientAccount;
	
	// 거래처 메모 
	private String clientMemo;
	
	//사용유무
	private String useStatus;
	
	// 작성자아이디 
	private String regMemberId;
	
	// 작성일 
	private String regDate;
	
	public String getClientCode() {
	    return clientCode;
	}
	
	public void setClientCode(String clientCode) {
	    this.clientCode = clientCode;
	}
	
	public String getFarmCode() {
	    return farmCode;
	}
	
	public void setFarmCode(String farmCode) {
	    this.farmCode = farmCode;
	}
	
	public String getClientName() {
	    return clientName;
	}
	
	public void setClientName(String clientName) {
	    this.clientName = clientName;
	}
	
	public String getClientPhone() {
	    return clientPhone;
	}
	
	public void setClientPhone(String clientPhone) {
	    this.clientPhone = clientPhone;
	}
	
	public String getClientAddress() {
	    return clientAddress;
	}
	
	public void setClientAddress(String clientAddress) {
	    this.clientAddress = clientAddress;
	}
	
	public String getClientAccountBank() {
	    return clientAccountBank;
	}
	
	public void setClientAccountBank(String clientAccountBank) {
	    this.clientAccountBank = clientAccountBank;
	}
	
	public String getClientAccount() {
	    return clientAccount;
	}
	
	public void setClientAccount(String clientAccount) {
	    this.clientAccount = clientAccount;
	}
	
	public String getClientMemo() {
	    return clientMemo;
	}
	
	public void setClientMemo(String clientMemo) {
	    this.clientMemo = clientMemo;
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
	
	// Client 모델 복사
	public void CopyData(Client param)
	{
	    this.clientCode = param.getClientCode();
	    this.farmCode = param.getFarmCode();
	    this.clientName = param.getClientName();
	    this.clientPhone = param.getClientPhone();
	    this.clientAddress = param.getClientAddress();
	    this.clientAccountBank = param.getClientAccountBank();
	    this.clientAccount = param.getClientAccount();
	    this.clientMemo = param.getClientMemo();
	    this.regMemberId = param.getRegMemberId();
	    this.regDate = param.getRegDate();
	}
	
	
}
