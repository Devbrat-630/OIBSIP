import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class DigitalLibrary {
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb", "root", "password");
    }

    public static void issueBook(int userId, int bookId) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO IssuedBooks (user_id, book_id, issue_date, return_date) VALUES (?, ?, ?, ?)")) {

            LocalDate today = LocalDate.now();
            LocalDate returnDate = today.plusDays(14);

            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ps.setDate(3, Date.valueOf(today));
            ps.setDate(4, Date.valueOf(returnDate));
            ps.executeUpdate();

            System.out.println("Book issued! Return by: " + returnDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void returnBook(int issueId) {
        try (Connection conn = getConnection();
             PreparedStatement selectPS = conn.prepareStatement("SELECT return_date FROM IssuedBooks WHERE issue_id = ?");
             PreparedStatement updatePS = conn.prepareStatement("UPDATE IssuedBooks SET fine_amount = ? WHERE issue_id = ?")) {

            selectPS.setInt(1, issueId);
            ResultSet rs = selectPS.executeQuery();

            if (rs.next()) {
                LocalDate returnDate = rs.getDate("return_date").toLocalDate();
                LocalDate today = LocalDate.now();
                long daysLate = ChronoUnit.DAYS.between(returnDate, today);
                double fine = daysLate > 0 ? daysLate * 5 : 0;

                updatePS.setDouble(1, fine);
                updatePS.setInt(2, issueId);
                updatePS.executeUpdate();

                System.out.println("Book returned. Fine: " + fine);
            } else {
                System.out.println("Invalid Issue ID!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void generateReport() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT ib.issue_id, u.name, b.title, ib.issue_date, ib.return_date, ib.fine_amount " +
                             "FROM IssuedBooks ib JOIN Users u ON ib.user_id = u.user_id " +
                             "JOIN Books b ON ib.book_id = b.book_id")) {

            System.out.printf("%-10s %-15s %-20s %-12s %-12s %-8s%n",
                    "IssueID", "User", "Book Title", "Issue Date", "Return Date", "Fine");

            while (rs.next()) {
                System.out.printf("%-10d %-15s %-20s %-12s %-12s %-8.2f%n",
                        rs.getInt("issue_id"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getDate("issue_date"),
                        rs.getDate("return_date"),
                        rs.getDouble("fine_amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Issue Book");
            System.out.println("2. Return Book");
            System.out.println("3. Generate Report");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("User ID: ");
                    int userId = sc.nextInt();
                    System.out.print("Book ID: ");
                    int bookId = sc.nextInt();
                    issueBook(userId, bookId);
                }
                case 2 -> {
                    System.out.print("Issue ID: ");
                    int issueId = sc.nextInt();
                    returnBook(issueId);
                }
                case 3 -> generateReport();
                case 4 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
