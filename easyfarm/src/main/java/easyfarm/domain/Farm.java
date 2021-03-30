package easyfarm.domain;

public class Farm {

	private String farmCode; 
	private String farmName; 
	private String ceoId; 
	private String farmMemberLevel;
	private String farmArea; 
	private String farmAddress; 
	private String farmPublicStatus; 
	private String farmRegDate;
	private String farmMemberCount;
	
	
	public String getFarmCode() {
		return farmCode;
	}
	public void setFarmCode(String farmCode) {
		this.farmCode = farmCode;
	}
	public String getFarmName() {
		return farmName;
	}
	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}
	public String getCeoId() {
		return ceoId;
	}
	public void setCeoId(String ceoId) {
		this.ceoId = ceoId;
	}
	public String getFarmMemberLevel() {
		return farmMemberLevel;
	}
	public void setFarmMemberLevel(String farmMemberLevel) {
		this.farmMemberLevel = farmMemberLevel;
	}
	public String getFarmArea() {
		return farmArea;
	}
	public void setFarmArea(String farmArea) {
		int area = Integer.parseInt(farmArea);
		
		
		this.farmArea = area+"";
	}
	public String getFarmAddress() {
		return farmAddress;
	}
	public void setFarmAddress(String farmAddress) {
		this.farmAddress = farmAddress;
	}
	public String getFarmPublicStatus() {
		return farmPublicStatus;
	}
	public void setFarmPublicStatus(String farmPublicStatus) {
		this.farmPublicStatus = farmPublicStatus;
	}
	public String getFarmRegDate() {
		return farmRegDate;
	}
	public void setFarmRegDate(String farmRegDate) {
		this.farmRegDate = farmRegDate;
	}
	public String getFarmMemberCount() {
		return farmMemberCount;
	}
	public void setFarmMemberCount(String farmMemberCount) {
		this.farmMemberCount = farmMemberCount;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Farm [farmCode=");
		builder.append(farmCode);
		builder.append(", farmName=");
		builder.append(farmName);
		builder.append(", ceoId=");
		builder.append(ceoId);
		builder.append(", farmMemberLevel=");
		builder.append(farmMemberLevel);
		builder.append(", farmArea=");
		builder.append(farmArea);
		builder.append(", farmAddress=");
		builder.append(farmAddress);
		builder.append(", farmPublicStatus=");
		builder.append(farmPublicStatus);
		builder.append(", farmRegDate=");
		builder.append(farmRegDate);
		builder.append(", farmMemberCount=");
		builder.append(farmMemberCount);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
}
