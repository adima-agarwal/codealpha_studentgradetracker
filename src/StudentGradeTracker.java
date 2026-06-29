public class StudentGradeTracker {

    // Inner class to represent a Student
    static class Student {
        String name;
        ArrayList<Double> grades;

        Student(String name) {
            this.name = name;
            this.grades = new ArrayList<>();
        }

        // Add a grade for this student
        void addGrade(double grade) {
            grades.add(grade);
        }

        // Calculate average of all grades
        double getAverage() {
            if (grades.isEmpty()) return 0;
            double sum = 0;
            for (double g : grades) sum += g;
            return sum / grades.size();
        }

        // Get highest grade
        double getHighest() {
            if (grades.isEmpty()) return 0;
            double max = grades.get(0);
            for (double g : grades) if (g > max) max = g;
            return max;
        }

        // Get lowest grade
        double getLowest() {
            if (grades.isEmpty()) return 0;
            double min = grades.get(0);
            for (double g : grades) if (g < min) min = g;
            return min;
        }

        // Convert average to letter grade
        String getLetterGrade() {
            double avg = getAverage();
            if (avg >= 90) return "A";
            else if (avg >= 80) return "B";
            else if (avg >= 70) return "C";
            else if (avg >= 60) return "D";
            else return "F";
        }
    }

    // Print a separator line
    static void printLine() {
        System.out.println("=".repeat(65));
    }

    // Display the full summary report
    static void displayReport(ArrayList<Student> students) {
        printLine();
        System.out.println("              STUDENT GRADE TRACKER - SUMMARY REPORT");
        printLine();
        System.out.printf("%-20s %-10s %-10s %-10s %-8s%n",
                "Name", "Average", "Highest", "Lowest", "Grade");
        printLine();

        double classTotal = 0;

        for (Student s : students) {
            System.out.printf("%-20s %-10.2f %-10.2f %-10.2f %-8s%n",
                    s.name, s.getAverage(), s.getHighest(), s.getLowest(), s.getLetterGrade());
            classTotal += s.getAverage();
        }

        printLine();
        double classAvg = classTotal / students.size();
        System.out.printf("CLASS AVERAGE: %.2f%n", classAvg);

        // Find top and bottom student
        Student top = students.get(0), bottom = students.get(0);
        for (Student s : students) {
            if (s.getAverage() > top.getAverage()) top = s;
            if (s.getAverage() < bottom.getAverage()) bottom = s;
        }
        System.out.println("TOP STUDENT   : " + top.name + " (" + String.format("%.2f", top.getAverage()) + ")");
        System.out.println("NEEDS SUPPORT : " + bottom.name + " (" + String.format("%.2f", bottom.getAverage()) + ")");
        printLine();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║    STUDENT GRADE TRACKER - CodeAlpha ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            System.out.println("\n1. Add student");
            System.out.println("2. View report");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();
                    Student student = new Student(name);

                    System.out.print("How many subjects? ");
                    int numSubjects = sc.nextInt();

                    for (int i = 1; i <= numSubjects; i++) {
                        System.out.print("Enter grade for subject " + i + " (0-100): ");
                        double grade = sc.nextDouble();
                        while (grade < 0 || grade > 100) {
                            System.out.print("Invalid! Enter grade between 0-100: ");
                            grade = sc.nextDouble();
                        }
                        student.addGrade(grade);
                    }
                    sc.nextLine();
                    students.add(student);
                    System.out.println("✓ Student '" + name + "' added successfully!");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No students added yet!");
                    } else {
                        displayReport(students);
                    }
                    break;

                case 3:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        sc.close();
    }
}
