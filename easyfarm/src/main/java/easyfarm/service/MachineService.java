package easyfarm.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import easyfarm.dao.MachineMapper;
import easyfarm.domain.Machine;

@Service
@Transactional
public class MachineService {
	@Autowired
	private  MachineMapper machineMapper;
	
	
	public List<Machine> getCommonMachineCode(){
		List<Machine> commonMachineList = machineMapper.getCommonMachineCode();
		return commonMachineList;
		
	}
	
}
