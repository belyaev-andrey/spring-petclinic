package org.springframework.samples.petclinic.owner;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class VisitService {

	private final OwnerRepository ownerRepository;

	public VisitService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Transactional
	public Owner saveVisit(Owner owner, int petId, Visit visit) {
		Owner currentOwner = ownerRepository.findOwnerById(owner.getId());
		Pet pet = currentOwner.getPets().stream()
			.filter(p -> p.getId() == petId).findFirst().orElseThrow(EntityNotFoundException::new);
		pet.addVisit(visit);
		return ownerRepository.save(currentOwner);
	}

}
