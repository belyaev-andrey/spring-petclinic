package org.springframework.samples.petclinic.vet;

import jakarta.annotation.Nullable;
import org.springframework.aop.framework.Advised;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.salary.SalaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VetService {


	private final VetRepository vetRepository;
	private final SalaryService salaryService;

	public VetService(VetRepository vetRepository, SalaryService salaryService) {
		this.vetRepository = vetRepository;
		this.salaryService = salaryService;
	}

	@Transactional(readOnly = true)
	public List<Vet> findAll() {
		List<Vet> vets = vetRepository.findAll();
		addSalaries(vets);
		return vets;
	}

	@Transactional(readOnly = true)
	public Page<Vet> findAll(Pageable pageable) {
		Page<Vet> vets = vetRepository.findAll(pageable);
		addSalaries(vets.getContent());
		return vets;
	}

	private void addSalaries(List<Vet> vets1) {
		Map<Integer, Vet> vetById = vets1.stream().collect(Collectors.toMap(Vet::getId, vet -> vet));
		salaryService
			.getSalaries(vets1.stream().map(Vet::getId).toList())
			.forEach(salary -> vetById.computeIfPresent(salary.vetID(), (id, vet) -> {
					vet.setSalary(salary.salary());
					return vet;
				}
			));
	}
}
