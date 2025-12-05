import java.util.Scanner;

public class MCQTest {
    private String[] questions = {
            "1. Java is a ____ language?\nA) Procedural  B) Object-Oriented  C) Machine",
            "2. Which keyword is used to inherit?\nA) extend  B) extends  C) inherit",
            "3. Which is not a Java datatype?\nA) int  B) float  C) real"
    };

    private char[] answers = {'B', 'B', 'C'};
    private int score = 0;
    private int timeLimit = 20;

    public void start() {
        Scanner sc = new Scanner(System.in);
        long start = System.currentTimeMillis();

        for (int i = 0; i < questions.length; i++) {
            long now = System.currentTimeMillis();
            if ((now - start) / 1000 >= timeLimit) {
                System.out.println("\nTime Up! Auto-submitted.");
                break;
            }

            System.out.println(questions[i]);
            System.out.print("Your Answer: ");

            String input = sc.nextLine();
            if (input.length() == 0) continue;

            char ans = Character.toUpperCase(input.charAt(0));
            if (ans == answers[i]) score++;
        }

        System.out.println("\nYour Score: " + score + "/" + questions.length);
    }
}
