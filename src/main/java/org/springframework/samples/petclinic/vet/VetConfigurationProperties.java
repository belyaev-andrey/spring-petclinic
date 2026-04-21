package org.springframework.samples.petclinic.vet;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "clinic.vet.config")
public class VetConfigurationProperties {

	private Integer minSalary;

	public Integer getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(Integer minSalary) {
		this.minSalary = minSalary;
	}
}
