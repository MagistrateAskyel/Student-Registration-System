package registrationsystem;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This sets up the Instructor constructor and class
 * @author Lee_G
 */
public class Instructor 
{
    private String firstName, lastName, streetAddress, city, postalCode;
    private int employeeNumber;
    private LocalDate hireDate, birthday;
    private ArrayList<String> courses = new ArrayList<String>();
    
    /**
     * This is the Instructor constructor
     * @param firstName
     * @param lastName
     * @param streetAddress
     * @param city
     * @param postalCode
     * @param employeeNumber
     * @param hireDate
     * @param birthday 
     */
    public Instructor(String firstName, String lastName, int employeeNumber, String streetAddress, String city, 
            String postalCode, LocalDate hireDate, LocalDate birthday)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.postalCode = postalCode;
        this.employeeNumber = employeeNumber;
        setBirthday(birthday);
        setHireDate(hireDate);
        courses = new ArrayList<>();
    }
    
    /**
     * Checks to see if an Instructor can teach a course
     * @param coursesToCheck
     * @return 
     */
    public boolean canTeach(String coursesToCheck)
    {
        return courses.contains(coursesToCheck);
    }
    
    /**
     * Adds a course to the list of courses the Instructor can teach
     * @param courseCode 
     */
    public void addCourseToAbilities(String courseCode)
    {
        if (!courses.contains(courseCode))
        {
            courses.add(courseCode);
        }
    }
    
    /**
     * This checks to see if the hire date is valid, otherwise throws an illegal argument
     * @param newHireDate 
     */
    public void setHireDate(LocalDate newHireDate)
    {
        if ((LocalDate.now().getYear() - newHireDate.getYear()) <= 80)
        {
            this.hireDate = newHireDate;
        }
        else
        {
            throw new IllegalArgumentException( newHireDate + " as a hire date would mean " + firstName + " started working over 80 years ago");
        }
    }
    
    /**
     * Set the instructor's birthday, if the instructor is 100 years old or more,
     * an illegal argument is thrown.
     * @param newBirthday 
     */
    public void setBirthday(LocalDate newBirthday)
    {
        if ( (LocalDate.now().getYear() - newBirthday.getYear())  <= 100)
        {
            this.birthday = newBirthday;
        }
        else throw new IllegalArgumentException( newBirthday + " would make " + firstName + " " + lastName + " over 100 years old");
        
    }
    
     /**
     * Change the current instructor's address
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
     * Get the instructor's full address combining streetAddress, city and postalCode
     * @return 
     */
    public String getAddress()
    {
        return streetAddress + ", " + city + ", " + postalCode;
    }
    
    /**
     * checks if the professors birthday was set properly, if not returns 0, 
     * other wise returns age in years
     * @return 
     */
    public int getAgeInYears()
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
     * This returns the number of years at the college since the instructor was hired
     * @return 
     */
    public int yearsAtCollege()
    {
        if (LocalDate.now().getDayOfYear() < hireDate.getDayOfYear())
        {
            return LocalDate.now().getYear() - hireDate.getYear() - 1; 
        }
        else return LocalDate.now().getYear() - hireDate.getYear();
    }
        
    /**
     * Return the instructor's first name and last name as a string
     * @return 
     */
    public String toString()
    {
        return firstName + " " + lastName;
    }
    
    /**
     * Returns a list of Courses the teacher can teach
     * @return 
     */
    public String listOfSubjectsCertifiedToTeach()
    {
        if(courses.isEmpty())
        {
            return "not qualified to teach courses yet.";
        } 
        else return courses.toString();
    }
}
