# spring-boot-jpa-rest-demo-filter-paging-sorting

An example application using Spring boot MVC, Spring Data JPA with the ability to do filter, pagination and sorting.

## Filter

REST APIs handles filter as followed in the demo application. 

It parses and converts the following notions as `org.springframework.data.jpa.domain.Specification`. The operation and specification mapping are kept as operation to lambda functions.

|   Symbol       |Operation                      |example filter query param                     |
|----------------|-------------------------------|-----------------------------|
|eq              | Equals                        |`city=eq:Sydney`
|neq             | Not Equals                     |`country=neq:uk`            |
|gt              | Greater Than                  |`amount=gt:10000`            |
|gte             | Greater Than or equals to     |`amount=gte:10000`            |
|lt              | Less Than                     |`amount=lt:10000`            |
|lte             | Less Than or equals to        |`amount=lte:10000`            |
|in             | IN                             |`country=in:uk, usa, au`            |
|nin             | Not IN                        |`country=nin:fr, de, nz`            |
|btn             | Between                      |`joiningDate=btn:2018-01-01, 2016-01-01`            |
|like             | Like                     |`firstName=like:John`            |

## Paging
The API's query params 'pageNumber' & 'pageSize' are converted to `org.springframework.data.domain.PageRequest`

Sample
.....?pageSize=10&pageNumber=2

## Sorting
'sort' query param with comma separated attributes prefixed with either '+' (ASC Order) or '-' (DESC Order) are converted to `org.springframework.data.domain.Sort` with `org.springframework.data.domain.PageRequest`

Example: .....?sort=+salary,+joiningDate

## Technology stack
-   Spring Boot
-   Spring MVC
-   Spring Data JPA
-   Hibernate 5.x
-   Swagger
-   Spring Boot Test/JUnit/Mockito/RestAssured

## Prerequisites
- JDK 8
- Maven

## Run
mvn spring-boot:run

