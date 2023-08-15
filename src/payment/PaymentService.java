package payment;

public class PaymentService {
    private static Float funds = 0.F;

    public static void addFunds(Float fundsToAdd) {
        funds += fundsToAdd;
    }
}
