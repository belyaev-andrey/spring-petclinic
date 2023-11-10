package org.springframework.samples.petclinic.clinic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.owner.Visit;
import org.springframework.samples.petclinic.owner.VisitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VisitService {

	private final VisitRepository visitRepository;
	@PersistenceContext
	private EntityManager entityManager;

	public Visit rescheduleVisit(Integer visitId, LocalDate newTime) {
		Visit visit = visitRepository.findById(visitId).orElseThrow();
		visit.setDate(newTime);
		return visitRepository.save(visit);
	}

}
