package easyfarm.domain;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
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
	
	
}
