# Power of Attorney Service
This awesome web service provides REST API for accessing power of attorney information of a user
  - Power of attorney details such as grantee, grantor and account details (/power-of-attorneys/{id})
  - Details for card products authorized by the power of attorney (/debit-cards/{id} and /credit-cards/{id})
  - Account details (/accounts/{id})
  - Some developer might have made an error somewhere

**To build and run:** use `mvn compile exec:java`
**Application runs on:** http://localhost:8080
**REST API documentation:** http://localhost:8080/swagger/

# Exercise!
  - Build a REST API presenting aggregated information from different services
  - Only show data that a user is actually authorized for
  - Handle any server errors you might run into gracefully
  
# Requirements
  - Requirements of the code and functionality is up to the candidate
  - We suggest using Java 11, Spring-Boot & Maven, but using Kotlin or Gradle is also fine
  - Perform whatever validation seems necessary
  - Don't return inactive products or accounts
  - (Optional) Expose the API over HTTPS
 
# Tips
  - Because every candidate has different experience and background, the candidate should decide on how complex code they want to show us
  - If the assignment is unclear, do what you feel is best and focus on the code, not the exercise
  - We look at the quality and readability of code that has been delivered more than if the functionality matches our expectations
  - Impress us!

# WWD (What Was Done)

## Requirement
  - As an user I want to get the aggregated information of the authorizations of an user by its userId,
  to be able to control its permissions. Inactive products or accounts are taking into account.

## Assumptions
  - The attribute direction "GIVEN" of power of attorney means that the grantor has given the authorizations to the grantee.
  - The attribute direction "RECEIVED" of power of attorney means that the grantee has received the authorizations from the grantor.
  - The attribute id of an account is the sequence of numbers after "NL23RABO" of the attribute account of a power of attorney.
  - One account is considered inactive when its attribute "ended" is not null.
  - One credict-card of debit-card is considered inactive when its attribute status is not "ACTIVE".

## How to build and run
  - wiremock: `mvn compile exec:java`
  - spring-boot app: `mvn spring-boot:run`
  - Service endpoint: ** http://localhost:8443/powerofattorney/authorizations/{userId}

## How to test
  - Use one of the following user ids:
    - Fellowship of the ring: ** http://localhost:8443/powerofattorney/authorizations/Fellowship%20of%20the%20ring
    - Super duper company: ** http://localhost:8443/powerofattorney/authorizations/Super%20duper%20company
    - Super duper employee: ** http://localhost:8443/powerofattorney/authorizations/Super%20duper%20employee
    - Sinterklaas: ** http://localhost:8443/powerofattorney/authorizations/Sinterklaas

## Future work
  - Identify new requirements
  - Expose API over HTTPS
