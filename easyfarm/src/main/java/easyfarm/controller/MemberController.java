package easyfarm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import easyfarm.domain.Member;
import easyfarm.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;

	@GetMapping("/member/login")
	public String login(Model model) {

		return "views/member/login";
	}

	@PostMapping("/member/login")
	public String login(@RequestParam(value = "memberId", required = false) String memberId,
			@RequestParam(value = "memberPw", required = false) String memberPw, HttpSession session) {
		if (memberId != null && !"".equals(memberId.trim()) && memberPw != null && !"".equals(memberPw.trim())) {
			System.out.println("실행");
			Member member = memberService.getMemberInfoById(memberId);
			System.out.println(member.getMemberId());
			System.out.println(member.getMemberPw());
			if (member.getMemberId().equals(memberId) && member.getMemberPw().equals(memberPw)) {
				System.out.println("============login============");
				// 아이디
				session.setAttribute("SID", memberId);
				// 권한
				session.setAttribute("SNAME", member.getMemberName());
				// 이름
				session.setAttribute("SLEVEL", member.getLevelName());
				
				System.out.println(member.getMemberName() + "111111111111111111111111111111111111111");
				System.out.println(member.getLevelName() + "00000000000000000000000000000000000000000000");
				memberService.updateLogin(memberId);

				return "main";
			}
			System.out.println("============login2============");

			return "redirect:/login";

		}

		return "redirect:/login";
	}

	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		System.out.println(session.getAttribute("SID") + "___________________________________________________________________");
		String memberId = (String) session.getAttribute("SID");
		memberService.updateLogout(memberId);
		session.invalidate();
		return "main";
	}

	@RequestMapping(value = "/member/addMember", method = RequestMethod.GET)
	public String addMember() {
		return "views/member/addMember";
	}

	@PostMapping("/member/addMember")
	public String addMember(Member member) {
		
		System.out.println(member + "member <---------------------------------------------------");

		String memberIdenNum = member.getMemberIdenNum() + "-" + member.getMemberIdenNum2();

		member.setMemberIdenNum(memberIdenNum);
		member.setLevelCode("level_2");
		member.setMemberStatus("정상");
		System.out.println(member.getMemberIdenNum());

		memberService.addMember(member);

		return "main";
	}

	@PostMapping("/ajax/idCheck")
	public @ResponseBody String idChdck(@RequestParam(value = "memberId", required = false) String memberId) {
		String result = "사용불가능";
		System.out.println(memberId);
		if (memberId != null && !"".equals(memberId) && !"".equals(memberId.trim())) {
			Member member = memberService.getMemberInfoById(memberId);

			if (member != null && member.getMemberId().equals(memberId)) {
				result = "사용불가능";
			} else {
				result = "사용가능";
			}
		}

		return result;
	}

		
	  @GetMapping("/member/getMember") 
	  public String getMember(Model model
							  ,@RequestParam(value="searchKey", required = false) String searchKey
							  ,@RequestParam(value="searchValue", required = false) String searchValue) {
		  
		  List<Member> memberList = null;
		  
		  if(searchKey != null && searchValue != null && !"".equals(searchKey) && !"".equals(searchValue)) { 
			  
			 System.out.println("11111111111111111");
			 memberList = memberService.getMemberList(searchKey, searchValue);
		  
			  System.out.println(memberList); 
			  }
		  else {
			  System.out.println("======member List====");
			  memberList = memberService.getMemberList(searchKey, searchValue);
			  
			  System.out.println(memberList); 
			  System.out.println("등록완료"); 
			  }
		  
		  	System.out.println(memberList + "<------------------memberList");
		  
			  model.addAttribute("memberList",memberList); 
		  
		  return "views/member/getMember";
	  }

	  
	  
}
