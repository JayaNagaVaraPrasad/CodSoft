import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "'}";
    }
}

class Course {
    private String code;
    private String title;

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Course{code='" + code + "', title='" + title + "'}";
    }
}

class RegistrationSystem {
    private List<Student> students;
    private List<Course> courses;
    private Map<String, List<String>> courseRegistrations;

    public RegistrationSystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        courseRegistrations = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void registerStudent(String studentId, String courseCode) {
        courseRegistrations.putIfAbsent(courseCode, new ArrayList<>());
        courseRegistrations.get(courseCode).add(studentId);
    }

    public List<Student> listStudents() {
        return students;
    }

    public List<Course> listCourses() {
        return courses;
    }

    public List<String> listStudentsInCourse(String courseCode) {
        return courseRegistrations.getOrDefault(courseCode, new ArrayList<>());
    }
}

public class Main {
    private static RegistrationSystem registrationSystem = new RegistrationSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Register Student for Course");
            System.out.println("4. List Students");
            System.out.println("5. List Courses");
            System.out.println("6. List Students in Course");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    registerStudent();
                    break;
                case 4:
                    listStudents();
                    break;
                case 5:
                    listCourses();
                    break;
                case 6:
                    listStudentsInCourse();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        registrationSystem.addStudent(new Student(id, name));
        System.out.println("Student added.");
    }

    private static void addCourse() {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        registrationSystem.addCourse(new Course(code, title));
        System.out.println("Course added.");
    }

    private static void registerStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        registrationSystem.registerStudent(studentId, courseCode);
        System.out.println("Student registered for course.");
    }

    private static void listStudents() {
        System.out.println("Students:");
        for (Student student : registrationSystem.listStudents()) {
            System.out.println(student);
        }
    }

    private static void listCourses() {
        System.out.println("Courses:");
        for (Course course : registrationSystem.listCourses()) {
            System.out.println(course);
        }
    }

    private static void listStudentsInCourse() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.println("Students in course " + courseCode + ":");
        for (String studentId : registrationSystem.listStudentsInCourse(courseCode)) {
            System.out.println(studentId);
        }
    }
}
