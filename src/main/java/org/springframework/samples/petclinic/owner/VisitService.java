package org.springframework.samples.petclinic.owner;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitService {

	private final OwnerRepository ownerRepository;

	public VisitService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Transactional
	public Visit saveVisit(int ownerId, int petId, Visit visit) {
		Owner currentOwner = ownerRepository.findOwnerById(ownerId);
		Pet pet = currentOwner.getPets()
			.stream()
			.filter(p -> p.getId() == petId)
			.findFirst()
			.orElseThrow(EntityNotFoundException::new);
		pet.addVisit(visit);
		ownerRepository.save(currentOwner);
		return visit;
	}

}
