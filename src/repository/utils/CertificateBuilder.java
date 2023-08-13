package repository.utils;

import repository.Certificate;
import repository.Diagnosis;

public class CertificateBuilder {
    private final Diagnosis diagnosis;
    public CertificateBuilder(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Certificate build() {
        final String DEFAULT_HEALTHY_MESSAGE = "No diseases detected";
        String certificateInfo;

        if (diagnosis.isPositive()) {
            certificateInfo = getTitle() + DEFAULT_HEALTHY_MESSAGE;
        } else {
            certificateInfo = getTitle()  + diagnosis.getResult();
        }

        return new Certificate(diagnosis, certificateInfo);
    }

    private String getTitle() {
        return "Certificate for " + diagnosis.getMedicalTest().getName() + " test.\n";
    }
}
