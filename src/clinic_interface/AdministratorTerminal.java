package clinic_interface;

import client_care_provider.Doctor;
import clinic_staff.EmployeeManagerService;
import payment.PaymentService;
import repository.*;
import schedule.Appointment;
import schedule.ScheduleService;

import java.util.ArrayList;

public class AdministratorTerminal extends BasicTerminal{

    public AdministratorTerminal(Clinic clinic) {
        super(clinic);
    }

    public Certificate generateCertificate(Patient patient, MedicalTest medicalTest) {
        return RepositoryService.createCertificate(patient, medicalTest);
    }

    public Appointment scheduleAppointment(Patient patient, MedicalTest medicalTest, Doctor doctor) {
        return ScheduleService.createAppointment(patient, medicalTest, doctor);
    }

    public void addFunds(Float funds) {
        PaymentService.addFunds(funds);
    }

    public ArrayList<MedicalTest> getMedicalTestsList() {
        return RepositoryService.getMedicalTests();
    }

    public MedicalTest getCheckUpMedicalTest() {
        return RepositoryService.getCheckUpTest();
    }

    public Doctor getDoctorForMedicalTest(MedicalTest medicalTest) {
        return EmployeeManagerService.getDoctorsForMedicalTest(medicalTest).get(0);
    }

}
