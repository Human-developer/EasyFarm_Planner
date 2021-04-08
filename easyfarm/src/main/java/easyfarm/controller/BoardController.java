package easyfarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping("/board/qnaList")
	public String boardList(Model model) {
		
		return"views/board/qnaboardlist";
	
	}
	@GetMapping("/board/qnaDetail")
	public String boardDetail(Model model) {
		
		return"views/board/qnaboarddetail";
		
	}
	@GetMapping("/board/qnaInsert")
	public String boardInser(Model model) {
		
		return"views/board/qnaboardinsert";
		
	}
	@GetMapping("/board/qnaModify")
	public String boardModify(Model model) {
		
		return"views/board/qnaboardmodify";
		
	}
	
}
