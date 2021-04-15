package easyfarm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import easyfarm.dao.FarmMapper;
import easyfarm.domain.Farm;
import easyfarm.domain.FarmCancelRequest;
import easyfarm.domain.FarmMember;
import easyfarm.domain.FarmMemberJoin;
import easyfarm.service.FarmService;



//http://api.nongsaro.go.kr/service/
//인증키 20210414GJHMG6WRKK5QXI9WYMOMQ
@Controller
public class FarmController {
	
	@Autowired
	private FarmService farmService;
	
	
	@PostMapping("/json/farmMemberLeaver")
	public @ResponseBody String farmMemberLeaver(HttpSession session,FarmMember farmMember) {
		String result = "실패";
		String memberId = (String)session.getAttribute("SID");
		if(farmMember != null && memberId != null) {
			result = farmService.deportation(farmMember, memberId);
		}

		return result;
	}
	
	
	
	@PostMapping("/json/modifyCeoFarm/")
	public @ResponseBody String modifyCeoFarm(FarmMember farmMember) {
		String result ="실패";
		if(farmMember != null) {
			try {
				int modifyCeoResult = farmService.modifyCeoFarm(farmMember);
				if(modifyCeoResult > 0) {
					result ="성공";
				}
			}
			catch (Exception e) {
				if(e.getMessage().equals("불일치")) {
					result = "다시시도해주세요";
				}
				
			}

		}
		
		return result;
	}
	@PostMapping("/json/farmMemberList")
	public @ResponseBody List<FarmMember> getFarmMemberList(@RequestParam(value = "farmCode",required = false) String farmCode){
		List<FarmMember> result = null;
		
		if(farmCode != null) {
			List<FarmMember> memberList =	farmService.farmMemeberList(farmCode);
			
			if(memberList != null) result = memberList;
		}
		
		return result;
	}
	
	//농가탈퇴 취소
	@PostMapping("/json/cancelLeaverFarm")
	public @ResponseBody String cancelLeaverFarm(
			@RequestParam(value = "cancelRequestCode",required = false) String cancelRequestCode) {
		String result ="삭제실패";
		
		if(cancelRequestCode != null) {
			result = farmService.cancelLeaverFarm(cancelRequestCode);
		}
		
		return result;
	}
	
	//탈퇴 승인거부
	@PostMapping("/farm/isLeaverFarm")
	public @ResponseBody String isLeaverFarm(
			HttpSession session,
			FarmCancelRequest cancelRequest) {
		String result = "실패";
		String memberId = (String)session.getAttribute("SID");
		
		if(memberId != null && cancelRequest != null)
		{			
			cancelRequest.setCancelApprovalMemberId(memberId);
			int cancelResult = farmService.isLeaverFarm(cancelRequest);
			
			if(cancelResult > 0) {
				result ="성공";
			}
		}
		else {
			result = "실패";
		}
		return result;
	}
	
	//탈퇴신청
	@PostMapping("/farm/addLeaverFarm")
	public @ResponseBody String addCancelMember(
			HttpSession session,
			@RequestParam(value = "farmName",required = false) String farmName,
			@RequestParam(value = "cancelRequestReason",required = false) String cancelRequestReason) {
		String result = "신청실패";
		String memberId = (String)session.getAttribute("SID");
		
		if(memberId != null && farmName != null) {
			result = farmService.addCancelMember(memberId,farmName,cancelRequestReason);
		}
		return result;
	}
	
	//가입신청취소
	@PostMapping("/json/removeFarmJoin")
	public @ResponseBody String removeFarmJoin(@RequestParam(value = "farmJoinCode", required = false) String farmJoinCode) {
		String result = "삭제실패";
		
		if(farmJoinCode != null) {
			int removeResult = 0;
			removeResult += farmService.removeJoinFarm(farmJoinCode);
			if(removeResult > 0) {
				result ="삭제성공";
			}
		}
		return result;
	}
	
	//가입승인 거부
	@PostMapping("/json/farmJoinMember")
	public @ResponseBody String farmJoinMember(HttpSession session,
			@RequestParam(value = "farmMemberJoinCode",required = false) String farmMemberJoinCode,
			@RequestParam(value = "approval",required = false) String approval) {
		String result = "처리 실패";
		String memberId = (String)session.getAttribute("SID");
		
		if(farmMemberJoinCode != null && approval != null && memberId != null) {
			int checkResult =0;
			checkResult = farmService.farmJoinMember(farmMemberJoinCode,approval,memberId);
			
			if(checkResult > 0) {				
				result ="처리 완료";
			}
		}
		return result;
	}
	
	//가입신청
	@PostMapping("/json/addfarmMemberJoin")
	public @ResponseBody String addFarmMemberJoin(
			HttpSession session,
			@RequestParam(value = "farmName",required = false) String farmName,
			@RequestParam(value = "farmJoinPurpose",required = false) String farmJoinPurpose) {
		String result = "신청실패";
		
		if(farmName != null && farmJoinPurpose != null) {
			String memberId = (String)session.getAttribute("SID");
			
			if(memberId != null && farmName != null && farmJoinPurpose != null) {
				result = farmService.addFarmMemberJoin(farmName, farmJoinPurpose, memberId);
			}
		}
		return result;
	}
	
	//농가명 중복확인
	@PostMapping("/json/farmNameCheck")
	public @ResponseBody String farmNameCheck(@RequestParam(value = "farmName", required = false) String farmName) {
		String result = "생성불가";
		Farm farm = null;
		
		if(farmName != null)  {
			farm = farmService.farmByName(farmName);
		}
		
		if(farm == null) {
			result ="생성가능";
		}
		
		return result;
	}
	
	
	
	/* 농가경로 */
	@GetMapping("/farm")
	public String farmMain(Model model) {
		return "views/farm/farm";
	}
	/* 농가경로 */
	
	
	/* 농가메인 */
	@GetMapping("/farm/farmMain")
	public String farmMain(Model model, HttpSession session) {
		String memberId = (String)session.getAttribute("SID");
		List<Farm> myFarmList =	farmService.myFarm(memberId);
		
		if(myFarmList != null) {
			model.addAttribute("myFarmList",myFarmList);
		}
		
		List<Farm> belongFarmList = farmService.belongFarm(memberId);
		
		if(belongFarmList != null) {
			model.addAttribute("belongFarmList",belongFarmList);
		}
		return "views/farm/farmMain";
	}
	/* 농가메인 */
	
	/* 농가상세보기 */
	@GetMapping("/farm/detailFarm")
	public String farmDetailFarm(Model model, Farm farm, HttpSession session) {
		String memberId = (String)session.getAttribute("SID");
		
		if(farm != null && farm.getFarmCode() != null && memberId != null) {						
			farm.setCeoId(memberId);
			Farm resultFarm = farmService.detailFarm(farm);			
			
			if(resultFarm.getFarmMemberLevel() != null){
				resultFarm.setFarmMemberLevel(farmService.getFarmMemberLevel(resultFarm.getFarmCode(), memberId));
			}
			
			
			model.addAttribute("farm", resultFarm);
			return "views/farm/detailFarm";
		}
		else {
			return "redirect:/farm/myFarm";
		}
	}
	/* 농가상세보기 */
	
	/* 나의농가 */
	@GetMapping("/farm/myFarm")
	public String myFarm(Model model, HttpSession session ) {
		String memberId = (String)session.getAttribute("SID");
		
		if(memberId != null) {
			List<Farm> myFarmList =	farmService.myFarm(memberId);
			
			if(myFarmList != null) {
				model.addAttribute("myFarmList",myFarmList);
			}
		}
		return "views/farm/myFarm";
	}
	/* 나의농가 */
	
	/* 내소속농가보기 */
	@GetMapping("/farm/belongFarm")
	public String belongFarm(Model model, HttpSession session) {
		String memberId = (String)session.getAttribute("SID");
		
		if(memberId != null) {
			List<Farm> myFarmList = farmService.belongFarm(memberId);
			
			if(myFarmList != null) {
				model.addAttribute("myFarmList",myFarmList);
			}
		}
		
		return "views/farm/belongFarm";
	}
	/* 내소속농가보기 */
	
	/* 농가검색 */
	@GetMapping("farm/searchFarm")
	public String searchFarm(Model model, HttpSession session) {	
		String memberId = (String)session.getAttribute("SID");
		List<Farm> farmList = null;
		farmList = farmService.searchFarm(memberId);
		
		if(farmList != null) {
			model.addAttribute("farmList", farmList);
		}
		return "views/farm/searchFarm";
	}
	/* 농가검색 */
	
	/* 농가수정 */
	@GetMapping("/farm/modifyFarm")
	public String modifyFarm(Model model, HttpSession session, @RequestParam(value = "farmCode", required = false) String fCode) {
		String memberId = (String)session.getAttribute("SID");
		
		if(fCode != null && memberId != null) {
			Farm myFarm = farmService.updateByFarm(fCode, memberId);
			
			if(myFarm != null && !"3".equals(myFarm.getFarmMemberLevel())) {
				model.addAttribute("farm", myFarm);
			}
			else {
				//권한이없다
				return "redirect:/farm/farmMain";
			}
			return "views/farm/modifyFarm";
		}
		else {
			return "redirect:/farm/farmMain";
		}
	}

	//처리
	@PostMapping("/farm/modifyFarm")
	public String modifyFarm(Farm farm) {

		if(farm!= null) {
			int result =0;
			result = farmService.updateFarm(farm);
			
			if(result > 0) {
				
				return "redirect:/farm/detailFarm?farmCode="+farm.getFarmCode()+"&farmMemberLevel="+farm.getFarmMemberLevel();
			}
			else {
				return "redirect:/farm/detailFarm?farmCode="+farm.getFarmCode()+"&farmMemberLevel="+farm.getFarmMemberLevel();				
			}
		}
		else {
			return "redirect:/farm/detailFarm?farmCode="+farm.getFarmCode()+"&farmMemberLevel="+farm.getFarmMemberLevel();
		}
		
		
	}
	/* 농가수정 */
	
	/* 농가등록 */
	@GetMapping("/farm/addFarm")
	public String addFarm() {

		return "views/farm/addFarm";
	}
	//처리
	@PostMapping("/farm/addFarm")
	public String addFarm(Model model,Farm farm) {
		
		if(farm!=null)	{
			int result = 0;
			result = farmService.addFarm(farm);
			
			if(result > 0 ) {
				System.out.println("농가생성 농가회원실패 조건이 뭘까?");
			}
			return "redirect:/farm/myFarm";
		}
		else {
			return "redirect:/farm/addFarm";
		}

		
	}
	/* 농가등록 */
	
	/* 농가회원조회 */
	@GetMapping("/farm/getMemberFarm")
	public String getMemberFarm(
			Model model, HttpSession session,
			@RequestParam(value = "farmCode", required = false) String farmCode,
			@RequestParam(value = "farmMemberLevel", required = false) String farmMemberLevel) {
		
		if(farmCode != null) {
			List<FarmMember> farmMemberList = farmService.getMemberFarm(farmCode);
			if(farmMemberList != null) {
				model.addAttribute("farmMemberList", farmMemberList);
				if(farmMemberLevel== null) {
					String memberId = (String)session.getAttribute("SID");
					farmMemberLevel = farmService.getFarmMemberLevel(farmCode,memberId);
				}
				model.addAttribute("myMemberLevel", farmMemberLevel);
			}
		}
		else {
			return "redirect:/farm/belongFarm";
		}
		return "views/farm/getMemberFarm";
	}
	/* 농가회원조회 */
	
	/* 농가가입신청리스트 */
	@GetMapping("/farm/getJoinFarm")
	public String getJoinFarm(Model model,
			@RequestParam(value = "farmCode", required = false) String farmCode) {
		if(farmCode != null) {
			List<FarmMemberJoin> farmMemberJoinList = farmService.getJoinFarm(farmCode);
			System.out.println(farmMemberJoinList + "ttttttttttttttttttttttttttt");
			
			if(farmMemberJoinList != null) {
				model.addAttribute("farmMemberJoinList", farmMemberJoinList);
				model.addAttribute("farmCode", farmCode);
			}
		}
		else {
			return "redirect:/farm/farmMain";
		}
		return "views/farm/getJoinFarm";
	}
	/* 농가가입신청리스트 */
	
	
	/* 농가탈퇴신청리스트 */
	@GetMapping("/farm/getLeaverFarm")
	public String getLeaverFarm(
			Model model,
			HttpSession session,
			@RequestParam(value = "farmCode", required = false) String farmCode) {
		String memberId = (String)session.getAttribute("SID");
		if(farmCode != null && memberId != null) {
			List<FarmCancelRequest> farmLeaverFarmList = farmService.getLeaverFarm(farmCode,memberId);
			
			if(farmLeaverFarmList != null) {
				model.addAttribute("farmLeaverFarmList", farmLeaverFarmList);
			}
		}
		return "views/farm/getLeaverFarm";
	}
	/* 농가탈퇴신청리스트 */
	
	
	/* 내가입신청리스트 */
	@GetMapping("/farm/myGetJoinFarm")
	public String myGetJoinFarm(Model model, HttpSession session) {
		String memberId = (String)session.getAttribute("SID");
		if(memberId != null) {
			List<FarmMemberJoin> farmMemberJoinList = farmService.myGetJoinFarm(memberId);
			System.out.println(farmMemberJoinList + "tttttttttttttttttttttttt");
			if(farmMemberJoinList != null) {
				model.addAttribute("myGetJoinFarmList", farmMemberJoinList);
			}
		}
		return "views/farm/myGetJoinFarm";
	}
	/* 내가입신청리스트 */
	
	@GetMapping("/farm/myGetLeaverFarm")
	public String myGetLeaverFarm(Model model, HttpSession session) {
		String memberId = (String)session.getAttribute("SID");
		if(memberId != null) {
			List<FarmCancelRequest> myLeaverFarmList =farmService.myGetLeaverFarm(memberId);
			
			if(myLeaverFarmList != null) {
				model.addAttribute("myLeaverFarmList", myLeaverFarmList);
			}
		}
		
		
		return "views/farm/myGetLeaverFarm";
	}
}
