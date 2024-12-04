package org.springframework.samples.petclinic.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "notifications.engine", havingValue = "dev", matchIfMissing = true)
class DevSmsNotificator implements Notificator {

	private static final Logger log = LoggerFactory.getLogger(DevSmsNotificator.class);

	private final OwnerRepository ownerRepository;

	public DevSmsNotificator(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public String sendNotification(int ownerId, int visitId) {
		String s = "DEV: SMS sent to %s".formatted(ownerRepository.findOwnerById(ownerId).getTelephone());
		log.info(s);
		return s;
	}

}
