import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static java.lang.System.*;

// Inheritance and Implementation
public class Student extends Person implements Functionalities
{
    // Encapsulate instance variables
    private Scanner scanner;
    private int studentNumber;
    private List<GradeBook> gradeBook;

    // Parametrized Constructors
    public Student(String firstName, String lastName)
    {
        super(firstName, lastName);
        this.gradeBook = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Constructor Overloading
    public Student(String firstName, String lastName, int studentNumber)
    {
        super(firstName, lastName);
        this.studentNumber = studentNumber;
        this.gradeBook = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Getter
    public int getStudentNumber()
    {
        return this.studentNumber;
    }

    // Methods
    // Returns the size of the grade book (like how many subjects are there)
    public int getGradeBookSize()
    {
        return (int) this.gradeBook
                .stream()
                .distinct()
                .count();
    }

    // Returns the total of the units (like the sum of all subject units)
    public int getGradeBookTotalUnits()
    {
        return this.gradeBook
                .stream()
                .mapToInt(GradeBook -> GradeBook.getUnit())
                .sum();
    }
    // Store grades through file
    @Override
    public void storeData()
    {
        while (true)
        {
            try
            {
                out.println("File name: ");
                File file = new File(scanner.nextLine());
                String fileAbsolutePath = file.getAbsolutePath();

                if (file.exists())
                {
                    String separator = detectSeparator(fileAbsolutePath);

                    if (separator != null)
                    {
                        out.println("\nProcessing... " + fileAbsolutePath);

                        Files.lines(Paths.get(fileAbsolutePath))
                                .map(m -> m.split(separator))
                                .filter(parts -> parts.length > 2)
                                .map(parts -> new GradeBook(parts[0], Float.parseFloat(parts[1]), Integer.parseInt(parts[2])))
                                .distinct()
                                .forEach(this.gradeBook::add);

                        break;
                    }
                    else
                    {
                        throw new NullPointerException("Separator used is not CSV format");
                    }
                }
                else
                {
                    throw new FileNotFoundException("File not found. Please try again!");
                }
            }
            catch (IOException e)
            {
                System.err.println(e);
            }
        }
    }

    // Store grades through user input
    @Override
    public void storeGrades()
    {
        out.println("How many subjects to grade? ");
        int subjectCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < subjectCount; i++)
        {
            if (i == subjectCount)
                break;

            out.println("Subject: ");
            String subject = scanner.nextLine();

            out.println("Grade: ");
            float grade = grade(1.00F, 5.00F, 0.25F);

            out.println("Unit: ");
            int unit = choice(1, 3);

            this.gradeBook.add(new GradeBook(subject, grade, unit));
        }
    }

    // Print or display grades
    public void printGradeBook()
    {
        this.gradeBook
                .stream()
                .map(GradeBook -> GradeBook.toString())
                .distinct()
                .forEach(grades -> out.println(grades));
    }

    // Calculate GWA
    public float computeGWA()
    {
        double totalGWA = this.gradeBook
                .stream()
                .mapToDouble(GradeBook -> GradeBook.getGradeUnitProduct())
                .sum();

        int totalUnits = this.gradeBook
                .stream()
                .mapToInt(GradeBook -> GradeBook.getUnit())
                .sum();

        return (float) (totalGWA / totalUnits);
    }

    // Method overloading
    // Find grades through subject name
    public void findGrade(String subject)
    {
        this.gradeBook
                .stream()
                .filter(GradeBook -> GradeBook.getSubject().contains(subject))
                .map(GradeBook -> GradeBook.toString())
                .forEach(subjects -> out.println(subjects));
    }

    // Find grades through subject unit
    public void findGrade(int unit)
    {
        this.gradeBook
                .stream()
                .filter(GradeBook -> GradeBook.getUnit() == unit)
                .map(GradeBook -> GradeBook.toString())
                .forEach(subjects -> out.println(subjects));
    }

    // Resets the grade book if user wants to input new set of grades
    public void resetGradeBook()
    {
        this.gradeBook.clear();
    }

    @Override
    public String toString()
    {
        return this.getFirstName() + " " + this.getLastName() + " (" + this.studentNumber + ")";
    }


    // Compares the value rather than the reference of an object
    // To prevent duplicates
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (!(obj instanceof Student compared))
            return false;

        return this.getFirstName().equalsIgnoreCase(compared.getFirstName()) &&
                this.getLastName().equalsIgnoreCase(compared.getLastName()) &&
                this.studentNumber == compared.studentNumber;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.getFirstName(), this.getLastName(), this.studentNumber);
    }
}
