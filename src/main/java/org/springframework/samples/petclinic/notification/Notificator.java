package org.springframework.samples.petclinic.notification;

public interface Notificator {

	String sendSms(String phone, int ownerId, int visitId);

}
