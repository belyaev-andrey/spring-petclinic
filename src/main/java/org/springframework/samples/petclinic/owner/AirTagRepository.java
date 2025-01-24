package org.springframework.samples.petclinic.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface AirTagRepository extends JpaRepository<AirTag, UUID> {
	AirTag findByCollar(Collar collar);
}
