import java.util.Objects;
public class GradeBook
{
    private String subject;
    private float grade;
    private int unit;

    public GradeBook(String subject, float grade, int unit)
    {
        this.subject = subject;
        this.grade = grade;
        this.unit = unit;
    }

    // Getters
    public String getSubject()
    {
        return this.subject;
    }

    public float getGrade()
    {
        return this.grade;
    }

    public int getUnit()
    {
        return this.unit;
    }

    // Methods
    public float getGradeUnitProduct()
    {
        return this.grade * this.unit;
    }

    @Override
    public String toString()
    {
        return this.subject + ": " + this.grade + " (" + this.unit + " units)";
    }

    // Compares the value rather than the reference of an object
    // To prevent duplicates
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (!(obj instanceof GradeBook compared))
            return false;

        return this.subject.equalsIgnoreCase(compared.subject) &&
                this.grade == compared.grade &&
                this.unit == compared.unit;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.subject, this.grade, this.unit);
    }
}
