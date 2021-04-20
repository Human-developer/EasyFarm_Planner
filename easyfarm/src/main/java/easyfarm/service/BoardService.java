package easyfarm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import easyfarm.dao.BoardMapper;
import easyfarm.domain.BoardVO;

@Service
@Transactional
public class BoardService {
@Autowired
private BoardMapper boardMapper;
	public List<BoardVO> selectBoardList()   {
		List<BoardVO> selectBoardList = boardMapper.selectBoardList();
		return selectBoardList;
	}

	public int insertBoard(BoardVO boardVO) {
		
		return boardMapper.insertBoard(boardVO);
	}

	public BoardVO selectBoardByCode(String code)   {
		return boardMapper.selectBoardByCode(code);
	}

	public int updateBoard(BoardVO boardVO) {
		return boardMapper.updateBoard(boardVO);
	}
	
	public boolean passCheck(String code,String password) {
		
		return boardMapper.passCheck(code,password);
	}

	public int removeQnaBoard(String code) {
		return boardMapper.removeQnaBoard(code);
	}
	
	
}
