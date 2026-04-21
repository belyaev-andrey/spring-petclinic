/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vet;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link Vet} instances.
 */
@Service
class VetService {

	private final VetRepository vetRepository;
	private final VetConfigurationProperties vetConfigurationProperties;

	VetService(VetRepository vetRepository,
			   VetConfigurationProperties vetConfigurationProperties) {
		this.vetRepository = vetRepository;
		this.vetConfigurationProperties = vetConfigurationProperties;
	}

	public Vet addNewVet(Vet vet) {
		if (vetConfigurationProperties.getMinSalary() > vet.getSalary()) {
			throw new IllegalArgumentException("Salary should be greater than minimum salary");
		}
		return this.vetRepository.save(vet);
	}

	public Collection<Vet> findAll() {
		return vetRepository.findAll();
	}

	public Page<Vet> findAll(Pageable pageable) {
		return vetRepository.findAll(pageable);
	}

}
