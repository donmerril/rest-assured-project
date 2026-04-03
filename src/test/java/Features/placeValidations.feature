Feature: Validating place  API

This feature covers adding a new place, updating its address, 
and verifying that the updated address is returned correctly

Scenario: Verify palce is being successfully added using AddPlace API 

Given  Add Place Payload is ready
When  User calls the "AddPlace API"
Then Response status code should be 200 
And "status" in response body is "OK"
