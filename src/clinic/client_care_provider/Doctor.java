package clinic.client_care_provider;

import clinic.Clinic;
import clinic.repository.*;
import clinic.schedule.Appointment;

import java.util.Objects;

public class Doctor extends Employee {
    private final Speciality speciality;

    public Doctor(String name, Clinic clinic, Speciality speciality) {
        super(name, clinic);
        this.speciality = speciality;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public Diagnosis provideAppointmentMedicalTest(Appointment appointment, Client client) {
        String examinationResult = examinationResult(client, appointment);
        return getTerminal().addDiagnosisResult(
                appointment.getPatientCard(),
                appointment.getMedicalTest(),
                this,
                examinationResult,
                getTerminal().getTodaysDate()
        );
    }

    private String examinationResult(Client client, Appointment appointment) {
        StringBuilder result = null;
        MedicalTest medicalTest = appointment.getMedicalTest();
        if (medicalTest instanceof BasicMedicalTest) {
            if (Objects.equals(client.getHealthInfo(), "Sick")) {
                return "Patient is sick.";
            }
        } else if (medicalTest instanceof MedicalTestPackage medicalTestPackage) {
            PatientCard patientCard = appointment.getPatientCard();
            result = new StringBuilder();
            for (MedicalTest childMedicalTest : medicalTestPackage.getMedicalTests()) {
                Diagnosis diagnosis = getTerminal().getDiagnosis(patientCard, childMedicalTest);
                if (diagnosis.getPositive()) {
                    result.append(
                            String.format(
                                    "Bad result for '%s' test. Description: %s\n",
                                    diagnosis.getMedicalTest().getName(),
                                    diagnosis.getMedicalReport()
                            )
                    );
                }
            }

            if (!result.isEmpty()) {
                return result.toString();
            }
        }
        return null;
    }

    private DoctorTerminal getTerminal() {
        return clinic.getDoctorTerminal();
    }
}
