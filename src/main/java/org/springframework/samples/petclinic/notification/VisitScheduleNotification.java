package org.springframework.samples.petclinic.notification;

public record VisitScheduleNotification(int ownerId, int petId, int visitId) { }
