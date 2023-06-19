# hospital-manager

Self development decomposition task solution for hospital management system

## Description

Module is designed to provide communication between hospital, employees(administrators and doctors) and clients.

Client can check-in through administrator (Administrator:checkIn(name, citizenId)) and ask for services provided by the clinic (Administrator:getServiceList()).

There can me multiple types of the services (Service:type, Example: patient examination, taking blood test, ...) each of them could have a cost (Service:cost). The service could also be complex in which case it would have child services (Service:child services). In case of the complex service cost should be calculated by aggregating prices of the child services and price of the service itself (Service:cost).
After choosing a service, the administrator could take upfront payment (Service Status:payed) and would shedule an appointment with the doctor.

Doctor provides the service at the appointment, outcome of the service is tracked by status (Service Status:currentStatus). Service could be rescheduled (Service Status:currentStatus Scheduled->Scheduled) in case if doctor would decide to send a patient for additional tests or in order to fulfill the complex service requirements. For the complex services doctor verifies child services results (Service Status:positive and Service Status:result) in order to determine result of his service.

After the client visits all of his appointments he can ask the administrator for the certificate and pay for the provided services if he hasn't done it upfront (Service Status:payed).

## Used design patterns

Chain of Responsibility:
_ Is used to provide simple interface to verify all of the child services are (negative). If some of the tests are positive results list of the service status objects would be returned.
_ Is used to provice simple interface to verify all of the child services has been payed by the client. If some of the services hasn't been payed list of the service status objects would be returned.

Singleton:
_ To retrieve clinic of the employee (for the administrator to add assets).
_ To retrieve the catalog.

Factory: \* To create employee for the clinic (administrator or doctor).

## Hospital management UML class diagram

![UML class diagram](../assets/uml/class-diagram.png?raw=true)
