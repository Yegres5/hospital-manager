package clinic.client_care_provider;

import clinic.Clinic;

public class Employee {
    protected final String name;
    protected final Clinic clinic;

    public Employee(String name, Clinic clinic) {
        this.name = name;
        this.clinic = clinic;
    }

    public String getName() {
        return name;
    }

    public Clinic getClinic() {
        return clinic;
    }
}
