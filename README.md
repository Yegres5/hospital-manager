# hospital-manager

Self-development decomposition task solution for hospital management system

## Description

The module is designed to provide communication between hospital, employees(administrators and doctors), and clients.

Before client will receive any treatment can check-in through the administrator (__Administrator:createPatientCard(Client)__) and create a record (__PatientCard__) in clinic database. 
Then he can ask for medical tests provided by the clinic (__Administrator:getMedicalTests()__).

There can be multiple types of medical tests each of them has it's own name, cost and speciality of the doctor required for the treatment. 
The medical tests could be packed in a package in which case it would have child services (__MedicalTestPackage:medicalTests__). 
In the case of the complex service, cost will be defined only by the cost of the package.

After choosing a service, the administrator takes upfront payment and schedules an appointment for the medical test, or multiple ones in case of the medical test package (__Administrator:processMedicalTestRequest(Client, MedicalTest)__).

The doctor fulfills the required obligations at the appointment and writes diagnosis (__Doctor:provideAppointmentMedicalTest(Appointment, Client)__). 
Diagnosis could be ether positive or negative if certain disease during the appointment were found (__MedicalTest:positive__). In positive case the doctor would also attach medical report (__MedicalTest:medicalReport__).  
For the complex services doctor verifies child medical tests results (__Diagnosis:positive__) in order to determine the result of the test package.

After the client visits all of his appointments he can ask the administrator for a certificate (__Administrator:generateCertificate(Client client, MedicalTest medicalTest)__).

Generated Certificate template:
```
Certificate for 'Check up' medical test.

You test positive for 'Check up'.
Description: 
Bad result for 'Blood check' test. Description: Patient is sick.
Bad result for 'Pressure check' test. Description: Patient is sick.

Curing doctor: Doctor Pathologist
Test date: 10:36:16
```


## Used design patterns:

Builder:
* to create certificate for the client __repository.util.CertificateBuilder__. Simplifies Certificate generation by setting only Diagnosis and Date.
* to create diagnosis for the client __repository.util.DiagnosisBuilder__. Simplifies Diagnosis generation by setting positive or negative result of the test by checking for the presence of the medical report.    

Composite:
* to create single interface for basic medical tests and packages (__MedicalTest__, __BasicMedicalTest__ ,__MedicalTestPackage__).

Facade:
* to isolate the complexity of interaction employees with the clinic (__AdministratorTerminal__, __DoctorTerminal__).

## Hospital management UML class diagram

![UML class diagram](../assets/uml/class-diagram.png?raw=true)
