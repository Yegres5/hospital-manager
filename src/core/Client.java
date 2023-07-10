package core;

public class Client {
    public Integer clientId;
    public Integer citizenId;
    public String name;

    public Client(Integer citizenId, String name) {
        this.citizenId = citizenId;
        this.name = name;
    }
}
