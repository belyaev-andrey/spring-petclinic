package org.springframework.samples.petclinic.salary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
class SalaryConfiguration {

	@Value("${salary.integration.enabled}")
	private Boolean enabled;

	@Bean(name = "salaryService")
	@RefreshScope
	SalaryService createSalaryService() {
		if (enabled) {
			return new SalaryServiceProd();
		}
		return new SalaryServiceTest();
	}

}
