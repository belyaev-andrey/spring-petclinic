package org.springframework.samples.petclinic.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.SpringApplicationEvent;

public class RescheduleEvent extends SpringApplicationEvent {

	public RescheduleEvent(SpringApplication application, String[] args) {
		super(application, args);
	}

}
