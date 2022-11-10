# cloud-gateway
### step1. DI:
#### (1). Gateway
`it provides a library for building API gateways on top of Spring and Java.`
#### (2). Actuator
`it is a sub-project of the Spring Boot Framework. It uses HTTP endpoints to expose operational information about any running application.
The main benefit of using this library is that we get health and monitoring metrics from production-ready applications.`
#### (3). Hystrix
`it is a library from Netflix. Hystrix isolates the points of access between the services, stops cascading failures across them and provides the fallback options.`
```
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class CloudGatewayApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "application");
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

}
```
### step2. Configuration:
```
spring:
  main:
    web-application-type: reactive
  application:
    name: API-GATEWAY
  cloud:
    gateway:
      routes:
        - id: USER-SERVICE
          uri: lb://USER-SERVICE
          predicates:
            - Path=/v1/users/**
          filters:
             - name: CircuitBreaker
               args:
                 name: USER-SERVICE
                 fallbackuri: farward:/userServiceFallBack
        - id: DEPARTMENT-SERVICE
          uri: lb://DEPARTMENT-SERVICE
          predicates:
            - Path=/v1/departments/**
          filters:
             - name: CircuitBreaker
               args:
                 name: DEPARTMENT-SERVICE
                 fallbackuri: farward:/departmentServiceFallBack

#Hystrix Configuration
hystrix:
  command:
    fallbackcmd:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 4000

management:
  endpoints:
    web: 
      exposure:
        include: hystrix.stream
```
