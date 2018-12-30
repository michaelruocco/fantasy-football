Feature: Gadgets are great!

  Background:
    Given baseUri is "http://localhost:8080/"

  Scenario: Should create club
    Given request body from file "requests/new-club.json"
    And content type is "application/json"
    When the client performs POST request on "/clubs"
    Then status code is 201
    And header "Location" contains "http://localhost:8080/clubs/1"