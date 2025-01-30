package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.thymeleaf.expression.Strings;

import java.util.List;
import java.util.Locale;

@DataJpaTest
@Testcontainers
@TestPropertySource(properties = {
	"database=postgres",
	"spring.sql.init.mode=always",
	"logging.level.sql=debug"}
)
public class OwnerDataAccessTest {

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:17.0");
	@Autowired
	private OwnerRepository ownerRepository;

	@Test
	void testFindOwnersByEmptyLastName() {
		Page<Owner> owners = ownerRepository.findByLastName("", Pageable.unpaged());
		Assertions.assertFalse(owners.getContent().isEmpty());
		owners.forEach(owner ->
			owner.getPets().forEach(pet -> Assertions.assertNotNull(pet.getId()))
		);
	}

	@Test
	void testCheckListJoin() {
		Page<Owner> owners = ownerRepository.findByLastName("", Pageable.unpaged());
		Assertions.assertFalse(owners.getContent().isEmpty());
		Strings s = new Strings(Locale.ENGLISH);
		owners.forEach(owner -> {
			List<String> strings = s.listToString(owner.getPets());
			Assertions.assertNotNull(strings);
			}
		);
	}

}
