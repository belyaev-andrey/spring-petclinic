package org.springframework.samples.petclinic.owner;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visits")
class VisitApiController {

	private final VisitService visitService;

	public VisitApiController(VisitService visitService) {
		this.visitService = visitService;
	}

	@PostMapping(value = "/owner/{ownerId}/pet/{petId}", consumes = "application/json", produces = "application/json")
	Visit scheduleVisit(@PathVariable int ownerId, @PathVariable int petId, @Valid @RequestBody Visit visit) {
		return visitService.saveVisit(ownerId, petId, visit);
	}

}

@Service
class VisitService {
	private final OwnerRepository ownerRepository;
	private final CollarRepository collarRepository;
	private final AirTagRepository airTagRepository;

	VisitService(OwnerRepository ownerRepository, CollarRepository collarRepository, AirTagRepository airTagRepository) {
		this.ownerRepository = ownerRepository;
		this.collarRepository = collarRepository;
		this.airTagRepository = airTagRepository;
	}

	@Transactional
	Visit saveVisit(int ownerId, int petId, Visit visit) {
		Owner owner = ownerRepository.findOwnerById(ownerId);
		owner.getPet(petId).addVisit(visit);
		Collar collar = collarRepository.findByCollarId_Pet_Id(petId).stream().findFirst().orElseThrow();
		AirTag airTag = airTagRepository.findAll().stream().findFirst().orElseThrow();
		Owner saved = ownerRepository.save(owner);
		return
			saved.getPet(petId).getVisits()
			.stream().min((v1, v2) -> v2.getDate().compareTo(v1.getDate()))
			.orElseThrow();
	}
}
