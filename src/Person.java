public abstract class Person
{
    public enum Sex
    {
        MALE, FEMALE;
    }

    // Encapsulate instance variables
    private String firstName;
    private String lastName;
    private Sex sex;

    // Parametrized Constructor
    public Person(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters
    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public Sex getSex()
    {
        return this.sex;
    }

    // Setter
    public void setSex(Sex sex)
    {
        this.sex = sex;
    }

    @Override
    public String toString()
    {
        return this.firstName + " " + this.lastName;
    }
}
