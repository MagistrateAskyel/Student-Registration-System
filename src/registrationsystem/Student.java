package registrationsystem;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This sets up the Student constructor and class
 * @author Lee_G
 */
public class Student 
{
    private String firstName, lastName, streetAddress, 
                   city, postalCode, courseCode;
    private int studentNumber;
    private LocalDate birthday, enrollDate;
    private Boolean studentStanding;
    private ArrayList<CompletedCourse> completedCoursesList;
    
    /**
     * This is the student constructor
     * @param firstName = Student's first name
     * @param lastName = Student's last name
     * @param streetAddress = student's street address
     * @param city = student's city
     * @param postalCode = students postal code
     * @param course = Course the student is enrolled in
     * @param studentNumber = student's student number
     * @param enrollDate = date the student enrolled
     * @param birthday = student's birthday
     */
    public Student(String firstName, String lastName, String streetAddress, String city, 
            String postalCode, String course, int studentNumber, LocalDate enrollDate, LocalDate birthday)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.postalCode = postalCode;
        this.courseCode = course;
        this.studentNumber = studentNumber;
        setBirthday(birthday);
        this.enrollDate = enrollDate;
        this.studentStanding = true;
        completedCoursesList = new ArrayList<>();
    }

    /**
     * Returns the year the student is born
     * @return 
     */
    public int getYearBorn()
    {
        return birthday.getYear();
    }
    
    /**
     * checks if the student birthday was set properly, if not returns 0, 
     * other wise returns age in years
     * @return 
     */
    public int getAge()
    {
        if (birthday == null)
        {
            return 0;
        }
        
        else
        {
            if (LocalDate.now().getDayOfYear() < birthday.getDayOfYear() )
            {
                return LocalDate.now().getYear() - birthday.getYear() - 1;
            }
            else return LocalDate.now().getYear() - birthday.getYear();
        }  
    }
    
    /**
     * This returns the birthday, if the birthday is null it returns the current day
     * @return 
     */
    public LocalDate getBirthday()
    {
        if (birthday == null)
        {
            return LocalDate.now();
        } else return birthday;  
    }
    
    /**
     * Set the student's birthday, if the student is 100 years old or more,
     * throw an illegal argument exception
     * @param newBirthday 
     */
    public void setBirthday(LocalDate newBirthday)
    {
        if ( (LocalDate.now().getYear() - newBirthday.getYear())  <= 100)
        {
            this.birthday = newBirthday;
        }
        else throw new IllegalArgumentException( newBirthday + " would make " + firstName + " over 100 years old");
    }
    
    /**
     * Get the year that the person was enrolled in school
     * @return 
     */
    public int getYearEnrolled()
    {
        return enrollDate.getYear();
    }
    
    /**
     * Return the status of the student's standing
     * @return 
     */
    public Boolean inGoodStanding()
    {
        return studentStanding;
    }
    
    /**
     * checks if the student enroll date was set properly, if not returns 0, 
     * other wise returns number of years enrolled
     * @return 
     */
    public int getYearsEnrolled()
    {
        if (enrollDate == null)
        {
            return 0;
        }
        
        else
        {
            if (LocalDate.now().getDayOfYear() < enrollDate.getDayOfYear() )
            {
                return LocalDate.now().getYear() - enrollDate.getYear() - 1;
            }
            else return LocalDate.now().getYear() - enrollDate.getYear();
        }  
    }
    
    /**
     * Set the student's standing to false, aka suspended
     */
    public void suspendStudent()
    {
        this.studentStanding = false;
    }
    
    /**
     * Set the student's standing to true, aka reinstated
     */
    public void reinstateStudent()
    {
        this.studentStanding = true;
    }
    
    /**
     * Change the current student's address
     * @param streetName
     * @param cityName
     * @param postalCode 
     */
    public void  changeAddress(String streetName, String cityName, String postalCode)
    {
        this.streetAddress = streetName;
        this.city = cityName;
        this.postalCode = postalCode;
    }
    
    /**
     * Get the student's full address combining streetAddress, city and postalCode
     * @return 
     */
    public String getAddress()
    {
        return streetAddress + " " + city + " " + postalCode;
    }
    
    /**
     * Return the student's first name, last name and student number as a string
     * @return 
     */
    public String toString()
    {
        return firstName + " " + lastName + ", student number: " + studentNumber;
    }
    
    /**
     * Adds a completed course to the student's completedCoursesList
     * @param studentCourse
     * @param finalGrade 
     */
    public void addCompletedCourse(Course studentCourse, int finalGrade)
    {
      CompletedCourse finishedCourse = new CompletedCourse(studentCourse,finalGrade);
      completedCoursesList.add(finishedCourse); 
    }

    /**
     * Prints a list of the courses completed by the student
     * @return 
     */
    public String getCompletedCourses()
    {
        return completedCoursesList.toString();
    }
    
    /**
     * This returns the student's number
     * @return 
     */
    public int getStudentNumber()
    {
        return studentNumber;
    }
    
    /**
     * This method checks to see if the student has completed a course by checking to make sure their grade was 50 or higher
     * @param course
     * @return 
     */
    public Boolean hasCompleted(String course)
    {
        int grade = 0;
        
        for (CompletedCourse completedCoursesList1: completedCoursesList)
        {
            if(completedCoursesList1.getCourse().getCourseCode().equals(course))
            {
                grade = completedCoursesList1.getGrade();
            }
        }
        
        if(grade >= 50)
        {
            return true;
        } else return false;
    }
}
