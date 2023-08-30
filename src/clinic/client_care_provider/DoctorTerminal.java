package clinic.client_care_provider;

import clinic.repository.Diagnosis;
import clinic.repository.MedicalTest;
import clinic.repository.PatientCard;
import clinic.repository.Repository;
import clinic.repository.util.DiagnosisBuilder;
import jdk.jshell.Diag;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DoctorTerminal {
    private final Repository repository;

    public DoctorTerminal(Repository repository) {
        this.repository = repository;
    }

    public Diagnosis addDiagnosisResult(PatientCard patientCard, MedicalTest medicalTest, Doctor doctor, String medicalReport, String date) {
        Diagnosis diagnosis = new DiagnosisBuilder()
                .setDate(date)
                .setDoctor(doctor)
                .setMedicalReport(medicalReport)
                .setMedicalTest(medicalTest)
                .build();

        repository.insertDiagnosis(patientCard, diagnosis);
        return diagnosis;
    }

    public Diagnosis getDiagnosis(PatientCard patientCard, MedicalTest medicalTest) {
        return repository.findDiagnosis(patientCard, medicalTest);
    }

    public String getTodaysDate() {
        return LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
    }
}
