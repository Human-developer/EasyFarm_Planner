package easyfarm.service;





import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import easyfarm.dao.MemberMapper;
import easyfarm.domain.FreeBoard;


@Service
@Transactional
public class FreeBoardService {
	
	private MemberMapper memberMapper;
	
	 FreeBoardService(MemberMapper memberMapper) { 
		 this.memberMapper = memberMapper;
	 }
	 
	 public List<FreeBoard> getFreeBoard(String boardId){
		 
		 List<FreeBoard> boardList = null;
		 
		 if(boardId != null && !"".equals(boardId)) {
			 boardList = memberMapper.getFreeBoard(boardId); 
		 }else {
			 boardList = memberMapper.getFreeBoard(null); 
		 }
		 
		 return boardList;
	 }
	 public List<FreeBoard> getCommentList(int boardNum){
		 
		 List<FreeBoard> commentList = memberMapper.getCommentList(boardNum); 
		 
		 return commentList;
	 }
	 public void updateBoardGetNum(int boardNum,int boardGetNum) {
		 memberMapper.updateBoardGetNum(boardNum,boardGetNum);
		 
	 }
	 public void addFreeBoard(FreeBoard board){
		 memberMapper.addFreeBoard(board);
		 
	 }
	 public FreeBoard getBoard(int boardNum){
		 
		 FreeBoard boardList = memberMapper.getBoard(boardNum); 
		 
		 return boardList;
	 }
	 public void addComment(String comment,int boardNum,String memberId) {
		 memberMapper.addComment(comment,boardNum,memberId);
	 }
	 public void modifyFreeBoard(FreeBoard board) {
		 memberMapper.modifyFreeBoard(board);
	 }
	 public void removeFreeBoard(int boardNum) {
		 memberMapper.removeFreeBoard(boardNum);
	 }
	 public void removeComment(int commentsNum) {
		 memberMapper.removeComment(commentsNum);
	 }
	
	
}
