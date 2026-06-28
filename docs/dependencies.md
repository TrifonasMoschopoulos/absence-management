## Project Dependencies
1. **Spring Boot starter parent**
    **Version: 3.x**
    - dependency version management for most Spring dependencies 
    - default plugin configurations: 
        - maven-compiler-plugin
        - maven-surefire-plugin
        - maven-jar-plugin
        - spring-boot-maven-plugin
    

2. **Spring Boot starter Web**
    - Provides Tomcat 
    - HTTP primitives
    - MVC framework (controllers, mappings)
    - spring-boot-starter (bean auto-configuration, logging)
    - Jackson
    - Global Exception Handling

3. **Spring Boot Starter Data JPA**
    - Hibernate
    - Converts java objects into DB tables using ORM
    - Provide repository interfaces for common db operations

4. **Spring Boot Starter Validation**
    - Provide bean validation for user input

5. **Springdoc OpenAPI starter webmvc ui**
    - Documentation, Swagger-UI

6. **PostgreSQL JDBC Driver**
    - Connection with the database