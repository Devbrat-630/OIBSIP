import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User user = new User("dev123", "1234", "Devbrat", "dev@example.com");

        Scanner sc = new Scanner(System.in);

        System.out.print("User ID: ");
        String id = sc.nextLine();
        System.out.print("Password: ");
        String pw = sc.nextLine();

        if (id.equals(user.getUserId()) && pw.equals(user.getPassword())) {
            ExamSystem exam = new ExamSystem(user);
            exam.menu();
        } else {
            System.out.println("Login Failed.");
        }
    }
}
