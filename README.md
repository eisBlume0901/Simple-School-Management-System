# Easy-to-Use School Management System

[Teacher](#woman_teacher-features-available-for-teacher-role)
[Student](#student-features-available-for-student)

This is my first Java project that gives simple features depending on your chosen role. Choosing the role of
a teacher allows you to manage your students and give them grades. On the other hand, the role of a student allows you
to manage your grade book and calculate your weighted average grade. This project was inspired by what I have learned
from the University of Helsinki's MOOC.fi Java Programming I and II which led to the creation of this project.
Furthermore, I created this project to better understand object-oriented concepts such as Abstraction, Inheritance,
Polymorphism, and Encapsulation and how they make relationships with each other.

## Getting Started
To launch the program, follow these simple steps
1. Go to src folder.
2. Open the `Main.java` file.
3. Make sure that the following code is present
    ```java
    public class Main
    {
        public static void main(String[] args)
        {
            UserInterface ui = new UserInterface();
            ui.start();
        }
    }
    ```
4. Run the `Main.java` to execute the program.

## Functionality
Once the program is running, you can freely explore the following features you can find
depending on which role you chose.

### :woman_teacher: Features available for teacher role

1. Store class list - saves class list either
   - processing a file, or
   - manually inputting the names and student numbers of the student one by one
2. Display class list - displays class list only if
   - the class list is stored, or
   - a new student is added
3. Delete class list - empties the students' information stored in the class list
4. Find a student - finds a student through
   - their last name, or
   - student number
5. Add a new student - adds a new student even if the class list is empty or not
6. Remove an existing student - removes an existing student from a class list
7. Store grades - stores grades either
   - processing a class list with their corresponding grade but only one subject, or
   - manually inputting the grades of each student but can store multiple and varying subjects
8. Display class grade book - displays grade book if you have successfully
   - process the grade book, or
   - manually input grades
9. Delete class grade book - empties the grades stored in the grade book

### :student: Features available for student

1. Store grades - save grades either
   - processing a file, or
   - manually inputting the subject, grade, and unit one by one
2. Display grades - displays the grades only if a list of grades is stored
3. Delete my grade book - empties the student's grade book
4. Compute my general weighted average - calculates the total general weighted average
   - Grade Point Range (1.00 - 5.00 with a step of 0.25)
   - Calculation
      - Variables:
         - GWA: General Weighted Average
         - Grade: Grade obtained in each subject
         - Unit: Unit or weight of each subject
      - Formula:
         - GWA = Summation of (Grade × Unit) / Summation of Unit
         - GWA = ((Grade1 × Unit1) + (Grade2 × Unit2) + ... + (GradeN × UnitN)) / (Unit1 + Unit2 + ... + UnitN)
5. Find grade - finds grades either
   - subject name, or
   - subject unit

## :blue_book: Creating class list and grade book
1. Format Requirements:
   - Despite that, there is a ready-to-made class list and grade book, you can still create your own file. However, there
     is a following format that must be followed so that it can be read and processed. The data is cleaned by separating
     values using the Comma-Separated Values (CSV) format. The following format is used for:
   - Teacher
      - Class List
         - FirstName, LastName, StudentNumber
      - Grade Book (can also be used as a class list)
         - FirstName, LastName, StudentNumber, Grade
   - Student
      - Grade Book
         - SubjectName, Grade, Unit
2. Location:
   - Create the file within the project folder named `Easy-to-Use School Management System`. Ensure that you ***create
     the file inside this project***, or else it will throw a `java.io.FileNotFoundException: File not found. Please try again`
   - Within the project folder named `Easy-to-Use School Management System`, you can see pre-existing files named
      - Student_A_Grades
      - Teacher_A_ClassList
      - Teacher_A_GradeBook
   - You can even check the following files mentioned and follow how they are formatted for your reference.
3. Creating a new file:
   - Within that section, right-click your mouse and select `New` and give your file a descriptive and meaningful name.
   - Then input your data according to the format requirements.  
 
