package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
@TestPropertySource("/application-mysql.properties")
public class VisitApiTest {

	@Container
	@ServiceConnection
	static MySQLContainer<?> container = new MySQLContainer<>("mysql:9.0");

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testVisitApi() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/visits/owner/{ownerId}/pet/{petId}", 6, 7).content("""
				{
					"description": "Test Visit"
				}
				""".stripIndent()).contentType("application/json"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Test Visit"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.date").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}

}
