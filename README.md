# java-restapi-lab
## Motivation
This is a lab ground to create and test REST API using different tools and frameworks.

restapi-tester: Project to test all different REST API created. (Error & Exception handling > Work in progress)
|Type|DI tool|Testing tool|
|-----|-------|------|
|Console|Weld cdi (Work in progress)|JUnit Jupiter (Work in progress)

springboot-example: REST API project created using Spring Boot.
|Framework|Database|DI tool|Testing Tool|
|------|-------|-------|----|
|Spring Boot|H2 Database|Spring DI|Spring Boot Starter Test|

jersey-example (Work in progress): REST API project created using Jersey.
|Framework|Database|DI tool|Testing Tool|
|------|-------|-------|----|
|Jersey|H2 Database|HK2|JUnit Jupiter|

## Other frameworks/ tools planned
* REST API framework: Resteasy, 
* ORM framework: Hibernate, MyBatis, 
* Testing Tool: 
* DI Tool:
* Database: 

## Other planned work
1. Add HATEOAS support (with better status code)
2. Add maven support
3. Add Apache Http Client support
4. Add logging support
5. Different http status & exceptions handling in console app
6. 

## Notes
* All projects are developed using text editor (with plugins/ extensions) and mainly tested using cli (e.g. cURL)
* Some useful command (gradlew):
  * build
  * clean
  * test
  * bootrun
    
