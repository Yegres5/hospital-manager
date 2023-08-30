package clinic.repository.util;

import clinic.repository.Certificate;
import clinic.repository.Diagnosis;

public class CertificateBuilder {
    private Diagnosis diagnosis;
    private String date;
    private static final String TITLE_TEMPLATE = "Certificate for '%s' medical test.";
    private static final String NEGATIVE_DESCRIPTION_TEMPLATE = "You test negative for '%s'.\nCuring doctor: %s\nTest date: %s";
    private static final String POSITIVE_DESCRIPTION_TEMPLATE = "You test positive for '%s'.\nDescription: \n%s\nCuring doctor: %s\nTest date: %s";

    public CertificateBuilder setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
        return this;
    }

    public CertificateBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public Certificate build() {
        return new Certificate(diagnosis, generateTitle(), generateDescription(), date);
    }

    private String generateTitle() {
        return String.format(TITLE_TEMPLATE, diagnosis.getMedicalTest().getName());
    }

    private String generateDescription() {
        if (diagnosis.getPositive()) {
            return String.format(
                    POSITIVE_DESCRIPTION_TEMPLATE,
                    diagnosis.getMedicalTest().getName(),
                    diagnosis.getMedicalReport(),
                    diagnosis.getDoctor().getName(),
                    diagnosis.getDate()
            );
        }
        return String.format(
                NEGATIVE_DESCRIPTION_TEMPLATE,
                diagnosis.getMedicalTest().getName(),
                diagnosis.getDoctor().getName(),
                diagnosis.getDate()
        );
    }
}
