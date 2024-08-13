package org.springframework.samples.petclinic.salary;

import java.math.BigDecimal;

public record SalaryDto(Integer vetID, BigDecimal salary) {
}
