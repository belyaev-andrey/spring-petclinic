package org.springframework.samples.petclinic.salary;

import java.math.BigDecimal;
import java.util.List;

public interface SalaryService {

	List<SalaryDto> getSalaries(List<Integer> vetIDs);

	BigDecimal getSalary(Integer vetID);

}
