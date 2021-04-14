package easyfarm.domain;


public class FreeBoard {
	private int commentsNum;		//댓글번호
	private String commentsBoard;	//댓글달 게시판
	private String memberId;		//댓글 작성자 아이디
	private String commentsContent;	//댓글 내용
	private String commentsDate;	//댓글 작성일
	private int boardNum;			//게시판 번호
	private String boardTitle;		//게시판 제목
	private String boardContent;	//게시판 내용
	private String boardDate;		//게시판 작성일
	private String boardId; 		//게시판 작성자 아이디
	private String boardGetNum; 	//게시판 조회수
	private String memberName; 		//게시판 작성자 이름
	
	
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getCommentsNum() {
		return commentsNum;
	}
	public void setCommentsNum(int commentsNum) {
		this.commentsNum = commentsNum;
	}
	public String getCommentsBoard() {
		return commentsBoard;
	}
	public void setCommentsBoard(String commentsBoard) {
		this.commentsBoard = commentsBoard;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCommentsContent() {
		return commentsContent;
	}
	public void setCommentsContent(String commentsContent) {
		this.commentsContent = commentsContent;
	}
	public String getCommentsDate() {
		return commentsDate;
	}
	public void setCommentsDate(String commentsDate) {
		this.commentsDate = commentsDate;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardGetNum() {
		return boardGetNum;
	}
	public void setBoardGetNum(String boardGetNum) {
		this.boardGetNum = boardGetNum;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FreeBoard [commentsNum=");
		builder.append(commentsNum);
		builder.append(", commentsBoard=");
		builder.append(commentsBoard);
		builder.append(", memberId=");
		builder.append(memberId);
		builder.append(", commentsContent=");
		builder.append(commentsContent);
		builder.append(", commentsDate=");
		builder.append(commentsDate);
		builder.append(", boardNum=");
		builder.append(boardNum);
		builder.append(", boardTitle=");
		builder.append(boardTitle);
		builder.append(", boardContent=");
		builder.append(boardContent);
		builder.append(", boardDate=");
		builder.append(boardDate);
		builder.append(", boardId=");
		builder.append(boardId);
		builder.append(", boardGetNum=");
		builder.append(boardGetNum);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	

	
}
