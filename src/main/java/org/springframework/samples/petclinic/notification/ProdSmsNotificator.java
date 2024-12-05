package org.springframework.samples.petclinic.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "notifications.engine.type", havingValue = "prod")
class ProdSmsNotificator implements Notificator {

	private static final Logger log = LoggerFactory.getLogger(ProdSmsNotificator.class);

	private final OwnerRepository ownerRepository;

	private final Environment environment;

	public ProdSmsNotificator(OwnerRepository ownerRepository, Environment environment) {
		this.ownerRepository = ownerRepository;
		this.environment = environment;
	}

	@Override
	public String sendNotification(int ownerId, int petId, int visitId) {
		String engineKey = environment.getProperty("notifications.engine.key");
		if (!"prod".equals(engineKey)) {
			throw new IllegalStateException("Wrong key");
		}
		Owner owner = ownerRepository.findOwnerById(ownerId);
		String s = "PROD: SMS sent to %s %s. Phone: %s".formatted(owner.getFirstName(), owner.getLastName(),
				owner.getTelephone());
		log.info(s);
		log.info("Visits: ");
		owner.getPet(petId).getVisits().forEach(v -> log.info(v.toString()));
		return s;
	}

	@Override
	@EventListener(classes = { VisitScheduleNotification.class })
	public void onVisitScheduled(VisitScheduleNotification event) {
		sendNotification(event.ownerId(), event.petId(), event.visitId());
	}

}
