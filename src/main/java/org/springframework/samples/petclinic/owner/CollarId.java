package org.springframework.samples.petclinic.owner;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CollarId implements Serializable {

	@Column(nullable = false)
	private Long serialId;

	@OneToOne(optional = false)
	@JoinColumn(name = "pet_id", nullable = false)
	private Pet pet;


	public Long getSerialId() {
		return serialId;
	}

	public void setSerialId(Long serialId) {
		this.serialId = serialId;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		CollarId collarId = (CollarId) o;
		return Objects.equals(serialId, collarId.serialId);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(serialId);
	}
}
