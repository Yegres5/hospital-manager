package payment;

public class PaymentService {
    private static Float _funds;

    public static void addFunds(Float funds) {
        _funds += funds;
    }
}
