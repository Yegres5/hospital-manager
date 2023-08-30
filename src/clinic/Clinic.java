package clinic;

import clinic.client_care_provider.AdministratorTerminal;
import clinic.client_care_provider.DoctorTerminal;
import clinic.client_care_provider.EmployeeManagement;
import clinic.repository.Repository;
import clinic.schedule.Scheduler;

public class Clinic {
    private final Repository repository;
    private final Scheduler scheduler;
    private final EmployeeManagement employeeManagement;
    private Float assets = 0.F;

    public Clinic(Repository repository, Scheduler scheduler, EmployeeManagement employeeManagement) {
        this.repository = repository;
        this.scheduler = scheduler;
        this.employeeManagement = employeeManagement;
    }

    public AdministratorTerminal getAdministratorTerminal() {
        return new AdministratorTerminal(repository, scheduler, employeeManagement);
    }

    public DoctorTerminal getDoctorTerminal() {
        return  new DoctorTerminal(repository);
    }
    public void addAssets(Float assets) {
        this.assets += assets;
    }

}
