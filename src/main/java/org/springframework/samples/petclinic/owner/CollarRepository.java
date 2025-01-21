package org.springframework.samples.petclinic.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface CollarRepository extends JpaRepository<Collar, CollarId> {
	List<Collar> findByCollarId_Pet_Id(Integer collarIdPetId);
}
