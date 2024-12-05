package org.springframework.samples.petclinic.owner;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visits")
class VisitApiController {

	@PostMapping(value = "/owner/{ownerId}/pet/{petId}", consumes = "application/json", produces = "application/json")
	Visit scheduleVisit(@PathVariable int ownerId, @PathVariable int petId, @Valid @RequestBody Visit visit) {
		return null;
	}

}
