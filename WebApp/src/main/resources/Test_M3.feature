Feature: M3 Clients Managment

 Scenario: Set measurements
        Given a logistic account 
        When I access the list of containers
        And I select one of them
        Then I can update the measurements for its internal status 

# we don't have this feature in case we delete this scenario. It can be repeated with pressure and Humidity
Scenario: Temperature out of range 
    Given a logistic account 
    When I access the list of containers
    And I select one of them
    But the temperature has a value far from the reality 
    Then I can update the measurements for its internal status 


Scenario: Check Container Status
    Given a client account 
    When I access the list of my Journey
    And I check the countainer associated to it
    Then I can see a list with all the history of the measurements 

Scenario: Empty measurements' history 
    Given a client account 
    When I access the list of my Journey
    And I check the countainer associated to it
    But no measurements have been set from the logistic company 
    Then I can see no measurements 

 