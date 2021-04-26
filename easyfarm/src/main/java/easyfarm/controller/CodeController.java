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
import org.springframework.web.bind.annotation.ResponseBody;

import easyfarm.domain.Crop;
import easyfarm.domain.CropDetailCategory;
import easyfarm.domain.CropWorkStage;
import easyfarm.domain.Machine;
import easyfarm.domain.Member;
import easyfarm.domain.TaxPayCate;
import easyfarm.domain.plan.StockCate;
import easyfarm.service.CropService;
import easyfarm.service.MachineService;
import easyfarm.service.PlanService;

@Controller
public class CodeController {
	@Autowired
	private CropService cropService;
	@Autowired
	private PlanService planService;
	@Autowired
	private MachineService machineService;
	
	@GetMapping("/code")
	public String codeMain(Model model) {
		
		return "views/code/codeMain";
	}
	//작물리스트 조회
	@GetMapping("/code/getCropCode")
	public String getCropList(Model model) {
		Map<String, Object> map=  new HashMap<String, Object>();
		List<Crop> cropList = cropService.getCropCode(map);
		model.addAttribute("cropList" , cropList );
		return "views/code/getCropCode";
	}
	//작업단계 조회
	@GetMapping("/code/getCropWorkStage")
	public String getCropWorkStage(Model model) {
		List<CropWorkStage> cropWorkStage = new ArrayList<CropWorkStage>();
		cropWorkStage = cropService.getCropWorkStage();
		System.out.println(cropWorkStage);
		model.addAttribute("cropWorkStage" , cropWorkStage );
		return "views/code/getCropWorkStage";
	}
	//상세작업항목조회
	@GetMapping("/code/getCropDetailCategory")
	public String getCropDetailCategory(Model model) {
		List<CropDetailCategory> cropDetailCategory = new ArrayList<CropDetailCategory>();
		cropDetailCategory = cropService.getCropDetailCategory();
		System.out.println(cropDetailCategory);
		model.addAttribute("cropDetailCategory" , cropDetailCategory );
		return "views/code/getCropDetailCategory";
	}
	//품목카테고리조회
	@GetMapping("/code/getStockCateList")
	public String getStockCateList(Model model) {
		List<StockCate> stockCateList = planService.getStockCateList();
		model.addAttribute("stockCateList", stockCateList);
		System.out.println(stockCateList);
		return "views/code/getStockCateList";
	}
	//공과금카테고리조회
	@GetMapping("/code/getTaxPayCateList")
	public String getTaxPayCate(Model model) {
		List<TaxPayCate> TaxPayCateList = cropService.getTaxPayCateList();
		model.addAttribute("TaxPayCateList", TaxPayCateList);
		System.out.println(TaxPayCateList);
		return "views/code/getTaxPayCateList";
	}
	//공통농기계조회
	@GetMapping("/code/getCommonMachineCode")
	public String getCommonMachineCode(Model model) {
		List<Machine> commonMachineList = new ArrayList<Machine>();
		commonMachineList = machineService.getCommonMachineCode();
		System.out.println(commonMachineList);
		model.addAttribute("commonMachineList",commonMachineList);
		return "views/code/getCommonMachineCode";
	}
	/*===========================================================*/	
	//작물등록
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

	//작업단계등록
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
	
	//상세작업항목등록
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

	//품목카테고리 등록
	@PostMapping("/code/addStockCate")
	public String addStockCate(StockCate stockCate, HttpSession session) {
		if (stockCate != null && !"".equals(stockCate.getStockCateCode())) {
			String memberId = (String) session.getAttribute("SID");
			cropService.addStockCate(stockCate, memberId);
		}
		return "redirect:/code/getStockCateList";
	}
	 
	@RequestMapping(value = "/code/addStockCate" , method = RequestMethod.GET)
	public String addStockCate() {
		return "views/code/addStockCate";
	}

	//공과금카테고리 등록
	@PostMapping("/code/addTaxPayCate")
	public String addTaxPayCate(TaxPayCate taxPayCate, HttpSession session) {
		if (taxPayCate != null && !"".equals(taxPayCate.getTaxPayCateCode())) {
			String memberId = (String) session.getAttribute("SID");
			cropService.addTaxPayCate(taxPayCate, memberId);
			System.out.println( " \n\n\n\n\n\n\n\n\n\n\n\test\n\n\n\n\n"+ taxPayCate);
		}
		return "redirect:/code/getTaxPayCateList";
	}

	@RequestMapping(value = "/code/addTaxPayCate" , method = RequestMethod.GET)
	public String addTaxPayCate() {
		return "views/code/addTaxPayCate";
	}

	//공통농기계등록
	@PostMapping("/code/addCommonMachineCode")
	public String addCommonMachineCode(Machine machine,HttpSession session) {
		if(machine != null && !"".equals(machine.getCommonMachineCode())){
			String memberId = (String)session.getAttribute("SID");
			if(memberId != null) System.out.println(memberId+" =test");
			machineService.addCommonMachineCode(machine,memberId);
		}
		return "redirect:/code/getCommonMachineCode"; 
	}
	@RequestMapping(value = "/code/addCommonMachineCode" , method = RequestMethod.GET)
	public String addCommonMachineCode() {
		return "views/code/addCommonMachineCode";
	}
	
	/*===========================================================*/
	//작물수정
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

	//작업단계 수정
	@PostMapping("/code/modifyCropWorkStage") 
	public String modifyCropWorkStage(CropWorkStage cropWorkStage) {
		if(cropWorkStage != null && !"".equals(cropWorkStage.getCropPhaseInfoCode())){
			cropService.modifyCropWorkStage(cropWorkStage);
		}
		return "redirect:/code/getCropWorkStage";
	}
	@GetMapping("/code/modifyCropWorkStage")
	public String modifyCropWorkStage(Model model,
			@RequestParam(name = "cropPhaseInfoCode", required = false) String cropPhaseInfoCode) {
		CropWorkStage cropWorkStage = cropService.getcropPhaseInfo(cropPhaseInfoCode);
		model.addAttribute("cropWorkStage", cropWorkStage);
		return "views/code/modifyCropWorkStage";		
	}	

	//상세작업항목　수정
	@PostMapping("/code/modifyCropDetailCategory") 
	public String modifyCropDetailCategory(CropDetailCategory cropDetailCategory) {
		if(cropDetailCategory != null && !"".equals(cropDetailCategory.getCommonWorkphaseCateCode())){
			cropService.modifyCropDetailCategory(cropDetailCategory);
		}
		return "redirect:/code/getCropDetailCategory";
	}
	
	@GetMapping("/code/modifyCropDetailCategory")
	public String modifyCropDetailCategory(Model model,
			@RequestParam(name = "commonWorkphaseCateCode", required = false) String commonWorkphaseCateCode) {
		CropDetailCategory cropDetailCategory = cropService.getCropDetailCategory(commonWorkphaseCateCode);
		model.addAttribute("cropDetailCategory", cropDetailCategory);
		return "views/code/modifyCropDetailCategory";		
	}
	
	//품목카테고리 수정
	@PostMapping("/code/modifyStockCate")
	public String modifyStockCate(StockCate stockCate) {
		if(stockCate != null && !"".equals(stockCate.getStockCateName()))
			cropService.modifyStockCate(stockCate);
		return "redirect:/code/getStockCateList";
	}
	@GetMapping("/code/modifyStockCate")
	public String modifyStockCate(Model model,
			@RequestParam(name = "stockCateCode" ,required = false)String StockCateCode) {
		StockCate stockCate = cropService.getInfoStockCate(StockCateCode);
		model.addAttribute("stockCate",stockCate);
		return "views/code/modifyStockCate"; //화면단만들기	
	}
	//공과금 카테고리 수정
	@PostMapping("/code/modifyTaxPayCate")
	public String modifyTaxPayCate(TaxPayCate TaxPayCate) {
		if(TaxPayCate != null && !"".equals(TaxPayCate.getTaxPayCateName()))
			cropService.modifyTaxPayCate(TaxPayCate);
		return "redirect:/code/getTaxPayCateList";
	}
	@GetMapping("/code/modifyTaxPayCate")
	public String modifyTaxPayCate(Model model,
			@RequestParam(name = "taxPayCateCode" ,required = false)String taxPayCateCode) {
		TaxPayCate taxPayCate = cropService.getInfoTaxPayCate(taxPayCateCode);
		model.addAttribute("taxPayCate",taxPayCate);
		return "views/code/modifyTaxPayCate"; //화면단만들기	
	}
	
	//공통농기계　수정		
	@PostMapping("/code/modifyCommonMachine") 
	public String modifyCommonMachine(Machine machine) {
		if(machine != null && !"".equals(machine.getCommonMachineName())){
			machineService.modifyCommonMachine(machine);
		}
		return "redirect:/code/getCommonMachineCode";
	}
	
	@GetMapping("/code/modifyCommonMachine")
	public String modifyCommonMachine(Model model,
			@RequestParam(name = "commonMachineCode", required = false) String commonMachineCode) {
		Machine machine = machineService.getCommonMachineInfo(commonMachineCode);
		model.addAttribute("machine", machine);
		return "views/code/modifyCommonMachine";		
	}	
	
	/*===========================================================*/
	//작물 중복체크
	@PostMapping("/ajax/cropCheck")
	public @ResponseBody String cropCheck(@RequestParam(value = "cropname", required = false) String cropName) {
		String result = "사용불가능";
		System.out.println("입력값: "+cropName);
		if (cropName != null && !"".equals(cropName) && !"".equals(cropName.trim())) {
			Crop crop =cropService.getCropNameinfo(cropName);

			if (crop != null && crop.getCropName().equals(cropName)) {
				result = "사용불가능";
			} else {
				result = "사용가능";
			}
		}

		return result;
	}
	
	//작업단계 중복체크
	@PostMapping("/ajax/cropWorkphaseCheck")
	public @ResponseBody String cropWorkphaseCheck(@RequestParam(value = "workphase", required = false) String workphase,
													@RequestParam(value = "cropcode", required = false) String cropCode) {
		String result = "";
		System.out.println("작물코드 "+cropCode);
		System.out.println("작업단계 "+workphase);
		if (workphase != null && !"".equals(workphase) && !"".equals(workphase.trim())) {
			if (cropCode != null && !"".equals(cropCode) && !"".equals(cropCode.trim())) {
				
				CropWorkStage crop =cropService.getcropPhaseInfo2(workphase,cropCode);
				System.out.println("조회");
				if (crop != null && crop.getCropCode().equals(cropCode) ) {
					System.out.println("작물 일치 -> 작업단계");
					if(crop != null && crop.getWorkphase().equals(workphase)) {
						result = "";
						System.out.println("작업단계 이미 등록되어있음 - 사용불가능");
					} else {
						result = "사용가능";
						System.out.println("작업단계 없음 - 사용가능");
					}
				}else {
					System.out.println("목록에 등록된 작물이없다. 1단계부터등록");
					result = "사용가능";
				}
			}
		}

		return result;
	}
	/*===========================================================*/
	 //품목카테고리 삭제
	 @GetMapping("/code/removeStockCate")
	 public String removeStockCate (@RequestParam(value = "stockCateCode",required = false) String stockCateCode) {
    	cropService.removeStockCate(stockCateCode);
		return "redirect:/code/getStockCateList";
	 }
	 //공과금카테고리 삭제
	 @GetMapping("/code/removeTaxPayCate")
	 public String removeTaxPayCate (@RequestParam(value = "taxPayCateCode",required = false) String taxPayCateCode) {
		 cropService.removeTaxPayCate(taxPayCateCode);
		 return "redirect:/code/getTaxPayCateList";
	 }
	
	
}
