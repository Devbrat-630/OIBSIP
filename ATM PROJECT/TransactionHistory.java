import java.util.ArrayList;

public class TransactionHistory {

    private ArrayList<Transaction> list = new ArrayList<>();

    public void add(String type, double amount) {
        list.add(new Transaction(type, amount));
    }

    public void display() {
        if (list.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("Transaction History:");
        for (Transaction t : list) {
            System.out.println(t);
        }
    }
}
