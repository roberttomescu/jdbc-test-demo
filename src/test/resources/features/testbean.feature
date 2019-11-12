Feature: Database feature

  Scenario: Database interaction with jdbc
    When I check test bean
    When I insert a customer into the database
    Then I test that the customer is in the database
    When I insert the same customer in the database and get an error
    When I insert a different customer
    And I change the email
    Then I check to see if the email was changed