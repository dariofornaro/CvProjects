
 
 
Feature: M1 Clients Managment
 
    All tests related to the mandatory functionality M1
 
 
    #User Story: Register new clients
 
    Scenario Outline: Register Client
        Given a new client named "<name>"
        And I am a logistic user
        When I create his profile 
        Then the storage has the client named "<name>"
    Examples:
        | name     |
        | Paulo    | 
        | Emma     |
        | Dusana   |
 
    Scenario Outline: Using duplicated username
        Given a new client named "<name>"
        When I use a already taken username
        And I am a logistic user
        And I create his profile
        Then the storage does not have the client named "<name>"
    Examples:
        | name     |
        | Paulo    | 
        | Emma     |
        | Dusana   |
 
 
    Scenario Outline: Not matching passwords
        Given a new client named "<name>"
        When I dont match the password
        And I am a logistic user
        And I create his profile
        Then the storage does not have the client named "<name>"
    Examples:
        | name     |
        | Paulo    | 
        | Emma     |
        | Dusana   |
 
    Scenario Outline: Username too long
        Given a new client named "<name>"
        When I input a username too long
        And I am a logistic user
        When I create his profile
        Then the storage does not have the client named "<name>"
    Examples:
        | name     |
        | Paulo    | 
        | Emma     |
        | Dusana   |
 
    Scenario Outline: Password too short
        Given a new client named "<name>"
        When I input a password too short
        And I am a logistic user
        And I create his profile
        Then the storage does not have the client named "<name>"
    Examples:
        | name     |
        | Paulo    | 
        | Emma     |
        | Dusana   |
 
    Scenario Outline: ID generation
        Given a new client
        When I am a logistic user
        And I create his profile 
        Then the client gets a unique ID
 
 
 
    #User Story: Login as a client user
 
    Scenario: Client login
        Given a client user
        When the user uses the correct credencials
        And the user tries to login
        Then the user is logged in his account
        # And the user is in the client section
 
    Scenario: Unsuccessful login
        Given a client user
        When the user uses the incorrect credencials
        And the user tries to login
        Then the user is not logged in his account
 
    # Scenario: Unsuccessful login
    #     Given a client user
    #     When the user tries to login
    #     And he uses a valid username
    #     But the password does not match
    #     Then he needs to try again
 
 
    #User Story: Login as logistic user
 
    Scenario: Logistics login
        Given a logistic user
        When the user uses the correct credencials
        And the user tries to login
        Then the user is logged in his account
        # And the user is in the logistic section
 
    # Scenario: Unsuccessful login
    #     Given a logistic user
    #     When the user tries to login
    #     And he uses a invalid username
    #     Then he needs to try again
 
    # Scenario: Unsuccessful login
    #     Given a logistic user
    #     When the user tries to login
    #     And he uses a valid username
    #     But the password does not match
    #     Then he needs to try again
 
    ######################################
 
    # User Story 5 Have a user profile
    Scenario: Deleting a Client
        Given a client account
        When I am a logistic user
        And I delete the client
        Then the client is no longer in the storage
 
 
    Scenario Outline: Client information update
        Given a new name "<name>"
        And I am a client user
        When I change my first name
        Then my first name is "<name>"
    Examples:
        | name     |
        | Paulo    | 
        | Emma     |
        | Dusana   |
 
 
    Scenario Outline: Search client
        Given a valid client ID "<ID>"
        And I am a logistic user
        When I search the given ID
        Then I find the client
    Examples:
        | ID                 |
        | existing_client_ID |
 