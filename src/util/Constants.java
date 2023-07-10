package util;

import java.util.HashMap;

public class Constants {
    public enum ServiceStatus {
        NEW("New"),
        SCHEDULED("Scheduled"),
        FINISHED("Finished");

        private String status;

        private ServiceStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }

    public enum ServiceType {
        BLOOD_TEST("Blood test"),
        EXAMINATION("Examination");

        private String type;

        private ServiceType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum EmployeeTitle {
        ADMINISTRATOR("Administrator"),
        PATHOLOGIST("Pathologist"),
        PRACTITIONER("Practitioner");

        private String title;

        private EmployeeTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    public static HashMap<String, String> ServiceTypeToEmploeeTitle = new HashMap<>();
    static {
        ServiceTypeToEmploeeTitle.put(ServiceType.BLOOD_TEST.getType(), EmployeeTitle.PATHOLOGIST.getTitle());
        ServiceTypeToEmploeeTitle.put(ServiceType.EXAMINATION.getType(), EmployeeTitle.PRACTITIONER.getTitle());
    }
}
