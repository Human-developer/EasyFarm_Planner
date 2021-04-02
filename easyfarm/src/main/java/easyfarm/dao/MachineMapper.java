package easyfarm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.Machine;

@Mapper
public interface MachineMapper {

	public List<Machine> getCommonMachineCode();
	public Machine getCommonMachineInfo(String commonMachineCode);

	public int addCommonMachineCode(Map<String, Object> map);
	public int modifyCommonMachine(Machine machine);
}
