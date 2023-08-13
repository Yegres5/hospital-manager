package repository;

public class Certificate {
    private String certificateInfo;
    private Diagnosis diagnosis;
    public Certificate(Diagnosis diagnosis, String certificateInfo) {
        this.diagnosis = diagnosis;
        this.certificateInfo = certificateInfo;
    }

    public String getCertificateInfo() {
        return certificateInfo;
    }
}
