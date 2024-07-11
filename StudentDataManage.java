import java.util.Scanner;

public class StudentDataManage {
    private static String[] names = new String[10];
    private static int[] grades = new int[10];
    private static int numStudents = 0;

public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;
        do {
            System.out.println("\nSelect what you want:");
            System.out.println("a. Add a student");
            System.out.println("b. Display all students");
            System.out.println("c. Search for a student by name");
            System.out.println("d. Calculate the avg grade");
            System.out.println("e. Sort students by grades in ascending order");
            System.out.println("f. Student with the highest grade");
            System.out.println("g. Student with the lowest grade");
            System.out.println("h. Update the grade of a specific student");
            System.out.println("i. Remove a student from the database");
            System.out.println("j. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.next().charAt(0);

            switch (choice) {
                case 'a':
                    addStudent(scanner);
                    break;
                case 'b':
                    displayStudents();
                    break;
                case 'c':
                    searchStudentByName(scanner);
                    break;
                case 'd':
                    calculateAverageGrade();
                    break;
                case 'e':
                    sortStudentsByGrade();
                    break;
                case 'f':
                    findHighestGradeStudent();
                    break;
                case 'g':
                    findLowestGradeStudent();
                    break;
                case 'h':
                    updateStudentGrade(scanner);
                    break;
                case 'i':
                    removeStudent(scanner);
                    break;
                case 'j':
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 'j');

        scanner.close();
    }
    //add student method
    private static void addStudent(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.next();
        System.out.print("Enter Grade: ");
        int grade = scanner.nextInt();

        if (numStudents == names.length) {           
            names = resizeArray(names);
            grades = resizeArray(grades);
        }

        names[numStudents] = name;
        grades[numStudents] = grade;
        numStudents++;

        System.out.println("Student added.");
    }

    private static void displayStudents() {
        System.out.println("All Student Details:");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Name: " + names[i] + ", Grade: " + grades[i]);
        }
    }

    private static void searchStudentByName(Scanner scanner) {
        System.out.print("Enter Student Name : ");
        String searchName = scanner.next();
        boolean found = false;

        for (int i = 0; i < numStudents; i++) {
            if (names[i].equalsIgnoreCase(searchName)) {
                System.out.println("Student found - Name: " + names[i] + ", Grade: " + grades[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Not exist that student.");
        }
    }

    private static void calculateAverageGrade() {
        if (numStudents == 0) {
            System.out.println("No students.");
            return;
        }

        int total = 0;
        for (int i = 0; i < numStudents; i++) {
            total += grades[i];
        }

        double average = (double) total / numStudents;
        System.out.println("Avg grade: " + average);
    }

    private static void sortStudentsByGrade() {
        for (int i = 0; i < numStudents - 1; i++) {
            for (int j = 0; j < numStudents - 1 - i; j++) {
                if (grades[j] > grades[j + 1]) {
                    // Swap grades
                    int tempGrade = grades[j];
                    grades[j] = grades[j + 1];
                    grades[j + 1] = tempGrade;

                    // Swap names
                    String tempName = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = tempName;
                }
            }
        }

        System.out.println("Sorted Student");
    }

    private static void findHighestGradeStudent() {
        if (numStudents == 0) {
            System.out.println("No students.");
            return;
        }

        int maxGrade = Integer.MIN_VALUE;
        String studentName = "";

        for (int i = 0; i < numStudents; i++) {
            if (grades[i] > maxGrade) {
                maxGrade = grades[i];
                studentName = names[i];
            }
        }

        System.out.println("Student with highest grade: " + studentName + " (Grade: " + maxGrade + ")");
    }

    private static void findLowestGradeStudent() {
        if (numStudents == 0) {
            System.out.println("No students.");
            return;
        }

        int minGrade = Integer.MAX_VALUE;
        String studentName = "";

        for (int i = 0; i < numStudents; i++) {
            if (grades[i] < minGrade) {
                minGrade = grades[i];
                studentName = names[i];
            }
        }

        System.out.println("Student with lowest grade: " + studentName + " (Grade: " + minGrade + ")");
    }

    private static void updateStudentGrade(Scanner scanner) {
        if (numStudents == 0) {
            System.out.println("No students.");
            return;
        }

        System.out.print("Enter the name to update: ");
        String updateName = scanner.next();
        boolean found = false;

        for (int i = 0; i < numStudents; i++) {
            if (names[i].equalsIgnoreCase(updateName)) {
                System.out.print("New grade of" + names[i] + ": ");
                grades[i] = scanner.nextInt();
                found = true;
                System.out.println("Grade updated.");
                break;
            }
        }

        if (!found) {
            System.out.println("Student not exist.");
        }
    }

    private static void removeStudent(Scanner scanner) {
        if (numStudents == 0) {
            System.out.println("No students");
            return;
        }

        System.out.print("Student to remove: ");
        String removeName = scanner.next();
        boolean found = false;

        for (int i = 0; i < numStudents; i++) {
            if (names[i].equalsIgnoreCase(removeName)) {
                // Shift elements to remove student
                for (int j = i; j < numStudents - 1; j++) {
                    names[j] = names[j + 1];
                    grades[j] = grades[j + 1];
                }
                numStudents--;
                found = true;
                System.out.println("Student removed.");
                break;
            }
        }

        if (!found) {
            System.out.println("Student not exist.");
        }
    }

    private static String[] resizeArray(String[] array) {
        String[] newArray = new String[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private static int[] resizeArray(int[] array) {
        int[] newArray = new int[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }
}

}