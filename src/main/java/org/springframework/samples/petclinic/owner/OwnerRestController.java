package org.springframework.samples.petclinic.owner;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerRestController {

	private final OwnerService ownerService;

	@GetMapping
	public Page<Owner> findAll(@Nullable Pageable pageable) {
		return ownerService.findAll(pageable);
	}

	@GetMapping("/{id}")
	public Owner findById(@PathVariable Integer id) {
		return ownerService.findById(id);
	}

	@GetMapping("/{lastName}")
	public Page<Owner> findByLastName(@PathVariable String lastName, @Nullable Pageable pageable) {
		return ownerService.findByLastName(lastName, pageable);
	}

	@PostMapping
	public void save(@RequestBody @Valid Owner owner) {
		ownerService.save(owner);
	}
}

