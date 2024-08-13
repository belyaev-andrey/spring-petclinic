package org.springframework.samples.petclinic.salary;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

class SalaryServiceProd implements SalaryService {

	@Transactional
	@Override
	public List<SalaryDto> getSalaries(List<Integer> vetIDs) {
		return vetIDs.stream().map(vetId -> new SalaryDto(vetId, BigDecimal.valueOf(vetId * 10))).toList();
	}

	@Transactional
	@Override
	public BigDecimal getSalary(Integer vetID) {
		return BigDecimal.valueOf(vetID * 10);
	}

}
