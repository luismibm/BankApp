import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Account account = null;

        do {
            try {
                System.out.print("IBAN: ");
                String iban = sc.nextLine();
                System.out.print("Holder name: ");
                String holder = sc.nextLine();
                account = new Account(iban, holder);
            } catch (AccountException e) {
                System.out.println(e.getMessage());
            }
        } while (account == null || !account.isValid());

        int option;
        double amount;

        do {

            System.out.println("- - BANK - -");
            System.out.println("1 - Show: All Account Data");
            System.out.println("2 - Show: IBAN");
            System.out.println("3 - Show: Holder");
            System.out.println("4 - Show: Balance");
            System.out.println("5 - Show: Transactions");
            System.out.println("6 - Option: Deposit");
            System.out.println("7 - Option: Withdraw");
            System.out.println("8 - Exit");

            option = sc.nextInt();

            switch (option) {

                case 1:
                    account.printData();
                    break;

                case 2:
                    System.out.println("IBAN: " + account.getIban());
                    break;

                case 3:
                    System.out.println("Holder: " + account.getHolder());
                    break;

                case 4:
                    System.out.println("Balance: " + account.getBalance());
                    break;

                case 5:
                    account.printTransactions();
                    break;

                case 6:
                    try {
                        System.out.print("Amount to deposit: ");
                        amount = sc.nextDouble();
                        if (account.deposit(amount)) {
                            System.out.println("Deposit: Successful");
                        }
                    } catch (AccountException | AlertTreasuryException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    try {
                        System.out.print("Amount to withdraw: ");
                        amount = sc.nextDouble();
                        if (account.withdraw(amount)) {
                            System.out.println("Withdraw: Successful");
                        }
                    } catch (AccountException | AlertTreasuryException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 8:
                    System.out.println("Logging off...");
                    break;

                default:
                    System.out.println("Invalid option, try again.");
                    break;

            }

        } while(option != 8);

        sc.close();

    }
}