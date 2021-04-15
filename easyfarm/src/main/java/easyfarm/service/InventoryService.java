package easyfarm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import easyfarm.dao.InventoryMapper;
import easyfarm.dao.ProjectMapper;
import easyfarm.domain.CropPhaseInfo;
import easyfarm.domain.Project;
import easyfarm.domain.ProjectWorkphase;
import easyfarm.domain.inventory.StockStatus;

@Transactional
@Service
public class InventoryService {
	
	// projectMapper 연결
	private final InventoryMapper inventoryMapper;
	@Autowired
	public InventoryService(InventoryMapper inventoryMapper) {
		this.inventoryMapper = inventoryMapper;
	}
	
	public List<StockStatus> getStockStatusList(String farmCode) {
		
		return inventoryMapper.getStockStatusList(farmCode);
	}
	
	public int modifyStockError(String stockStatusCode,
			String farmCode,
			String errorRemainQty,
			String searchRemainQty,
			String stockQtyCapUnit) {
		String stockQtyCapUnitExtra = Integer.toString(Integer.parseInt(stockQtyCapUnit) * Integer.parseInt(searchRemainQty));
		String availableStatus = "Y";
		if(searchRemainQty.equals("") || searchRemainQty==null) {
			availableStatus = "N";
		}
		int result = inventoryMapper.modifyStockStatus(stockStatusCode, searchRemainQty, stockQtyCapUnitExtra, availableStatus);
		if(result !=0) System.out.println("재고현황 업데이트 성공");
		return result;
	}
}
