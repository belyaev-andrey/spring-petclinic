## Scenario:
**Initial state**: pet clinic + notification interface

**Legend**: we need to add a REST API to schedule visits and use push notifications service. We have notification services implemented. 

### Task: add API to create visits
* Add REST controller (_show quick component creation_)
* Create endpoint 
* Create service (_quick component creation_)
* Use service in controller (_bean injection_)

### Task: test API - integration test
* Create controller test
* Check return value for visit - failure, ID is null. 
* Start debugging 
* _Show connection_
* _Show transaction and entity states_
* _Talk about evaluator_
* _Show structure for entities_
* Find the problem with visit and fix it

### Task: add notification service
* Inject service into controller by interface (bean completion)
* Generate REST request to check integration
* Invoke the controller and check logs
* Switch to production mode and run the controller again
* Failure: still dev
* Start debug and check injection
* _Show bean display in text_
* _Navigate to definition_
* Observe config property
* _Show property evaluator_
* _Show property stack_
* _Show property source_
* Navigate to source
* Fix the issue
* Restart the application

Code hints:
```java
@RestController
@RequestMapping("/api/visits")
class VisitRestController {

	private final VisitService visitService;
	private final Notificator notificator;

	public VisitRestController(VisitService visitService, Notificator notificator) {
		this.visitService = visitService;
		this.notificator = notificator;
	}

	@PostMapping(value = "/{ownerId}/pets/{petId}", consumes = "application/json", produces = "application/json")
	Visit scheduleVisit(@PathVariable int ownerId, @PathVariable int petId, @Valid @RequestBody Visit visit) {
		Visit addedVisit = visitService.addVisit(ownerId, petId, visit);
		notificator.sendNotification(ownerId, addedVisit.getId());
		return addedVisit;
	}
}

@Service
class VisitService {
	private final OwnerRepository ownerRepository;

	public VisitService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Transactional
	public Visit addVisit(int ownerId, int petId, Visit visit) {
		Owner owner = ownerRepository.findOwnerById(ownerId);
		owner.getPet(petId).addVisit(visit);
		Owner saved = ownerRepository.save(owner);
		return saved.getPet(petId).getVisits().stream().max(Comparator.comparing(Visit::getDate)).orElseThrow();
	}
}
```

```java
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
```

```properties
notifications.engine=${NOTIFICATION_ENGINE:dev}
```
