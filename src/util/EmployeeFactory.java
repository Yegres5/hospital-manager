package util;

import core.Employee;
import core.Administrator;
import core.Doctor;

public class EmployeeFactory {
    public static Employee createEmployee(String name, String title) throws Exception {
        if (title == Constants.EmployeeTitle.ADMINISTRATOR.getTitle()) {
            return new Administrator(name, title);
        } else if (title == Constants.EmployeeTitle.PATHOLOGIST.getTitle()
                || title == Constants.EmployeeTitle.PRACTITIONER.getTitle()) {
            return new Doctor(name, title);
        }
        throw new Exception("Wrong title were ussed to create employee");
    }
}