package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
@TestPropertySource("/application-mysql.properties")
public class VisitApiTest {

	private static final Logger log = LoggerFactory.getLogger(VisitApiTest.class);

	@Container
	@ServiceConnection
	static MySQLContainer<?> container = new MySQLContainer<>("mysql:9.0");

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testVisitApi() throws Exception {

		String visitJson = """
			{
			    "description": "Routine Checkup"
			}
			""".stripIndent();


		MvcResult mvcResult = mockMvc.perform(post("/api/visits/{ownerId}/pets/{petId}", 6, 7)
				.contentType("application/json")
				.content(visitJson)
			)
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Routine Checkup"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.date").exists())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
			.andReturn();

		log.info(mvcResult.getResponse().getContentAsString());

	}

}
