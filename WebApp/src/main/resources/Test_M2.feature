Feature: M2 Clients Managment

# 
Scenario: Container registration 
    Given a client account
    When I access the journeys management page
    And I want to add a new Journey 
    And I input the journey's parameters
    Then a registration notification will be sent to the logistic company

Scenario: not covered port
    Given a client account
    When I access the journeys management page
    And I want to add a new Journey 
    And I input the journey's parameters
    But I input a not covered port 
    Then the Journey will not be registered 

Scenario: approval of registration
    Given a logistic account
    When I access the Journey
    And I will click on a journey
    And I will change the status to confirmed 
    Then the corresponding client will have his Journey confirmed
#############
Scenario: Tracking my shipment 
    Given a client account 
    When I access the Journey management page
    And I will click on an active journey
    Then I will be able to locate my journey
##################
Scenario: Getting the Journey ID 
    Given a client account 
    When I access the Journey management page
    And I will click on an active journey
    Then I will be able to get my journey ID 

Scenario: Sharing of tracking code
    Given a customer of the logistic company's client 
    When  I access the home page of the logistic company
    And I input a tracking code 
    Then I will be to locate the associated shipment


