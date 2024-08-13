package org.springframework.samples.petclinic.salary;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface SalaryService {

	List<SalaryDto> getSalaries(List<Integer> vetIDs);

	BigDecimal getSalary(Integer vetID);
}
