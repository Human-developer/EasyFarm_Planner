package easyfarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InventoryController {

	@GetMapping("/inventory")
	public String inventoryMain(Model model) {
		
		return "views/inventory/inventoryMain";
	}
}
