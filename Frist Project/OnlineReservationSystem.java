import java.util.*;

class Reservation {
    String passengerName;
    int trainNumber;
    String trainName;
    String classType;
    String dateOfJourney;
    String from;
    String to;
    String pnr;

    Reservation(String passengerName, int trainNumber, String trainName, String classType,
                String dateOfJourney, String from, String to, String pnr) {
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
        this.pnr = pnr;
    }
}

public class OnlineReservationSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static HashMap<Integer, String> trainData = new HashMap<>();

    public static void main(String[] args) {

        trainData.put(101, "Superfast Express");
        trainData.put(102, "Shatabdi Express");
        trainData.put(103, "Rajdhani Express");

        System.out.println("----------- ONLINE RESERVATION SYSTEM -----------");

        if (login()) {
            int choice;
            do {
                System.out.println("\n1. Reservation");
                System.out.println("2. Cancellation");
                System.out.println("3. View All Reservations");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        reservationForm();
                        break;
                    case 2:
                        cancellationForm();
                        break;
                    case 3:
                        viewReservations();
                        break;
                    case 4:
                        System.out.println("Thank you for using the system!");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 4);
        }
    }

    public static boolean login() {
        String validUser = "user";
        String validPass = "1234";

        System.out.print("Enter Login ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if (id.equals(validUser) && pass.equals(validPass)) {
            System.out.println("Login Successful!");
            return true;
        } else {
            System.out.println("Invalid Login Credentials!");
            return false;
        }
    }

    public static void reservationForm() {

        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Train Number: ");
        int trainNo = sc.nextInt();
        sc.nextLine();

        String trainName = trainData.getOrDefault(trainNo, "Train Not Found");

        System.out.println("Train Name: " + trainName);

        System.out.print("Enter Class Type (Sleeper/AC/General): ");
        String classType = sc.nextLine();

        System.out.print("Enter Date of Journey (DD-MM-YYYY): ");
        String doj = sc.nextLine();

        System.out.print("From Station: ");
        String from = sc.nextLine();

        System.out.print("To Station: ");
        String to = sc.nextLine();

        String pnr = "PNR" + (reservations.size() + 1);

        Reservation r = new Reservation(name, trainNo, trainName, classType, doj, from, to, pnr);
        reservations.add(r);

        System.out.println("\nReservation Successful!");
        System.out.println("Your PNR: " + pnr);
    }

    public static void cancellationForm() {
        System.out.print("Enter PNR Number to Cancel: ");
        String pnr = sc.nextLine();

        Reservation found = null;

        for (Reservation r : reservations) {
            if (r.pnr.equals(pnr)) {
                found = r;
                break;
            }
        }

        if (found == null) {
            System.out.println("PNR not found!");
           	return;
        }

        System.out.println("\nPNR Details:");
        System.out.println("Passenger: " + found.passengerName);
        System.out.println("Train: " + found.trainName + " (" + found.trainNumber + ")");
        System.out.println("From: " + found.from);
        System.out.println("To: " + found.to);

        System.out.print("\nConfirm cancellation? (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            reservations.remove(found);
            System.out.println("Ticket Cancelled Successfully!");
        } else {
            System.out.println("Cancellation Aborted.");
        }
    }

    public static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations yet!");
            return;
        }

        for (Reservation r : reservations) {
            System.out.println("\nPNR: " + r.pnr);
            System.out.println("Name: " + r.passengerName);
            System.out.println("Train: " + r.trainName + " (" + r.trainNumber + ")");
            System.out.println("Date: " + r.dateOfJourney);
            System.out.println("Route: " + r.from + " → " + r.to);
        }
    }
}
