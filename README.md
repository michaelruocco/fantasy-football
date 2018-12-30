# Fantasy Football

[![Build Status](https://travis-ci.org/michaelruocco/fantasy-football.svg?branch=master)](https://travis-ci.org/michaelruocco/fantasy-football)
[![Coverage Status](https://coveralls.io/repos/github/michaelruocco/fantasy-football/badge.svg?branch=master)](https://coveralls.io/github/michaelruocco/fantasy-football?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f0cc4e9688ab4d64afbb5c19c61b7a93)](https://www.codacy.com/app/michaelruocco/fantasy-football?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/fantasy-football&amp;utm_campaign=Badge_Grade)

This project is the beginnings of a system for running a fantasy football system. The back
end is being built as a suite RESTful web service endpoints that follow the JSON API specification
built using spring boot. More detail will follow as further progress is made.

## Running the Unit Tests

To run the unit tests you can run the following command:

```
./gradlew clean build
```

## Starting the api service locally

To start up the service on your local machine you can run:

```
./gradlew bootRun
```

This will start the service on your local machine, once the service is started it can
be manually tested [here](http://localhost:8080/swagger-ui.html).

## Running cucumber tests locally

To run the cucumber tests on your local machine you will first need to run the
bootRun task as listed above to get the api service running (functionality will
be added to do this automatically shortly, but for now this is how it is run.)
Once the service is running you can run the cucumber task to run the tests

```
./gradlew cucumber
```

## Starting the UI locally

To start up the UI on your local machine you must have node JS and npm installed, if
you have these tools installed then you can run the following commands from the ui
directory:

```
npm install // only required after a first initial pull of the repo
```

```
ng serve --open
```

## Next steps

1. json api error responses from server
2. acceptance / integration testing - first stab is in place, still need improvements
   for starting service automatically when running tests (if required), asserting uuid
   ids are set on json api elements, checking not found error messages with a specific
   id, using single requests with placeholders and substituting in values.
3. error handling in client
