public class AlertTreasuryException extends Exception{
    private String iban;
    private String holder;
    private double operationAmount;

    public AlertTreasuryException(String message, String iban, String holder, double operationAmount) {
        super(message);
        this.iban = iban;
        this.holder = holder;
        this.operationAmount = operationAmount;
    }

    public String getIban() {
        return iban;
    }

    public String getHolder() {
        return holder;
    }

    public double getOperationAmount() {
        return operationAmount;
    }

}