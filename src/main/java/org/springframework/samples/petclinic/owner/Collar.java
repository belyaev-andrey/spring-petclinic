package org.springframework.samples.petclinic.owner;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "collar")
public class Collar {

	@EmbeddedId
	private CollarId collarId;

	@Column(name = "collar_message")
	private String collarMessage;

	public CollarId getCollarId() {
		return collarId;
	}

	public void setCollarId(CollarId collarId) {
		this.collarId = collarId;
	}

	public String getCollarMessage() {
		return collarMessage;
	}

	public void setCollarMessage(String collarMessage) {
		this.collarMessage = collarMessage;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Collar collar = (Collar) o;
		return Objects.equals(collarId, collar.collarId);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(collarId);
	}
}
