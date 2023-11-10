package org.springframework.samples.petclinic;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.samples.petclinic.clinic.RescheduleEvent;
import org.springframework.samples.petclinic.owner.Visit;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VisitEventListener {
	private final KafkaTemplate<String, Visit> kafkaTemplate;

	@PostUpdate
	@PostPersist
	public void onPostUpdate(Visit visit) {
		kafkaTemplate.send("visitUpdate", visit);
	}

	@KafkaListener(topics = "visitUpdate", containerFactory = "listenerFactory")
	public void consumeVisit(Visit object) {
		log.info(object.getDescription());
	}

	@EventListener
	public void handleApplicationEnvironmentPreparedEvent(ApplicationEnvironmentPreparedEvent event) {
	}

	@EventListener
	public void handleRescheduleEvent(RescheduleEvent event) {

	}


}
