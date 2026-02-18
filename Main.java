import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     STUDENT MANAGEMENT SYSTEM        ║");
        System.out.println("╚══════════════════════════════════════╝");

        while (true) {
            printMenu();
            int choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> manager.viewAllStudents();
                case 3 -> searchById();
                case 4 -> searchByName();
                case 5 -> updateStudent();
                case 6 -> deleteStudent();
                case 7 -> manager.sortByGpa();
                case 8 -> manager.showStatistics();
                case 9 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n========= MENU =========");
        System.out.println(" 1. Add Student");
        System.out.println(" 2. View All Students");
        System.out.println(" 3. Search by ID");
        System.out.println(" 4. Search by Name");
        System.out.println(" 5. Update Student");
        System.out.println(" 6. Delete Student");
        System.out.println(" 7. Sort by GPA");
        System.out.println(" 8. Show Statistics");
        System.out.println(" 9. Exit");
        System.out.println("========================");
    }

    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        String name   = getStringInput("Enter Name   : ");
        int age       = getIntInput(   "Enter Age    : ");
        String course = getStringInput("Enter Course : ");
        double gpa    = getDoubleInput("Enter GPA    : ");
        manager.addStudent(name, age, course, gpa);
    }

    private static void searchById() {
        int id = getIntInput("Enter Student ID: ");
        manager.searchById(id);
    }

    private static void searchByName() {
        String name = getStringInput("Enter Name to Search: ");
        manager.searchByName(name);
    }

    private static void updateStudent() {
        System.out.println("\n--- Update Student ---");
        int id        = getIntInput(   "Enter Student ID : ");
        String name   = getStringInput("New Name         : ");
        int age       = getIntInput(   "New Age          : ");
        String course = getStringInput("New Course       : ");
        double gpa    = getDoubleInput("New GPA          : ");
        manager.updateStudent(id, name, age, course, gpa);
    }

    private static void deleteStudent() {
        int id = getIntInput("Enter Student ID to Delete: ");
        manager.deleteStudent(id);
    }

    // ---- Input Helpers ----
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double val = Double.parseDouble(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println(" Please enter a valid decimal number.");
            }
        }
    }

}
