# hospital-manager

Self-development decomposition task solution for hospital management system

## Description

The module is designed to provide communication between hospital, employees(administrators and doctors), and clients.

A client can check-in through the administrator (__Administrator:checkIn(name, citizenId)__) and ask for services provided by the clinic (__Administrator:getServiceList()__).

There can be multiple types of services (__Service:type__, Example: patient examination, taking a blood test, ...) each of them could have a cost (__Service:cost__). The service could also be complex in which case it would have child services (__Service:child services__). In the case of the complex service, cost should be calculated by aggregating prices of the child services and the price of the service itself (__Service:cost__).
After choosing a service, the administrator could take an upfront payment (__Service Status:payed__) and would schedule an appointment with the doctor.

The doctor provides the service at the appointment, the outcome of the service is tracked by status (__Service Status:currentStatus__). Service could be rescheduled (__Service Status:currentStatus__ Scheduled->Scheduled) in case if the doctor would decide to send a patient for additional tests or in order to fulfill the complex service requirements. For the complex services doctor verifies child services results (__Service Status:positive__ and __Service Status:result__) in order to determine the result of his service.

After the client visits all of his appointments he can ask the administrator for the certificate and pay for the provided services if he hasn't done it up front (__Service Status:payed__).

## Used design patterns

Chain of Responsibility:
* Is used to provide a simple interface to verify all of the child services are (negative). If some of the tests are positive results list of the service status objects would be returned.
* Is used to provide a simple interface to verify all of the child services have been paid by the client. If some of the services haven't been paid list of the service status objects would be returned.

Singleton:
* To retrieve the clinic of the employee (for the administrator to add assets).
* To retrieve the catalog.

Factory: 
* To create employees for the clinic (administrator or doctor).

## Hospital management UML class diagram

![UML class diagram](../assets/uml/class-diagram.png?raw=true)
