package org.springframework.samples.petclinic.owner;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
class OwnerApiController {

	private final OwnerRepository ownerRepository;

	public OwnerApiController(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@GetMapping("/api/owners/{ownerId}")
	public @ResponseBody OwnerDto fetchOwner(@PathVariable("ownerId") int ownerId) {
		Owner owner = ownerRepository.findById(ownerId).orElseThrow(EntityNotFoundException::new);
		return new OwnerDto(owner);
	}

	@ExceptionHandler
	public ResponseEntity<String> handle(Exception ex) {
		if (ex instanceof EntityNotFoundException) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

}
