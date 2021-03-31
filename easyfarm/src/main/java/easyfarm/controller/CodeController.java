package easyfarm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import easyfarm.dao.CropMapper;
import easyfarm.domain.Crop;
import easyfarm.domain.CropDetailCategory;
import easyfarm.domain.CropWorkStage;
import easyfarm.domain.Machine;
import easyfarm.domain.Member;
import easyfarm.service.CropService;
import easyfarm.service.MachineService;

@Controller
public class CodeController {
	@Autowired
	private CropService cropService;
	@Autowired
	private MachineService machineService;
	
	@GetMapping("/code")
	public String codeMain(Model model) {
		
		return "views/code/codeMain";
	}
	
	@GetMapping("/code/getCropCode")
	public String getCropList(Model model) {
		Map<String, Object> map=  new HashMap<String, Object>();
		List<Crop> cropList = cropService.getCropCode(map);
		model.addAttribute("cropList" , cropList );
		return "views/code/getCropCode";
	}
	
	@GetMapping("/code/getCropWorkStage")
	public String getCropWorkStage(Model model) {
		List<CropWorkStage> cropWorkStage = new ArrayList<CropWorkStage>();
		cropWorkStage = cropService.getCropWorkStage();
		System.out.println(cropWorkStage);
		model.addAttribute("cropWorkStage" , cropWorkStage );
		return "views/code/getCropWorkStage";
	}
	
	@GetMapping("/code/getCropDetailCategory")
	public String getCropDetailCategory(Model model) {
		List<CropDetailCategory> cropDetailCategory = new ArrayList<CropDetailCategory>();
		cropDetailCategory = cropService.getCropDetailCategory();
		System.out.println(cropDetailCategory);
		model.addAttribute("cropDetailCategory" , cropDetailCategory );
		return "views/code/getCropDetailCategory";
	}
	
	@GetMapping("/code/getCommonMachineCode")
	public String getCommonMachineCode(Model model) {
		List<Machine> commonMachineList = new ArrayList<Machine>();
		commonMachineList = machineService.getCommonMachineCode();
		System.out.println(commonMachineList);
		model.addAttribute("commonMachineList",commonMachineList);
		return "views/code/getCommonMachineCode";
	}
/*===========================================================*/	
	@PostMapping("/code/addCrop")
	public String addCrop(Crop crop,HttpSession session) {
		if(crop != null && !"".equals(crop.getCropCode())){
			String memberId = (String)session.getAttribute("SID");
			if(memberId != null) System.out.println(memberId+" =test");
			cropService.addCrop(crop,memberId);
		}
		return "redirect:/code/getCropCode"; 
	}
	@RequestMapping(value = "/code/addCrop" , method = RequestMethod.GET)
	public String addCrop() {
		return "views/code/addCrop";
	}
/*===========================================================*/
	
	@PostMapping("/code/addCropWorkStage")
	public String addCropWorkStage(CropWorkStage crop,HttpSession session) {
		if(crop != null && !"".equals(crop.getCropPhaseInfoCode())){
			String memberId = (String)session.getAttribute("SID");
			if(memberId != null) System.out.println(memberId+" =test");
			cropService.addCropWorkStage(crop,memberId);
		}
		return "redirect:/code/getCropWorkStage"; 
	}
	
	@GetMapping("/code/addCropWorkStage")
	public String addCropWorkStage(Model model) {
		Map<String, Object> map=  new HashMap<String, Object>();
		List<Crop> crop = cropService.getCropCode(map);
		model.addAttribute("crop", crop);
		return "views/code/addCropWorkStage";		
	}	
	
/*===========================================================*/
	@PostMapping("/code/addCropDetailCategory")
	public String addCropDetailCategory(CropDetailCategory crop,HttpSession session) {
		if(crop != null && !"".equals(crop.getCommonWorkphaseCateCode())){
			String memberId = (String)session.getAttribute("SID");
			if(memberId != null) System.out.println(memberId+" =test");
			cropService.addCropDetailCategory(crop,memberId);
		}
		return "redirect:/code/getCropDetailCategory"; 
	}
	@RequestMapping(value = "/code/addCropDetailCategory" , method = RequestMethod.GET)
	public String addCropDetailCategory() {
		return "views/code/addCropDetailCategory";
	}
/*===========================================================*/
	
	@PostMapping("/code/modifyCrop")
	public String modifyCrop(Crop crop,HttpSession session) {
		if(crop != null && !"".equals(crop.getCropCode())){
			String memberId = (String)session.getAttribute("SID");
			if(memberId != null) System.out.println(memberId+" =test");
			cropService.modifyCrop(crop,memberId);
		}
		return "redirect:/code/getCropCode";
	}
	 
	@GetMapping("/code/modifyCrop")
	public String getmodifycrop(Model model,
								@RequestParam(name = "cropCode", required = false) String cropCode) {
		Crop crop = cropService.getcropCateInfo(cropCode);
		model.addAttribute("crop", crop);
		return "views/code/modifyCrop";		
	}	
/*===========================================================*/
	/*@PostMapping("/code/modifyCropWorkStage") 
	public String qweqwe(Crop crop,HttpSession session) {
		if(crop != null && !"".equals(crop.getCropCode())){
			String memberId = (String)session.getAttribute("SID");
			if(memberId != null) System.out.println(memberId+" =test");
			cropService.modifyCrop(crop,memberId);
		}
		return "redirect:/code/getCropWorkStage";
	}*/
	@GetMapping("/code/modifyCropWorkStage")
	public String qweqwe(Model model,
			@RequestParam(name = "cropPhaseInfoCode", required = false) String cropPhaseInfoCode) {
		CropWorkStage cropWorkStage = cropService.getcropPhaseInfo(cropPhaseInfoCode);
		model.addAttribute("cropWorkStage", cropWorkStage);
		return "views/code/modifyCropWorkStage";		
	}	
/*===========================================================*/
}
