package clinic_staff;

public enum DoctorTitle {
    PATHOLOGIST("Pathologist"),
    PRACTITIONER("Practitioner");

    private final String title;

    private DoctorTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
