import java.io.*;
import java.util.*;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.dat";
    private int nextId = 1;

    public StudentManager() {
        loadFromFile();
        // Set next ID based on loaded data
        for (Student s : students) {
            if (s.getId() >= nextId) nextId = s.getId() + 1;
        }
    }

    // ---- ADD ----
    public void addStudent(String name, int age, String course, double gpa) {
        students.add(new Student(nextId++, name, age, course, gpa));
        saveToFile();
        System.out.println("Student added successfully!");
    }

    // ---- VIEW ALL ----
    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        printHeader();
        for (Student s : students) System.out.println(s);
        printFooter();
    }

    // ---- SEARCH BY ID ----
    public void searchById(int id) {
        Student found = findById(id);
        if (found != null) {
            printHeader();
            System.out.println(found);
            printFooter();
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // ---- SEARCH BY NAME ----
    public void searchByName(String name) {
        List<Student> results = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(s);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No students found with name: " + name);
        } else {
            printHeader();
            for (Student s : results) System.out.println(s);
            printFooter();
        }
    }

    // ---- UPDATE ----
    public void updateStudent(int id, String name, int age, String course, double gpa) {
        Student s = findById(id);
        if (s != null) {
            s.setName(name);
            s.setAge(age);
            s.setCourse(course);
            s.setGpa(gpa);
            saveToFile();
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // ---- DELETE ----
    public void deleteStudent(int id) {
        Student s = findById(id);
        if (s != null) {
            students.remove(s);
            saveToFile();
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    // ---- SORT BY GPA ----
    public void sortByGpa() {
        students.sort((a, b) -> Double.compare(b.getGpa(), a.getGpa()));
        System.out.println("Students sorted by GPA (High to Low):");
        viewAllStudents();
    }

    // ---- STATISTICS ----
    public void showStatistics() {
        if (students.isEmpty()) {
            System.out.println("No data to show statistics.");
            return;
        }
        double total = 0, max = students.get(0).getGpa(), min = students.get(0).getGpa();
        for (Student s : students) {
            total += s.getGpa();
            if (s.getGpa() > max) max = s.getGpa();
            if (s.getGpa() < min) min = s.getGpa();
        }
        System.out.println("\n===== Statistics =====");
        System.out.println("  Total Students : " + students.size());
        System.out.printf("  Average GPA    : %.2f%n", total / students.size());
        System.out.printf("  Highest GPA    : %.2f%n", max);
        System.out.printf("  Lowest GPA     : %.2f%n", min);
        System.out.println("========================\n");
    }

    // ---- HELPER: Find by ID ----
    private Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    // ---- FILE: Save ----
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // ---- FILE: Load ----
    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private void printHeader() {
        System.out.println("+------+----------------------+-----+----------------------+------+");
        System.out.println("| ID   | Name                 | Age | Course               | GPA  |");
        System.out.println("+------+----------------------+-----+----------------------+------+");
    }

    private void printFooter() {
        System.out.println("+------+----------------------+-----+----------------------+------+");
    }

}
