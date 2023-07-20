# hospital-manager

Self-development decomposition task solution for hospital management system

## Description

The module is designed to provide communication between hospital, employees(administrators and doctors), and clients.

A client can check in through the administrator (__Administrator:checkIn(name, citizenId)__) 
and ask for services provided by the clinic (__Administrator:getServiceList()__).

There can be multiple types of services (__Service:type__, Example: patient examination, 
taking a blood test, ...) each of them could have a cost (__Service:cost__). The service 
could also be complex in which case it would have child services (__Service:child service__). 
In the case of the complex service, cost should be calculated by aggregating prices of the 
child services and the price of the service itself (__Service:cost__).

After choosing a service, the administrator could take an upfront payment 
(__Administrator:recievePaymentForService(serviceId)__). As a result administrator 
will create Service Status and after schedule an appointment with the doctor 
(__Administrator:scheduleAppointment(cliendId, doctorId)__) that will be given in a list 
of the available ones (__Administrator:getDoctorsForService__).

The doctor provides the service at the appointment (__Doctor__:provideService(serviceStatusId)), 
the outcome of the service is tracked by status (__Service Status:currentStatus__). 
Service could be rescheduled (__Service Status:currentStatus__ Scheduled->Scheduled) 
in case if the doctor would decide to send a patient for additional tests or in order to 
fulfill the complex service requirements. For the complex services doctor verifies child 
services results (__Service Status:positive__ and __Service Status:result__) in order 
to determine the result of his service.

After the client visits all of his appointments he can ask the administrator for the 
certificate and pay for the provided services if he hasn't done it up front 
(__Service Status:payed__). 

Generated Certificate template:
```
Certificate:

Service outcome is positive/negative
Service Status:result (if the outcome is positive)
```


## Used design patterns

Builder:
* To create certificate for the client. __util.Certificate__

Singleton:
* To retrieve the clinic of the employee (for the administrator to add assets). __util.ClinicManager__

Factory: 
* To create employees for the clinic (administrator or doctor). __util.EmployeeFactory__

## Hospital management UML class diagram

![UML class diagram](../assets/uml/class-diagram.png?raw=true)
