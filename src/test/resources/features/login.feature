Feature: Login Functionality

	@smaoke @regression
	Scenario Outline: Verify Login with Valid Credentials
		Given user is on login page
		When user logs in with username "<username>" and password "<password>"
		Then user should see the home page
		
		Examples:
			| username          | password |
			| ineuron@ineuron.ai| ineuron  |
			| admin@ineuron.ai  | admin123 |
