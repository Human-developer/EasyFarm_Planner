package easyfarm.domain;

public class BoardVO {
	  /** 글 번호 **/
    private int code;
    
    /** 글 제목 **/
    private String title ;
    
    /** 글 내용 **/
    private String content;
    /** 작성자 **/
    private String regMemberId;
    
    /** 등록시간 **/
    private String regDate;
    
    private String phoneNum;
    private String email;
    private String password;
    private String conAnswer;
    /*
     * 계층형 게시판을 위한 추가 필드
     * originNo, groupOrd, groupLayer 
     */
    
    /** 원글 번호 **/
    private int originNo;
    
   

	/** 원글(답글포함)에 대한 순서 **/
    private int groupOrd;
 
    /** 답글 계층 **/
    private int groupLayer;

    
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public int getOriginNo() {
		return originNo;
	}

	public void setOriginNo(int originNo) {
		this.originNo = originNo;
	}

	public int getGroupOrd() {
		return groupOrd;
	}

	public void setGroupOrd(int groupOrd) {
		this.groupOrd = groupOrd;
	}

	public int getGroupLayer() {
		return groupLayer;
	}

	public void setGroupLayer(int groupLayer) {
		this.groupLayer = groupLayer;
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

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getConAnswer() {
		return conAnswer;
	}

	public void setConAnswer(String conAnswer) {
		this.conAnswer = conAnswer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardVO [code=");
		builder.append(code);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", regMemberId=");
		builder.append(regMemberId);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", phoneNum=");
		builder.append(phoneNum);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", conAnswer=");
		builder.append(conAnswer);
		builder.append(", originNo=");
		builder.append(originNo);
		builder.append(", groupOrd=");
		builder.append(groupOrd);
		builder.append(", groupLayer=");
		builder.append(groupLayer);
		builder.append("]");
		return builder.toString();
	}




}
