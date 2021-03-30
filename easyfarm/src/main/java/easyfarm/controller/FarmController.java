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


import easyfarm.domain.Farm;
import easyfarm.domain.FarmMember;
import easyfarm.service.FarmService;




@Controller
public class FarmController {

	@Autowired
	private FarmService farmService;
	
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
		
		if(farm != null && farm.getFarmCode() != null) {						
			farm.setCeoId((String)session.getAttribute("SID"));
			Farm resultFarm = farmService.detailFarm(farm);			
			
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
				
				return "redirect:/farm/detailFarm?farmCode="+farm.getFarmCode()+",farmMemberLevel="+farm.getFarmMemberLevel();
			}
			else {
				return "redirect:/farm/detailFarm?farmCode="+farm.getFarmCode()+",farmMemberLevel="+farm.getFarmMemberLevel();				
			}
		}
		else {
			return "redirect:/farm/detailFarm?farmCode="+farm.getFarmCode()+",farmMemberLevel="+farm.getFarmMemberLevel();
		}
		
		
	}
	/* 농가수정 */
	
	/* 농가등록 */
	@GetMapping("/farm/addFarm")
	public String addFarm() {

		return "views/farm/addFarm";
	}
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
			Model model,
			@RequestParam(value = "farmCode", required = false) String farmCode,
			@RequestParam(value = "farmMemberLevel", required = false) String farmMemberLevel) {
		
		if(farmCode != null && farmMemberLevel != null) {
			List<FarmMember> farmMemberList = farmService.getMemberFarm(farmCode);
			if(farmMemberList != null) {
				model.addAttribute("farmMemberList", farmMemberList);
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
	public String getJoinFarm(Model model) {
		return "views/farm/getJoinFarm";
	}
	/* 농가가입신청리스트 */
	
	
	/* 농가탈퇴신청리스트 */
	@GetMapping("/farm/getLeaverFarm")
	public String getLeaverFarm(Model model) {
		return "views/farm/getLeaverFarm";
	}
	/* 농가탈퇴신청리스트 */
	
	
	/* 내가입신청리스트 */
	@GetMapping("/farm/myGetJoinFarm")
	public String myGetJoinFarm(Model model) {
		return "views/farm/myGetJoinFarm";
	}
	/* 내가입신청리스트 */
}
