package easyfarm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.BoardVO;

@Mapper
public interface BoardMapper {
	 // 게시물 리스트 조회
    public List<BoardVO> selectBoardList() ;
    
    // 게시물 등록
    public int insertBoard(BoardVO boardVO) ;
    
    // 게시물 수정
    public int updateBoard(BoardVO boardVO) ;
    
    // 게시물 삭제
    public void deleteBoard(BoardVO boardVO) ;
    
    // 게시물 조회
    public BoardVO selectBoardByCode(String code) ;
    public boolean passCheck(String code, String password);
    
}
