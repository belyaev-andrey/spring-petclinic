package org.springframework.samples.petclinic.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "notifications.engine", havingValue = "prod")
class ProdSmsNotificator implements Notificator {

	private static final Logger log = LoggerFactory.getLogger(ProdSmsNotificator.class);
	private final OwnerRepository ownerRepository;

	public ProdSmsNotificator(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public String sendNotification(int ownerId, int visitId) {
		Owner owner = ownerRepository.findOwnerById(ownerId);
		String s = "PROD: SMS sent to %s %s. Phone: %s".formatted(owner.getFirstName(), owner.getLastName(), owner.getTelephone());
		log.info(s);
		return s;
	}

}
