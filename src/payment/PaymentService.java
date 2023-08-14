package payment;

public class PaymentService {
    private static Float funds;

    public static void addFunds(Float fundsToAdd) {
        funds += fundsToAdd;
    }
}
