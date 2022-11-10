# cloud-gateway
### step1. DI:
#### (1). Gateway
`it provides a library for building API gateways on top of Spring and Java.`
#### (2). Actuator
`it is a sub-project of the Spring Boot Framework. It uses HTTP endpoints to expose operational information about any running application.
The main benefit of using this library is that we get health and monitoring metrics from production-ready applications.`
### step2. Configuration:
```
spring:
  application:
    name: API-GATEWAY
  cloud:
    gateway:
      routes:
        - id: USER-SERVICE
          uri: lb://USER-SERVICE
          predicates:
            - Path=/v1/users/**
        - id: DEPARTMENT-SERVICE
          uri: lb://DEPARTMENT-SERVICE
          predicates:
            - Path=/v1/departments/**
```
