package org.springframework.samples.petclinic.owner;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Visit}
 */
public record VisitDto(Integer id, LocalDate date) implements Serializable {
}
