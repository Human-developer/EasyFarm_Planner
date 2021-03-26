package easyfarm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonAlias;

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
		
		Farm farm = farmService.farmByName(farmName); 
		if(farm == null) {
			result ="생성가능";
		}
		else {
			result ="해당 농가가 이미 존재합니다";
		}
		
		if(farmName.length() < 2) {
			result = "2글자 이상 입력해주세요";
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
		
		return "views/farm/farmMain";
	}
	/* 농가메인 */
	
	
	/* 농가상세보기 */
	@GetMapping("/farm/detailFarm")
	public String farmDetailFarm(Model model, @RequestParam(name ="farmCode", required = false) String fCode) {
	
		return "views/farm/detailFarm";
	}
	/* 농가상세보기 */
	
	/* 나의농가 */
	@GetMapping("/farm/myFarm")
	public String myFarm(Model model, HttpSession session ) {
		
		return "views/farm/myFarm";
	}
	/* 나의농가 */
	
	/* 내소속농가보기 */
	@GetMapping("/farm/belongFarm")
	public String belongFarm(Model model, HttpSession session) {
		
		
		return "views/farm/belongFarm";
	}
	/* 내소속농가보기 */
	
	/* 농가검색 */
	@GetMapping("farm/searchFarm")
	public String searchFarm(Model model) {	
		return "views/farm/searchFarm";
	}
	/* 농가검색 */
	
	/* 농가수정 */
	@GetMapping("/farm/modifyFarm")
	public String modifyFarm(Model model, String fCode) {
		return "views/farm/modifyFarm";
	}
	@PostMapping("/farm/modifyFarm")
	public String modifyFarm(Model model) {
		return "redirect:/views/farmMain";
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
			
		}

		
		return "redirect:/farm/myFarm";
	}
	/* 농가등록 */
	
	/* 농가회원조회 */
	@GetMapping("/farm/getMemberFarm")
	public String getMemberFarm(Model model) {
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
