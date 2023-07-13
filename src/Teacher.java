import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static java.lang.System.*;

// Inheritance and Implementation
public class Teacher extends Person implements Functionalities
{
    private Scanner scanner;
    private List<Student> myClassList;
    private Map<Student, List<GradeBook>> studentGradeBook;
    public Teacher(String firstName, String lastName)
    {
        super(firstName, lastName);
        this.myClassList = new ArrayList<>();
        this.studentGradeBook = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }


    // Store student's information (name and student number) through a file
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
                                .map(parts -> (new Student(parts[0], parts[1], Integer.parseInt(parts[2]))))
                                .distinct()
                                .forEach(this.myClassList::add);

                        break;
                    }
                    else
                    {
                        throw new NullPointerException("Separator used is not CSV format");
                    }
                }
                else
                {
                    throw new FileNotFoundException("File not found. Please try again");
                }
            }
            catch (IOException e)
            {
                System.err.println(e);
            }
        }
    }

    // Add new student
    public void addStudent(Student student)
    {
        this.myClassList.add(student);
    }

    // Remove student
    public void removeStudent(String lastName)
    {
        this.myClassList
                .removeIf(Student -> Student.getLastName().equalsIgnoreCase(lastName));
    }

    public void removeStudent(int studentNumber)
    {
        this.myClassList
                .removeIf(Student -> Student.getStudentNumber() == studentNumber);
    }

    // Find a particular student
    // Method Overloading
    public void findStudent(String lastName)
    {
        this.myClassList
                .stream()
                .filter(Student -> Student.getLastName().equalsIgnoreCase(lastName))
                .distinct()
                .map(Student -> Student.toString())
                .forEach(out::println);
    }

    public void findStudent(int studentNumber)
    {
        this.myClassList
                .stream()
                .filter(Student -> Student.getStudentNumber() == studentNumber)
                .distinct()
                .map(Student -> Student.toString())
                .forEach(out::println);
    }

    // Resets class list so that new set of class list can be added
    // Can be use if the class list will not be updated
    public void resetMyClassList()
    {
        this.myClassList.clear();
    }


    // Returns the total count of the students existing in the class list
    public int getMyClassListSize()
    {
        return (int) this.myClassList
                .stream()
                .distinct()
                .count();
    }

    public int getMyStudentGradeBookSize()
    {
        return this.studentGradeBook.size();
    }
    // Print class list alphabetically
    public void printClassList()
    {
        this.myClassList
                .stream()
                .map(Student -> Student.getLastName() + " (" + Student.getStudentNumber() + ")")
                .distinct()
                .sorted()
                .forEach(out::println);
    }

    // Store grades through user input
    // Method Overloading
    @Override
    public void storeGrades()
    {
        for (Student current : this.myClassList)
        {
            out.println(current);

            out.println("How many subjects to grade? ");
            int subjectCount = Integer.parseInt(scanner.nextLine());

            List<GradeBook> studentGrades = new ArrayList<>();

            while (subjectCount > 0)
            {
                out.println("Subject: ");
                String subject = scanner.nextLine();

                out.println("Grade: ");
                float grade = grade(1.00F, 5.00F, 0.25F);

                out.println("Unit: ");
                int unit = choice(1, 3);

                studentGrades.add(new GradeBook(subject, grade, unit));
                subjectCount--;
            }
            studentGradeBook.put(current, studentGrades);
        }
    }

    // Store grades through file; however, can only be used for storing one subject grade
    public void storeGrades(String subject, int unit)
    {
       while (true)
        {
            try
            {
                out.println("File name: ");
                File file = new File(scanner.nextLine());

                String fileAbsolutePath = file.getAbsolutePath();

                List<GradeBook> gradeBookList = new ArrayList<>();

                if (file.exists())
                {
                    String separator = detectSeparator(fileAbsolutePath);

                    if (separator != null)
                    {
                        out.println("\nProcessing... " + fileAbsolutePath);
                        Files.lines(Paths.get(fileAbsolutePath))
                                .map(m -> m.split(separator))
                                .filter(parts -> parts.length > 3)
                                .map(parts -> (new GradeBook(subject, Float.parseFloat(parts[3]), unit)))
                                .forEach(gradeBookList::add);

                        if (this.getMyClassListSize() == gradeBookList.size())
                        {
                            for (int i = 0; i < this.getMyClassListSize(); ++i)
                            {
                                List<GradeBook> studentIndividualGrades = new ArrayList<>();
                                studentIndividualGrades.add(gradeBookList.get(i));

                                this.studentGradeBook.put(this.myClassList.get(i), studentIndividualGrades);
                            }
                        }
                        else
                        {
                            out.println("\u001B[31m \n Mismatched data found between class list and grade book. " +
                                    "\n Please check the class list and grade book again");
                        }
                        break;
                    }
                    else
                    {
                        throw new NullPointerException("Separator used is not CSV format");
                    }
                }
                else
                {
                    throw new FileNotFoundException("File not found. Please try again");
                }
            }
            catch (IOException e)
            {
                System.err.println(e);
            }
        }
    }

    // Display Class Grade Book
    public void printStudentGradeBook()
    {
        for (Map.Entry<Student, List<GradeBook>> entry : this.studentGradeBook.entrySet())
        {
            Student key = entry.getKey();
            List<GradeBook> value = entry.getValue();

            out.println(key);

            for (GradeBook gradeBook : value)
                out.println(gradeBook);

            out.println();
        }
    }

    // Resets student grade book so that new set of grades can be added
    // Can be use if the grade book will not be updated
    public void resetMyStudentGradeBook()
    {
        this.studentGradeBook.clear();
    }

}