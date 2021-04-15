package easyfarm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import easyfarm.domain.BoardVO;
import easyfarm.domain.Machine;
import easyfarm.service.BoardService;

@Controller
public class BoardController {

	 @Autowired
	 private BoardService boardService;
	 @Autowired
	 private JavaMailSender javaMailSender;
	
	 
	 
	
	@PostMapping("/ajax/passCheck")
	public @ResponseBody boolean passCheck(
			@RequestParam(value = "code",required = false)String code,
			@RequestParam(value = "password",required = false)String password
			) {
		boolean result = false;
		
		result = boardService.passCheck(code,password);
		
		return result;
	}
	
   
//    게시판조회
    @GetMapping("/board/qnaList")
	public String boardList(Model model) throws Exception {
		List<BoardVO> selectBoardList = new ArrayList<BoardVO>();
		selectBoardList = boardService.selectBoardList();
		model.addAttribute("selectBoardList", selectBoardList);
		System.out.println(selectBoardList);
		return "views/board/qnaboardlist";
	
	}
    
    @GetMapping("/board/qnaInsert")
   	public String boardInsert(Model model) {
   		
   		return "views/board/qnaboardinsert";
   		
   	}
    @PostMapping("/board/qnaInsert")
    public String boardInsert(Model model,BoardVO boardVo) {
    	boardVo.setPhoneNum(boardVo.getPhoneNum().replace(",", "-"));
    	boardService.insertBoard(boardVo);
    	
    	System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+boardVo+"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    	return "redirect:/board/qnaList";
    	
    }
    
   
    
    @GetMapping("/board/qnaboarddetail")
	public String boardDetail(Model model,
    			@RequestParam(name = "code", required = false) String code) throws Exception {
    		BoardVO BoardVO = boardService.selectBoardByCode(code);
    		model.addAttribute("BoardVO", BoardVO);
		return"views/board/qnaboarddetail";
		
	}
    
    public Runnable createRunnable(final SimpleMailMessage message) {
		 return new Runnable() {
			 
		 	 @Override
			 public void run() {
				 javaMailSender.send(message);
			 }			
		 };
	 }
    
    @GetMapping("/board/qnaModify")
	public String boardModify(Model model, @RequestParam(value = "code",required = false) String code) {
    	BoardVO BoardVO = boardService.selectBoardByCode(code);
		model.addAttribute("BoardVO", BoardVO);
		return"views/board/qnaboardmodify";
	}
    
    @PostMapping("/board/qnaModify")
    public String boardModify(Model model, BoardVO boardVo) {
    	int result = boardService.updateBoard(boardVo);
    	
    	System.out.println("\n\n\n\n\n\n\n\n\n\n\n"+boardVo.getConAnswer() + boardVo.getCode());
    	
    	if(result > 0) {    		
    		SimpleMailMessage message = new SimpleMailMessage();
    		message.setTo(boardVo.getEmail());  
    		message.setSubject("고객님께서 문의하신 "+boardVo.getTitle() + "에 대한 답변입니다.");
    		message.setText("문의내용  : "+boardVo.getContent()+"\n답변  : "+ boardVo.getConAnswer());
    		Thread thread = new Thread(createRunnable(message));
			thread.start();
			thread = null;
    	}
    	return "redirect:/board/qnaList";
    }
    
    
	

	
	
}
