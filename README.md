# Fantasy Football

This project is the beginnings of a system for running a fantasy football system. The back
end is being built as a suite RESTful web service endpoints that follow the JSON API specification
built using spring boot. More detail will follow as further progress is made.

## Running the Unit Tests

To run the unit tests you can run the following command:

```
./gradlew clean build
```

## Starting the service locally

To start up the service on your local machine you can run:

```
./gradlew bootRun
```

This will start the service on your local machine, once the service is started it can
be manually tested [here](http://localhost:8080/swagger-ui.html).

## Next steps

1. error handling in client
2. further unit coverage
3. acceptance / intergration testing
4. return 200 from create if entity already exists, rather than always 200.
5. add a players endpoint that does not have parent resource of clubs.