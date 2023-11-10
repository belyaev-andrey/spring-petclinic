package org.springframework.samples.petclinic;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.samples.petclinic.owner.Visit;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VisitEventListener {

	@PostUpdate
	@PostPersist
	public void onPostUpdate(Visit visit) {

	}

}
