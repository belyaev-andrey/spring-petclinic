package org.springframework.samples.petclinic.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "notifications.engine.type", havingValue = "dev", matchIfMissing = true)
class DevSmsNotificator implements Notificator {

	private static final Logger log = LoggerFactory.getLogger(DevSmsNotificator.class);

	private final OwnerRepository ownerRepository;

	public DevSmsNotificator(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public String sendNotification(int ownerId, int petId, int visitId) {
		Owner owner = ownerRepository.findOwnerById(ownerId);
		String s = "DEV: SMS sent to %s".formatted(owner.getTelephone());
		log.info(s);
		return s;
	}

	@Override
	@EventListener(classes = { VisitScheduleNotification.class })
	public void onVisitScheduled(VisitScheduleNotification event) {
		sendNotification(event.ownerId(), event.petId(), event.visitId());
	}

}
