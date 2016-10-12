package registrationsystem;

/**
 * This sets up the Completed Course class and constructor
 * @author Lee_G
 */
public class CompletedCourse 
{
    private int grade;
    private Course completedCourse;
    
    /**
     * This is the CompletedCourse constructor
     * @param completedCourse
     * @param grade 
     */
    public CompletedCourse(Course completedCourse, int grade)
    {
        this.completedCourse = completedCourse;
        setGrade(grade);
    }
    
    /**
     * This checks if a grade is valid, if it is it sets the grade of the course
     * @param grade 
     */
    public void setGrade(int grade)
    {
        if (grade >= 0 && grade <= 100)
        {
            this.grade = grade;
        }
        else 
        throw new IllegalArgumentException("grade must be 0-100 inclusive");
    }
    
    /**
     * This gets the grade of the completed course and returns it.
     * @return 
     */
    public int getGrade()
    {
        return grade;
    }
    
    /**
     * This gets the completed course
     * @return 
     */
    public Course getCourse()
    {
        return completedCourse;
    }
    
    /**
     * This returns the name of the completed course and it's grade as a string
     * @return 
     */
    public String toString()
    {
        return completedCourse + " grade=" + grade;
    }
}
