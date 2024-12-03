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

