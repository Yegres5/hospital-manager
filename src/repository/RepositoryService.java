package repository;

import java.util.*;

import client_care_provider.Client;
import repository.util.CertificateBuilder;
import repository.util.DiagnosisBuilder;
import repository.util.PatientBuilder;

public class RepositoryService {
    static HashMap<Integer, Patient> patientByClientStateId = new HashMap<Integer, Patient>();
    static HashMap<Integer, ArrayList<Diagnosis>> diagnosisByPatientId = new HashMap<Integer, ArrayList<Diagnosis>>();
    static HashMap<Integer, Certificate> certificateByDiagnosisId = new HashMap<Integer, Certificate>();
    static ArrayList<MedicalTest> medicalTests = new ArrayList<MedicalTest>();

    public static ArrayList<MedicalTest> getMedicalTests() {
        return medicalTests;
    }

    public static Patient getPatient(Client client) {
        if (!patientByClientStateId.containsKey(client.getStateId())) {
            Patient patient = new PatientBuilder(client).build();
            patientByClientStateId.put(client.getStateId(), patient);
        }
        return patientByClientStateId.get(client.getStateId());
    }

    public static MedicalTest getCheckUpTest() {
        return medicalTests.stream()
                .filter(medicalTest -> Objects.equals(medicalTest.getName(), "Check Up"))
                .findAny()
                .orElse(null);
    }

    public static Diagnosis getDiagnosis(Patient patient, MedicalTest medicalTest) {
        final String medicalTestName = medicalTest.getName();
        final ArrayList<Diagnosis> diagnosesForPatient = diagnosisByPatientId.get(patient.getId());

        return diagnosesForPatient.stream()
                .filter(diagnosis -> Objects.equals(medicalTestName, diagnosis.getMedicalTest().getName()))
                .findAny()
                .orElse(null);
    }

    public static Diagnosis createDiagnosis(Patient patient, MedicalTest medicalTest, String result) {
        Diagnosis diagnosis = new DiagnosisBuilder(patient, medicalTest)
                .setResult(result)
                .build();
        if (!diagnosisByPatientId.containsKey(patient.getId())) {
            diagnosisByPatientId.put(patient.getId(), new ArrayList<Diagnosis>());
        }
        diagnosisByPatientId.get(patient.getId()).add(diagnosis);
        return diagnosis;
    }

    public static Certificate createCertificate(Patient patient, MedicalTest medicalTest) {
        Diagnosis diagnosis = getDiagnosis(patient, medicalTest);

        if (!certificateByDiagnosisId.containsKey(diagnosis.getId())) {
            certificateByDiagnosisId.put(
                    diagnosis.getId(),
                    new CertificateBuilder(diagnosis).build()
            );
        }
        return certificateByDiagnosisId.get(diagnosis.getId());
    }

    public static void addMedicalTest(MedicalTest medicalTest) {
        medicalTests.add(medicalTest);
    }
}
