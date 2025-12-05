import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int round = 1;
        boolean playAgain = true;

        System.out.println("=====================================");
        System.out.println("          GUESS THE NUMBER ");
        System.out.println("=====================================");

        while (playAgain) {

            System.out.println("\n---------- ROUND " + round + " ----------");

            int number = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 7;
            boolean correct = false;

            while (attempts < maxAttempts) {

                System.out.print("Enter your guess (1-100): ");
                int guess = sc.nextInt();
                attempts++;

                if (guess == number) {
                    System.out.println("Correct! You guessed the number.");
                    int points = (maxAttempts - attempts + 1) * 10;
                    totalScore += points;
                    System.out.println("Attempts used: " + attempts);
                    System.out.println("Points earned: " + points);
                    correct = true;
                    break;
                } else if (guess > number) {
                    System.out.println("Too High");
                } else {
                    System.out.println("Too Low");
                }

                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }

            if (!correct) {
                System.out.println("Out of attempts!");
                System.out.println("The number was: " + number);
            }

            System.out.println("Total Score: " + totalScore);

            System.out.print("Play another round? (yes/no): ");
            sc.nextLine();
            String ch = sc.nextLine().toLowerCase();

            if (!ch.equals("yes")) {
                playAgain = false;
            } else {
                round++;
            }
        }

        System.out.println("=====================================");
        System.out.println("           FINAL SCORE: " + totalScore);
        System.out.println("=====================================");

        sc.close();
    }
}
