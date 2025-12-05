import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String userId = "1234";
        String pin = "4321";

        User user = new User(userId, pin, 10000);

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String id = sc.nextLine();

        System.out.print("Enter PIN: ");
        String p = sc.nextLine();

        if (id.equals(user.getUserId()) && p.equals(user.getPin())) {
            ATM atm = new ATM(user);
            atm.menu();
        } else {
            System.out.println("Invalid Login.");
        }
    }
}
