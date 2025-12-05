import java.util.Scanner;

public class ExamSystem {
    private User user;
    private Scanner sc = new Scanner(System.in);

    public ExamSystem(User user) {
        this.user = user;
    }

    public void menu() {
        while (true) {
            System.out.println("\n1. Update Profile");
            System.out.println("2. Change Password");
            System.out.println("3. Attempt MCQ Test");
            System.out.println("4. Logout");
            System.out.print("Choose: ");

            int c = sc.nextInt();
            sc.nextLine();

            if (c == 1) updateProfile();
            else if (c == 2) changePassword();
            else if (c == 3) startTest();
            else if (c == 4) {
                System.out.println("Logged Out.");
                return;
            } else System.out.println("Invalid Option.");
        }
    }

    private void updateProfile() {
        System.out.print("Enter new name: ");
        user.setName(sc.nextLine());
        System.out.print("Enter new email: ");
        user.setEmail(sc.nextLine());
        System.out.println("Profile Updated.");
    }

    private void changePassword() {
        System.out.print("Enter old password: ");
        if (!sc.nextLine().equals(user.getPassword())) {
            System.out.println("Wrong Password.");
            return;
        }
        System.out.print("Enter new password: ");
        user.setPassword(sc.nextLine());
        System.out.println("Password Changed.");
    }

    private void startTest() {
        MCQTest test = new MCQTest();
        test.start();
    }
}
