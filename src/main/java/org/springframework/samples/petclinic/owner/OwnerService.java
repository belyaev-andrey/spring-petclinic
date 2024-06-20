package org.springframework.samples.petclinic.owner;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OwnerService {

	private final OwnerRepository ownerRepository;

	public OwnerService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Transactional
	public void sendEmails(LocalDateTime time) {
		ownerRepository.findAll().stream().filter(owner -> hasVisitsPlanned(owner, time)).forEach(this::sendEmail);
	}

	@EventListener
	public void receiveVisitCancellation(VisitCancellationEvent event) {
		Owner owner = ownerRepository.findById(event.getOwnerId())
			.orElseThrow(() -> new IllegalArgumentException("Invalid owner Id:" + event.getOwnerId()));
		owner.getPets().forEach(pet -> {
			pet.getVisits().removeIf(visit -> visit.getDate().equals(event.getVisitDate()));
		});
	}

	private void sendEmail(Owner owner) {

	}

	private boolean hasVisitsPlanned(Owner owner, LocalDateTime time) {
		return false;
	}

}
