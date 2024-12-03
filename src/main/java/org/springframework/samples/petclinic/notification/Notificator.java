package org.springframework.samples.petclinic.notification;

public interface Notificator {

	String sendNotification(int ownerId, int visitId);

}
