package org.springframework.samples.petclinic.notification;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@ConditionalOnProperty(name = "notifications.enabled", havingValue = "true")
class ProdSmsNotificator implements Notificator {

	@Override
	public String sendSms(String phone, int ownerId, int visitId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
