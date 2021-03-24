package easyfarm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import easyfarm.domain.Farm;
import easyfarm.domain.FarmMember;
import easyfarm.service.FarmService;




@Controller
public class FarmController {

	@Autowired
	private FarmService farmService;
	
	@GetMapping("/farmMain")
	public String farmMain(Model model, HttpSession session, @RequestParam(name = "memberId", required = false) String id) {
		Map<String, List<Farm>> mapListFarm = null;
		String memberId = null;
		memberId = (String)session.getAttribute("SID");
		
		if(id != null) memberId = id;
		
		if(memberId != null) {
			
			mapListFarm = farmService.myFarmList(memberId);
			
			if(mapListFarm != null) {
				List<Farm> myFarmList = mapListFarm.get("myFarmList");
				List<Farm> belongFarmList = mapListFarm.get("belongFarmList");
				
				if(myFarmList != null) {					
					model.addAttribute("myFarmList", myFarmList);
				}
				if(belongFarmList != null) {
					model.addAttribute("belongFarmList",belongFarmList);
				}
				
			}
		}
		
		return "views/farm/farmMain";
	}
	@GetMapping("/farm/detailFarm")
	public String farmDetailFarm(Model model, @RequestParam(name ="farmCode", required = false) String fCode) {
		
		Map<String,Object> detailFarm = farmService.detailFarm(fCode);
		if(detailFarm != null) {
			Farm resultFarm =(Farm)detailFarm.get("myFarm");
			List<FarmMember> resultFarmMemberList = (List<FarmMember>)detailFarm.get("myFarmMemberList");
			
			if(resultFarm != null) {
				model.addAttribute("myFarm", resultFarm);
			}
			
			if(resultFarmMemberList != null) {
				model.addAttribute("myFarmMemberList", resultFarmMemberList);
			}
			 
			
		
		}
		
		
		
		
		
		
		
		return "views/farm/detailFarm";
	}
	
}
