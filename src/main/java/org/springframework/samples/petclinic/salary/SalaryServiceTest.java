package org.springframework.samples.petclinic.salary;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

class SalaryServiceTest implements SalaryService {

	@Transactional
	@Override
	public List<SalaryDto> getSalaries(List<Integer> vetIDs) {
		return vetIDs.stream().map(vetId -> new SalaryDto(vetId, BigDecimal.ZERO)).toList();
	}

	@Transactional
	@Override
	public BigDecimal getSalary(Integer vetID) {
		return BigDecimal.ZERO;
	}

}
