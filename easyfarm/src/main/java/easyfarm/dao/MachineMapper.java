package easyfarm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import easyfarm.domain.Machine;

@Mapper
public interface MachineMapper {

	public List<Machine> getCommonMachineCode();
}
