package org.springframework.samples.petclinic.vet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.salary.SalaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
class VetService {

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

	private void addSalaries(List<Vet> vets) {
		Map<Integer, Vet> vetById = vets.stream().collect(Collectors.toMap(Vet::getId, vet -> vet));
		salaryService.getSalaries(vets.stream().map(Vet::getId).toList())
			.forEach(salary -> vetById.computeIfPresent(salary.vetID(), (id, vet) -> {
				vet.setSalary(salary.salary());
				return vet;
			}));
	}

	@Transactional(readOnly = true)
	public Vet findById(Integer integer) {
		Vet vet = vetRepository.findById(integer).orElseThrow();
		vet.setSalary(salaryService.getSalary(vet.getId()));
		return vet;
	}

}
