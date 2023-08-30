package clinic.client_care_provider;

public class Client {
    private String name;
    private String healthInfo;

    public Client(String name, String healthInfo) {
        this.name = name;
        this.healthInfo = healthInfo;
    }

    public String getHealthInfo() {
        return healthInfo;
    }
}
