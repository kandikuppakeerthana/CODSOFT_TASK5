import java.io.*;
import java.util.*;

class Student implements Serializable {
    String name;
    int rollNumber;
    String grade;
    String department;

    Student(String name, int rollNumber, String grade, String department) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.department = department;
    }

    public String toString() {
        return "\n----------------------------" +
               "\nRoll Number : " + rollNumber +
               "\nName        : " + name +
               "\nGrade       : " + grade +
               "\nDepartment  : " + department +
               "\n----------------------------";
    }
}

public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static final String FILE_NAME = "students.dat";

    // Add Student
    static void addStudent(Student s) {
        students.add(s);
        System.out.println("Student Added Successfully!");
    }

    // Remove Student
    static void removeStudent(int roll) {
        Student s = searchStudent(roll);

        if (s != null) {
            students.remove(s);
            System.out.println("Student Removed Successfully!");
        } else {
            System.out.println("Student Not Found!");
        }
    }

    // Search Student
    static Student searchStudent(int roll) {
        for (Student s : students) {
            if (s.rollNumber == roll)
                return s;
        }
        return null;
    }

    // Display Students
    static void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No Student Records Available.");
            return;
        }

        for (Student s : students)
            System.out.println(s);
    }

    // Edit Student
    static void editStudent(int roll, Scanner sc) {

        Student s = searchStudent(roll);

        if (s == null) {
            System.out.println("Student Not Found!");
            return;
        }

        System.out.print("Enter New Name: ");
        s.name = sc.nextLine();

        System.out.print("Enter New Grade: ");
        s.grade = sc.nextLine();

        System.out.print("Enter New Department: ");
        s.department = sc.nextLine();

        System.out.println("Student Updated Successfully!");
    }

    // Save Data
    static void saveData() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(students);
            out.close();
            System.out.println("Data Saved Successfully!");
        } catch (IOException e) {
            System.out.println("Error Saving File.");
        }
    }

    // Load Data
    static void loadData() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            students = (ArrayList<Student>) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println("No Previous Data Found.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        loadData();

        int choice;

        do {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Save Data");
            System.out.println("7. Exit");
            System.out.print("Enter Your Choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a valid number: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    String name;

                    do {
                        System.out.print("Enter Name: ");
                        name = sc.nextLine();
                    } while (name.trim().isEmpty());

                    int roll;

                    System.out.print("Enter Roll Number: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Enter Valid Roll Number: ");
                        sc.next();
                    }
                    roll = sc.nextInt();
                    sc.nextLine();

                    String grade;

                    do {
                        System.out.print("Enter Grade: ");
                        grade = sc.nextLine();
                    } while (grade.trim().isEmpty());

                    String dept;

                    do {
                        System.out.print("Enter Department: ");
                        dept = sc.nextLine();
                    } while (dept.trim().isEmpty());

                    addStudent(new Student(name, roll, grade, dept));

                    break;

                case 2:

                    System.out.print("Enter Roll Number: ");
                    int removeRoll = sc.nextInt();
                    sc.nextLine();

                    removeStudent(removeRoll);

                    break;

                case 3:

                    System.out.print("Enter Roll Number: ");
                    int searchRoll = sc.nextInt();
                    sc.nextLine();

                    Student s = searchStudent(searchRoll);

                    if (s != null)
                        System.out.println(s);
                    else
                        System.out.println("Student Not Found!");

                    break;

                case 4:

                    displayStudents();

                    break;

                case 5:

                    System.out.print("Enter Roll Number: ");
                    int editRoll = sc.nextInt();
                    sc.nextLine();

                    editStudent(editRoll, sc);

                    break;

                case 6:

                    saveData();

                    break;

                case 7:

                    saveData();
                    System.out.println("Thank You!");
                    break;

                default:

                    System.out.println("Invalid Choice!");

            }

        } while (choice != 7);

        sc.close();
    }
}
