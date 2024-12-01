package org.springframework.samples.petclinic.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@ConditionalOnProperty(name = "notifications.enabled", havingValue = "true")
class DevSmsNotificator implements Notificator {

	private static final Logger log = LoggerFactory.getLogger(DevSmsNotificator.class);

	@Override
	public String sendSms(String phone, int ownerId, int visitId) {
		String s = "SMS sent to %s".formatted(phone);
		log.info(s);
		return s;
	}

}
