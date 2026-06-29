
import java.util.ArrayList;
import java.util.Scanner;

public class GradeManagerMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n--- Grade Management Menu ---");
            System.out.println("1. Add Student\n2. View All\n3. Show Stats\n4. Exit");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: "); String n = scanner.nextLine();
                    System.out.print("Grade: "); double g = scanner.nextDouble();
                    students.add(new Student(n, g));
                    break;
                case 2:
                    for (Student s : students) System.out.println(s.getName() + ": " + s.getGrade());
                    break;
                case 3:
                    if (students.isEmpty()) System.out.println("No data.");
                    else {
                        double sum = 0, h = students.get(0).getGrade(), l = students.get(0).getGrade();
                        for (Student s : students) {
                            sum += s.getGrade();
                            if (s.getGrade() > h) h = s.getGrade();
                            if (s.getGrade() < l) l = s.getGrade();
                        }
                        System.out.printf("Avg: %.2f | High: %.2f | Low: %.2f\n", (sum/students.size()), h, l);
                    }
                    break;
            }
        } while (choice != 4);
        scanner.close();
    }
}