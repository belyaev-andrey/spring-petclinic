package org.springframework.samples.petclinic.owner;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "airtag")
public class AirTag {

	@Id
	@Column(name = "tag_id")
	private UUID tagId;

	@Column(name = "description", nullable = false)
	private String description;

	@OneToOne
	private Collar collar;

	public UUID getTagId() {
		return tagId;
	}

	public void setTagId(UUID tagId) {
		this.tagId = tagId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
