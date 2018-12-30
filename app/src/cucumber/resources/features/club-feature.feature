Feature: Club CRUD Operations

  Background:
    Given baseUri is "http://localhost:8080/"
    When the client performs DELETE request on "/clubs"
    Then status code is 204

  Scenario: POST chould create club if club does not exist
    Given request body from file "requests/create-club-request-1.json"
    And content type is "application/json"
    When the client performs POST request on "/clubs"
    Then status code is 201
    And header "Location" contains "http://localhost:8080/clubs/1"
    And response contains properties from file "requests/create-club-response.json"

  Scenario: POST should update club if club already exists
    Given request body from file "requests/create-club-request-1.json"
    And content type is "application/json"
    When the client performs POST request on "/clubs"

    Given request body from file "requests/update-club-request.json"
    And content type is "application/json"
    When the client performs POST request on "/clubs"
    Then status code is 200
    And response contains properties from file "requests/update-club-response.json"

  Scenario: Update club succeeds if club exists
    Given request body from file "requests/create-club-request-1.json"
    And content type is "application/json"
    When the client performs POST request on "/clubs"

    Given request body from file "requests/update-club-request.json"
    And content type is "application/json"
    When the client performs PUT request on "/clubs/1"
    Then status code is 200
    And response contains properties from file "requests/update-club-response.json"

  Scenario: Update club fails if club does not exist
    Given request body from file "requests/create-club-request-1.json"
    And content type is "application/json"
    When the client performs PUT request on "/clubs/1"
    Then status code is 404
    And response contains properties from file "requests/club-not-found.json"

  Scenario: Get club returns club if club exists
    Given request body from file "requests/create-club-request-1.json"
    And content type is "application/json"
    When the client performs POST request on "/clubs"

    When the client performs GET request on "/clubs/1"
    Then status code is 200
    And response contains properties from file "requests/create-club-response.json"

  Scenario: Get club fails if club does not exist
    When the client performs GET request on "/clubs/1"
    Then status code is 404
    And response contains properties from file "requests/club-not-found.json"

  Scenario: Get all clubs returns empty array if no clubs exist
    When the client performs GET request on "/clubs"
    Then status code is 200
    And response contains properties from file "requests/all-clubs-empty.json"

  Scenario: Get all clubs returns paginated clubs
    Given request body from file "requests/create-club-request-1.json"
    And content type is "application/json"
    When the client performs POST request on "/clubs"

    Given request body from file "requests/create-club-request-2.json"
    And content type is "application/json"
    When the client performs POST request on "/clubs"

    Given request body from file "requests/create-club-request-3.json"
    And content type is "application/json"
    When the client performs POST request on "/clubs"

    When the client performs GET request on "/clubs?pageNumber=0&pageSize=2"
    Then status code is 200
    And response contains properties from file "requests/all-clubs-page-1.json"

    When the client performs GET request on "/clubs?pageNumber=1&pageSize=2"
    Then status code is 200
    And response contains properties from file "requests/all-clubs-page-2.json"