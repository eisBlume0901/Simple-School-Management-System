import static java.lang.System.*;
import java.util.*;
public class UserInterface
{
    private Scanner scanner;
    private Teacher teacher;
    private Student student;

    public UserInterface()
    {
        this.scanner = new Scanner(System.in);
    }

    // Method Overloading
    public void start()
    {
        out.println("""
                \u001B[1;35m\tRole
                [1] Teacher\s
                [2] Student\s
                [3] Exit
                """);
        int choice = choice(1, 3);

        if (choice == 1)
        {
            runProgramAsTeacher();
        }
        if (choice == 2)
        {
            runProgramAsStudent();
        }
        if (choice == 3)
        {
            out.println("\u001B[31m\t Exiting the program...");
        }
    }
    public int choice(int min, int max)
    {
        int choice;
        while (true)
        {
            try
            {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max)
                {
                    break;
                }
                else
                {
                    throw new Exception();
                }
            }
            catch (Exception e)
            {
                out.println("Please enter a number from " + min + " to " + max + " only!");
            }
        }
        return choice;
    }


    public void teacherChoices()
    {
        out.println("""
                    \u001B[1;34m
                    Please enter a number to continue
                    [1] Store class list\s
                    [2] Display class list\s
                    [3] Delete class list\s
                    [4] Find a student\s
                    [5] Add a new student\s
                    [6] Remove an existing student\s
                    [7] Store grades\s
                    [8] Display class grade book\s
                    [9] Delete class grade book\s
                    """);
    }

    public void storeTeacherInformation()
    {
        out.println("\u001B[1;34m\t Logging in as a Teacher...");
        out.println("First name: ");
        String firstName = scanner.nextLine();
        out.println("Last name: ");
        String lastName = scanner.nextLine();
        out.println("Sex \n[1] Male \n[2] Female ");
        int sex = choice(1, 2);

        teacher = new Teacher(firstName, lastName);

        String honorific = "";
        if (sex == 1)
        {
            teacher.setSex(Person.Sex.MALE);
            honorific = "Mr. ";
        }
        if (sex == 2)
        {
            teacher.setSex(Person.Sex.FEMALE);
            honorific = "Ms. ";
        }

        out.println("\n\t Welcome, " + honorific + teacher.getLastName());
    }

    public void teacherChoice1StoreClassList()
    {
        out.println("""
                    Store class list
                    [1] From a file\s
                    [2] Through manual input
                    """);

        int classListChoice = choice(1, 2);

        if (classListChoice == 1)
        {
            teacher.storeData();
        }
        if (classListChoice == 2)
        {
            teacher.storeData();
        }
    }

    public void teacherChoice2DisplayClassList()
    {
        out.println("\t" + teacher.getLastName() + "'s Class List");
        teacher.printClassList();
        out.println("Total students: " + teacher.getMyClassListSize());
    }

    public void teacherChoice3DeleteClassList()
    {
        out.println("\u001B[31mDeleting class list...");
        teacher.resetMyClassList();
        out.println("Total students: " + teacher.getMyClassListSize());
    }

    public void teacherChoice4FindAStudent()
    {
        if (teacher.getMyClassListSize() == 0)
        {
            out.println("\u001B[31m Class list is empty! Please store the class list");
        }
        else
        {
            out.println("""
                            Find a student
                            [1] Through last name\s
                            [2] Through student number
                            """);
            int findChoice = choice(1, 2);

            if (findChoice == 1)
            {
                out.println("Student's last name: ");
                String studentLastName = scanner.nextLine();

                teacher.findStudent(studentLastName);
            }

            if (findChoice == 2)
            {
                out.println("Student number: ");
                int studentNumber = Integer.parseInt(scanner.nextLine());

                teacher.findStudent(studentNumber);
            }
        }
    }

    public void teacherChoice5AddANewStudent()
    {
        out.println("Add a new student");
        out.println("First name: ");
        String sFirstName = scanner.nextLine();
        out.println("Last name: ");
        String sLastName = scanner.nextLine();
        out.println("Student number: ");
        int studentNumber = Integer.parseInt(scanner.nextLine());

        teacher.addStudent(new Student(sFirstName, sLastName, studentNumber));
    }

    public void teacherChoice6RemoveAnExistingStudent()
    {
        if (teacher.getMyClassListSize() == 0)
        {
            out.println("\u001B[31m Class list is empty! Please store the class list first");
        }
        else
        {
            out.println("Remove an existing student");
            out.println("""
                        [1] Using student's last name\s
                        [2] Using student number
                        """);

            int removeChoice = choice(1, 2);

            if (removeChoice == 1)
            {
                out.println("Last name: ");
                String sLastName = scanner.nextLine();
                out.println("\u001B[31mRemoving ...");
                teacher.findStudent(sLastName);
                teacher.removeStudent(sLastName);
            }

            if (removeChoice == 2)
            {
                out.println("Student number: ");
                int studentNumber = Integer.parseInt(scanner.nextLine());
                out.println("\u001B[31mRemoving ...");
                teacher.findStudent(studentNumber);
                teacher.removeStudent(studentNumber);
            }
        }
    }

    public void teacherChoice7StoreGrades()
    {
        if (teacher.getMyClassListSize() == 0)
        {
            out.println("\u001B[31m Class list is empty! Please store class list");
        }
        else
        {
            out.println("""
                Store grades
                [1] Through file (Can store one subject only)
                [2] Through manual input (Can store multiple subjects)
                """);

            int storeChoice = choice(1, 2);

            if (storeChoice == 1)
            {
                out.println("Subject: ");
                String subject = scanner.nextLine();
                out.println("Unit: ");
                int unit = choice(1, 3);
                teacher.storeGrades(subject, unit);
            }
            else if (storeChoice == 2)
            {
                teacher.storeGrades();
            }
        }
    }

    public void teacherChoice8DisplayClassGradeBook()
    {
        if (teacher.getMyStudentGradeBookSize() == 0)
        {
            out.println("\u001B[31m Grade book is empty! Please store grades");
        }
        else
        {
            out.println("Class Name: ");
            String className = scanner.nextLine();
            out.println("\n\t" + className + " Grades");
            teacher.printStudentGradeBook();
        }
    }

    public void teacherChoice9ResetClassGradeBook()
    {
        out.println("\u001B[31mDeleting class grade book...");
        teacher.resetMyStudentGradeBook();
    }

    public void runProgramAsTeacher()
    {
        storeTeacherInformation();
        while (true)
        {
            teacherChoices();
            int choice = choice(1, 9);

            if (choice == 1)
                teacherChoice1StoreClassList();
            else if (choice == 2)
                teacherChoice2DisplayClassList();
            else if (choice == 3)
                teacherChoice3DeleteClassList();
            else if (choice == 4)
                teacherChoice4FindAStudent();
            else if (choice == 5)
                teacherChoice5AddANewStudent();
            else if (choice == 6)
                teacherChoice6RemoveAnExistingStudent();
            else if (choice == 7)
                teacherChoice7StoreGrades();
            else if (choice == 8)
                teacherChoice8DisplayClassGradeBook();
            else if (choice == 9)
                teacherChoice9ResetClassGradeBook();

            out.println("\u001B[1;34m \nExit the program");
            out.println("""
                    [1] Yes\s
                    [2] No
                    """);
            int useAgain = choice(1, 2);

            if (useAgain == 1)
            {
                out.println("\u001B[31m\t" + teacher.getLastName() + " is logging off...");
                break;
            }
        }
    }


    // Student
    public void runProgramAsStudent()
    {
        storeStudentInformation();
        while (true)
        {
            studentChoices();
            int choice = choice(1, 5);

            if (choice == 1)
                studentChoice1StoreGrades();
            else if (choice == 2)
                studentChoice2DisplayGrades();
            else if (choice == 3)
                studentChoice3ResetGradeBook();
            else if (choice == 4)
                studentChoice4ComputeGWA();
            else if (choice == 5)
                studentChoice5FindGrade();

            out.println("\u001B[1;36m \nExit the program");
            out.println("""
                    [1] Yes\s
                    [2] No
                    """);
            int useAgain = choice(1, 2);

            if (useAgain == 1)
            {
                out.println("\u001B[31m\t" + student.getLastName() + " is logging off...");
                break;
            }
        }

    }

    public void storeStudentInformation()
    {
        out.println("\u001B[1;36m\t Logging in as a Student...");
        out.println("First name: ");
        String firstName = scanner.nextLine();
        out.println("Last name: ");
        String lastName = scanner.nextLine();
        out.println("Sex \n[1] Male \n[2] Female ");
        int sex = choice(1, 2);

        student = new Student(firstName, lastName);

        String honorific = "";
        if (sex == 1)
        {
            student.setSex(Person.Sex.MALE);
            honorific = "Mr. ";
        }
        if (sex == 2)
        {
            student.setSex(Person.Sex.FEMALE);
            honorific = "Ms. ";
        }

        out.println("\n\t Welcome, " + honorific + student.getLastName());
    }
    public void studentChoices()
    {
        out.println("""
                \u001B[1;64m
                Please enter a number to continue
                [1] Store grades\s
                [2] Display grades\s
                [3] Delete my grade book\s
                [4] Compute my general weighted average\s
                [5] Find grade
                """);
    }

    public void studentChoice1StoreGrades()
    {
        out.println("""
                Store grades
                [1] From a file\s
                [2] Through manual input
                """);

        int storeChoice = choice(1, 2);

        if (storeChoice == 1)
            student.storeData();
        else if (storeChoice == 2)
            student.storeGrades();
    }

    public void studentChoice2DisplayGrades()
    {
        if (student.getGradeBookSize() == 0)
        {
            out.println("\u001B[31m Grade book is empty! Please store the grades");
        }
        else
        {
            out.println(student.getLastName() + "'s Grades \n");
            student.printGradeBook();
            out.println("\nTotal subjects: " + student.getGradeBookSize());
            out.println("Total units: " + student.getGradeBookTotalUnits());

        }
    }
    public void studentChoice3ResetGradeBook()
    {
        out.println("\u001B[31mDeleting my grade book...");
        student.resetGradeBook();
    }

    public void studentChoice4ComputeGWA()
    {
        if (student.getGradeBookSize() == 0)
        {
            out.println("\u001B[31m Grade book is empty! Please store grades");
        }
        else
        {
            out.println("Calculated GWA: " + student.computeGWA());
        }
    }

    public void studentChoice5FindGrade()
    {
        if (student.getGradeBookSize() == 0)
        {
            out.println("\u001B[31m Grade book is empty! Please store grades");
        }
        else
        {
            out.println("""
                    Find grade
                    [1] Through subject name\s
                    [2] Through subject unit
                    """);

            int findChoice = choice(1, 2);

            if (findChoice == 1)
            {
                out.println("Subject name: ");
                String subject = scanner.nextLine();

                student.findGrade(subject);
            }
            else if (findChoice == 2)
            {
                out.println("Subject unit: ");
                int unit = choice(1, 3);

                student.findGrade(unit);
            }
        }
    }

}
