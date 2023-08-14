package repository;

public class Certificate {
    private final String certificateInfo;
    private final Diagnosis diagnosis;
    public Certificate(Diagnosis diagnosis, String certificateInfo) {
        this.diagnosis = diagnosis;
        this.certificateInfo = certificateInfo;
    }

    public String getCertificateInfo() {
        return certificateInfo;
    }
}
