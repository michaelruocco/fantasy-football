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

## Starting the backend service locally

To start up the service on your local machine you can run:

```
./gradlew bootRun
```

This will start the service on your local machine, once the service is started it can
be manually tested [here](http://localhost:8080/swagger-ui.html).

## Starting the frontend UI locally

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

1. add environment config in ui
2. json api error responses from server
3. acceptance / integration testing
4. error handling in client
