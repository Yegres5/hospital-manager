package clinic_interface;

import repository.Diagnosis;
import repository.MedicalTest;
import repository.Patient;
import repository.RepositoryService;

public class DoctorTerminal extends BasicTerminal {
    public DoctorTerminal(Clinic clinic) {
        super(clinic);
    }

    public Diagnosis getDiagnosis(Patient patient, MedicalTest medicalTest) {
        return RepositoryService.getDiagnosis(patient, medicalTest);
    }

    public Diagnosis createDiagnosis(Patient patient, MedicalTest medicalTest, String result) {
        return RepositoryService.createDiagnosis(patient, medicalTest, result);
    }
}
