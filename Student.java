import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int age;
    private String course;
    private double gpa;

    public Student(int id, String name, int age, String course, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.gpa = gpa;
    }

    // Getters
    public int getId()       { return id; }
    public String getName()  { return name; }
    public int getAge()      { return age; }
    public String getCourse(){ return course; }
    public double getGpa()   { return gpa; }

    // Setters
    public void setName(String name)     { this.name = name; }
    public void setAge(int age)          { this.age = age; }
    public void setCourse(String course) { this.course = course; }
    public void setGpa(double gpa)       { this.gpa = gpa; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-20s | %-3d | %-20s | %-4.2f |",
                id, name, age, course, gpa);
    }
}