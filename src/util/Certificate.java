package util;

public class Certificate {
    private Boolean positive;
    private String details = "";

    public static Certificate createNewCertificate() {
        return new Certificate();
    }

    public Certificate setServiceResultOutcome(Boolean positive) {
        this.positive = positive;
        return this;
    }

    public Certificate addDetails(String details) {
        this.details += details;
        return this;
    }

    public String printCertificate() {
        return String.format("Service outcome is %s\n%s", resultToString(), details);
    }

    public String resultToString() {
        return positive ? "positive" : "negative";
    }
}
