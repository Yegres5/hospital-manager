import core.*;
import util.*;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;

public class App {
        public static void main(String[] args) throws Exception {
                // Default clinic creation
                Clinic clinic = ClinicManager.getInstance();

                Administrator administrator = new Administrator(
                                "Default admin",
                                Constants.EmployeeTitle.ADMINISTRATOR.getTitle());
                clinic.addNewEmplooyee(administrator);

                Doctor doctorPathologist = new Doctor(
                                "Default Pathologist",
                                Constants.EmployeeTitle.PATHOLOGIST.getTitle());
                clinic.addNewEmplooyee(doctorPathologist);

                Doctor doctorPractitioner = new Doctor(
                                "Default Practitioner",
                                Constants.EmployeeTitle.PRACTITIONER.getTitle());
                clinic.addNewEmplooyee(doctorPractitioner);

                // Creation of the parent Examination test
                Service serviceExamination = new Service(
                                Constants.ServiceType.EXAMINATION.getType(),
                                "Default examination",
                                150.f,
                                null);
                Catalog.putService(serviceExamination);

                // Creation of the child blood test for the Examination parent
                Service serviceBloodTest = new Service(
                                Constants.ServiceType.BLOOD_TEST.getType(),
                                "Default blood test",
                                50.f,
                                serviceExamination.serviceId);
                Catalog.putService(serviceBloodTest);

                // Client asks for list of services
                List<Service> services = administrator.getServiceList();
                for (Service service : services) {
                        System.out.println(service.serviceId + " " + service.name + " " + service.cost);
                }
                Service pickedComplexService = serviceExamination;

                // Client goes through check in
                Client client = administrator.checkIn("Default name", 007);
                // Client choose the service
                // Client registers for the service and pays the fees
                System.out.println(
                                "Price for full service: "
                                                + administrator.getServiceTotalCost(pickedComplexService.serviceId));
                List<ServiceStatus> serviceStatuses = administrator.recievePaymentForService(
                                pickedComplexService.serviceId,
                                client.clientId,
                                200.f);
                assert serviceStatuses != null : "Client gave less money for the service that reqired";

                // Client recieves the Appointments to the doctors
                List<Appointment> clientAppointments = new ArrayList<Appointment>();
                ListIterator<ServiceStatus> iterator = serviceStatuses.listIterator(serviceStatuses.size());

                // Create appointments from simple/child services
                while (iterator.hasPrevious()) {
                        ServiceStatus serviceStatus = iterator.previous();
                        // List of the doctors for the client
                        Doctor doctor = administrator.getDoctorsForService(serviceStatus.serviceId).get(0);
                        // Client choose doctor from the lits and creates an appointment through the
                        // administrator
                        clientAppointments.add(administrator.scheduleAppointment(doctor.employeeId,
                                        serviceStatus.serviceStatusId));
                }

                // Doctor recieves the client and provides the service (diagnosis)
                for (Appointment appointment : clientAppointments) {
                        Doctor doctor = (Doctor) ClinicManager.getInstance().getEmployee(appointment.doctorId);
                        doctor.provideService(appointment.appointmentId);
                }

                // Client visits the administrator and recieves the certificate

        }
}
