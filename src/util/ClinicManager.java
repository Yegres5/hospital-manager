package util;

import core.Clinic;

public class ClinicManager {
    private static Clinic instance;

    public static Clinic getInstance() {
        if (instance == null) {
            instance = new Clinic("Default name", "Default address", 0.f);
        }
        return instance;
    }

    public static Clinic getInstance(String name, String address) {
        if (instance == null) {
            instance = new Clinic(name, address, 0.f);
        }
        return instance;
    }
}
