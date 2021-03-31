package easyfarm.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


	//공통농기계 추가등록
	public int addCommonMachineCode(Machine machine, String memberId) {
		Map<String, Object> test = new HashMap<>();
		test.put("machine", machine);
		test.put("memberId", memberId);
		int result = machineMapper.addCommonMachineCode(test);
		return result;
	
	}
}
