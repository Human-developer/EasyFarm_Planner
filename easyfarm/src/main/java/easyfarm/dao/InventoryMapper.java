package easyfarm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.inventory.StockStatus;

@Mapper
public interface InventoryMapper {
	
	public List<StockStatus> getStockStatusList(String farmCode);
	public int modifyStockStatus(String stockStatusCode, String searchRemainQty,String stockQtyCapUnitExtra, String availableStatus);
	
	

	 
}
