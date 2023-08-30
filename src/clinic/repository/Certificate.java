package clinic.repository;

public class Certificate {
    private final Diagnosis diagnosis;
    private final String title;
    private final String description;
    private final String date;

    public Certificate(Diagnosis diagnosis, String title, String description, String date) {
        this.diagnosis = diagnosis;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String print() {
        return title + "\n\n" + description + "\n\n" + date;
    }
}
