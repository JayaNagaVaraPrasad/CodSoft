import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Student Grade Calculator!");

        // Prompt the user to enter the number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        // Initialize variables
        double totalNumericScore = 0.0;
        double averageScore;

        // Prompt the user to enter the grades for each subject
        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter the grade for subject " + i + " (A, B, C, D, F): ");
            String grade = scanner.next();

            // Convert letter grade to numeric score
            double numericScore = convertGradeToNumeric(grade);

            // Add the numeric score to the total score
            totalNumericScore += numericScore;
        }

        // Calculate the average score
        averageScore = totalNumericScore / numSubjects;

        // Display the average score
        System.out.printf("The average score of the student is: %.2f\n", averageScore);

        scanner.close();
    }

    // Method to convert letter grade to numeric score
    public static double convertGradeToNumeric(String grade) {
        switch (grade.toUpperCase()) {
            case "A":
                return 90.0;
            case "B":
                return 80.0;
            case "C":
                return 70.0;
            case "D":
                return 60.0;
            case "F":
                return 0.0;
            default:
                System.out.println("Invalid grade entered. Assuming grade as F (0.0).");
                return 0.0;
        }
    }
}
