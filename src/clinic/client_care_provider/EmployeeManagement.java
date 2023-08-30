package clinic.client_care_provider;

import java.util.ArrayList;

public class EmployeeManagement {
    private ArrayList<Employee> employees = new ArrayList<>();

    public void insertEmployee(Employee employee) {
        employees.add(employee);
    }

    public ArrayList<Employee> retrieveEmployees() {
        return employees;
    }
}
