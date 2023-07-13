import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import static java.lang.System.*;

public interface Functionalities
{
    // Use to store data (e.g., grades, student's information)
    void storeData();
    // Use to store grades
    void storeGrades();
    // Detect if there is a separator used in the file
    // Used for separating the different types of data such as subject, grade, unit or student first name,
    // student last name, student number
    default String detectSeparator(String fileAbsolutePath)
    {
        String separator = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileAbsolutePath)))
        {
            String line = reader.readLine();
            if (line != null)
            {
                String[] separators = {",", ", ", " ,", " , ",
                                       ";", "; ", " ;", " ; ",
                                       ":", ": ", " :", " : "};

                for (String s : separators)
                {
                    String[] fields = line.split(s);
                    if (fields.length > 1)
                    {
                        separator = s;
                    }
                }
            }
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
        return separator;
    }
//    use for input validation to force user to choose a number that within a specified range
    default int choice(int min, int max)
    {
        Scanner scanner = new Scanner(System.in);
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
    // Use to properly set the correct format of grade used
    default float grade(float min, float max, float step)
    {
        Scanner scanner = new Scanner(System.in);
        float grade;
        while (true)
        {
            try
            {
                grade = Float.parseFloat(scanner.nextLine());
                if (grade >= min && grade <= max && (grade - min) % step == 0)
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
                out.println("Please enter a number from " + min + " to " + max + " with a step of " + step);
            }
        }
        return grade;
    }
}
