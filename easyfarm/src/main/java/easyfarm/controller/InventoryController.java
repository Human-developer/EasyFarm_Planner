package easyfarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import easyfarm.domain.inventory.StockStatus;
import easyfarm.service.InventoryService;

@Controller
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@GetMapping("/inventory")
	public String inventoryMain(Model model) {
		
		return "views/inventory/inventoryMain";
	}
	
	@GetMapping("/inventoryList")
	public String inventoryList(Model model,
			@RequestParam String farmCode) {
		List stockStatusList = inventoryService.getStockStatusList(farmCode);
		model.addAttribute("farmCode", farmCode);
		model.addAttribute("stockStatusList",stockStatusList);
		return "views/inventory/inventoryList";
	}
	
	@ResponseBody
	@PostMapping("/modifyStockError")
	public int modifyStockError(String stockStatusCode,
			String farmCode,
			String errorRemainQty,
			String searchRemainQty,
			String stockQtyCapUnit) {
		System.out.println(stockStatusCode);
		System.out.println(farmCode);
		System.out.println(errorRemainQty);
		System.out.println(searchRemainQty);
		System.out.println(stockQtyCapUnit);
		int result = inventoryService.modifyStockError(stockStatusCode, farmCode, errorRemainQty, searchRemainQty, stockQtyCapUnit);
		return result;
	}
}
