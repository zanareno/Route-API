# Route API

## Objective ##

Estimate the traveling costs of driving from one point to another.
The public api endpoint receives a previous license plate of a vehicle which has its characteristics, and based on google distance api estimates only the fuel cost.

### Improve ###
At this time tolls are not included.
Fuel price is provided by a service which returns static values.


## Public API ##

Available at **http://localhost:8080/route-api/v1.0/swagger-ui.html**

## Database ##

- Tables
  - vehicle
  - brand
  - model 

## Project details ##

- Java 8
- Compilation using Maven 3 
- Mysql 8 Database
- DB versioning using flyway
 
### Compile ###
- mvn clean install (source folder)
- since DB is not install locally use mvn clean install -D skipTests (source folder)
 
### Run local ###
java -jar app.jar (available in demo-services/target)

### Docker image ###
docker build -t route . (source folder)

### Docker compose ###
docker-compose -f deploy/docker-compose.yml up -d (source folder)

## Tests ##
Running tests without DB will result in errors.

### Tests coverage ###
Note: DB is needed 
Available in target/site/jacoco/index.html"# Route-API" 
