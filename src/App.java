import java.util.ArrayList;
import java.util.Scanner;

public class App {
    
    static ArrayList<Student> studentList = new ArrayList<Student>();
    
    public static void main(String[] args) throws Exception {
        
        Scanner input = new Scanner(System.in);
        
        while (true) {
            System.out.println("Enter your choice:\n1. Add Student\n2. Update Student\n3. Delete Student\n4. Fetch Student\n5. Check if student is present\n6. Exit");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    fetchStudent();
                    break;
                case 5:
                    isPresent();
                    break;
                case 6:
                    listStudent();
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }
    
    public static void addStudent() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Student ID: ");
        int id = input.nextInt();
        System.out.println("Enter Student Name: ");
        String name = input.next();
        System.out.println("Enter Student Age: ");
        int age = input.nextInt();
        studentList.add(new Student(id, name, age));
        System.out.println("Student added successfully.");
    }

    public static void listStudent() {
        for (Student student : studentList) {
            System.out.println("Student ID " + student.id + " age." + student.age + "name:"+student.name);
            return;
        }
    }
    
    public static void updateStudent() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Student ID to update: ");
        int id = input.nextInt();
        for (Student student : studentList) {
            if (student.getId() == id) {
                System.out.println("Enter new Name: ");
                String name = input.next();
                System.out.println("Enter new Age: ");
                int age = input.nextInt();
                student.setName(name);
                student.setAge(age);
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }
    
    public static void deleteStudent() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Student ID to delete: ");
        int id = input.nextInt();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == id) {
                studentList.remove(i);
                System.out.println("Student deleted successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }
    
    public static void fetchStudent() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Student ID to fetch: ");
        int id = input.nextInt();
        for (Student student : studentList) {
            if (student.getId() == id) {
                System.out.println("Student ID: " + student.getId() + " | Name: " + student.getName() + " | Age: " + student.getAge());
                return;
            }
        }
        System.out.println("Student not found.");
    }
    
    public static void isPresent() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Student ID to check if present: ");
        int id = input.nextInt();
        for (Student student : studentList) {
            if (student.getId() == id) {
                System.out.println("Student with ID " + id + " is present.");
                return;
            }
        }
        System.out.println("Student with ID " + id + " is not present.");
    }

    static class Student {
        private int id;
        private String name;
        private int age;
        
        public Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
        
        public int getId() {
            return id;
        }
        
        public void setId(int id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public int getAge() {
            return age;
        }
        
        public void setAge(int age) {
            this.age = age;
        }
    }
}    
