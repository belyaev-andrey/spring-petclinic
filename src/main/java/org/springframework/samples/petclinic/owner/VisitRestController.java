package org.springframework.samples.petclinic.owner;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.samples.petclinic.notification.Notificator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;

@RestController
@RequestMapping("/api/visits")
class VisitRestController {

	private final VisitService visitService;
	private final Notificator notificator;

	public VisitRestController(VisitService visitService, Notificator notificator) {
		this.visitService = visitService;
		this.notificator = notificator;
	}

	@PostMapping(value = "/{ownerId}/pets/{petId}", consumes = "application/json", produces = "application/json")
	Visit scheduleVisit(@PathVariable int ownerId, @PathVariable int petId, @Valid @RequestBody Visit visit) {
		Visit addedVisit = visitService.addVisit(ownerId, petId, visit);
		notificator.sendNotification(ownerId, addedVisit.getId());
		return addedVisit;
	}
}

@Service
class VisitService {
	private final OwnerRepository ownerRepository;

	public VisitService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Transactional
	public Visit addVisit(int ownerId, int petId, Visit visit) {
		Owner owner = ownerRepository.findOwnerById(ownerId);
		owner.getPet(petId).addVisit(visit);
		Owner saved = ownerRepository.save(owner);
		return saved.getPet(petId).getVisits().stream().max(Comparator.comparing(Visit::getDate)).orElseThrow();
	}
}
