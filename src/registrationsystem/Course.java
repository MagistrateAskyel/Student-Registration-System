package registrationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * This sets up the Course constructor and class
 * @author Lee_G
 */
public class Course 
{
    private Instructor instructor;
    protected String courseCode, courseName, room, prerequisite;
    private DayOfWeek classDate;
    private LocalTime classTime;
    private int maxStudents;
    private ArrayList<Student> studentList;
    
    /**
     * This is the Course constructor
     * @param instructor
     * @param courseCode
     * @param courseName
     * @param room
     * @param classDate
     * @param classTime 
     * @param maxStudents 
     */
    public Course(Instructor instructor, String courseCode, String courseName, 
            String room, DayOfWeek classDate, LocalTime classTime, int maxStudents)
    {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.room = room;
        this.classDate = classDate;
        setTime(classTime);
        setProf(instructor);
        setClassSize(maxStudents);
        studentList = new ArrayList<>();
    }
    
    /**
     * This is a Course Constructor that requires a prerequisite
     * @param instructor
     * @param courseCode
     * @param courseName
     * @param room
     * @param classDate
     * @param classTime
     * @param maxStudents
     * @param prerequisite 
     */
    public Course(Instructor instructor, String courseCode, String courseName,
            String room, DayOfWeek classDate, LocalTime classTime, int maxStudents, String prerequisite)
    {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.room = room;
        this.classDate = classDate;
        setTime(classTime);
        setProf(instructor);
        setClassSize(maxStudents);
        studentList = new ArrayList<>();
        this.prerequisite = prerequisite;
    }
    
    /**
     * This checks to make sure the class time is at 8 or later, but not later 
     * than 18, if not it throws an illegal argument
     * @param classTime 
     */
    public void setTime(LocalTime classTime)
    {      
        if(classTime.getHour() >= 8 && classTime.getHour() <= 18)
        {
            if(classTime.getHour() == 18 && classTime.getMinute() > 0)
            {
                throw new IllegalArgumentException("Course start time must be between 08:00-18:00");
            }
            else
            this.classTime = classTime;
        }
        else 
        throw new IllegalArgumentException("Course start time must be between 08:00-18:00");
    }
    
    /**
     * This validates if the instructor can teach the course, and 
     * if so adds them as the instructor, if not it throws an illegal argument
     * @param prof 
     */
    public void setProf (Instructor prof)
    {
        if(prof.canTeach(courseCode))
        {
            this.instructor = prof;
        } 
        else
        throw new IllegalArgumentException("Professor " + prof + " is not qualified to teach " + courseCode);
    }
    
    /**
     * Validates if a student can attend a class, by checking if 
     * they are in good standing, and checking class size, if validated, 
     * they join the class list. If the course has a prerequisite, it checks if 
     * they have completed it with a passing grade
     * @param studentApplying 
     */
    public String addStudent (Student studentApplying)
    {
        if(!studentApplying.inGoodStanding())
        {
            return("The Student is not in good standing and cannot join the course.");
        } 
        else if((studentList.size() >= maxStudents))
        {
            return("Student was not added because the course is full");
        } 
        else if((prerequisite != null) && (studentApplying.hasCompleted(prerequisite) != true) )
        {
            return "Student has not completed the prerequisite course: " + prerequisite;
        }
        else 
        {
            studentList.add(studentApplying);
            return("Success");
        }
    }
    
    /**
     * Prints a list of the students in the course
     * @return 
     */
    public String showClassList()
    {
        String classList = "";
        
        for (Student studentList1 : studentList) 
        {
            classList =  classList + studentList1.toString();
        }
        
        return classList;
    }
    
    /**
     * Calculates the average years the students in the course have spent in college
     * @return 
     */
    public double averageStudentTimeAtCollege()
    {
        double totalYearsAtCollege = 0;
        
        for (Student studentList1 : studentList) 
        {
            totalYearsAtCollege = totalYearsAtCollege + studentList1.getYearsEnrolled();
        }
        return totalYearsAtCollege / studentList.size();
    }
    
    /**
     * This method checks to see if the class average age is over 25 or not
     * @return 
     */
    public Boolean matureClass()
    {
        double totalAgeOfStudents = 0;
        
        double averageAge;
        
        for (Student studentList1 : studentList)
        {
            totalAgeOfStudents = totalAgeOfStudents + studentList1.getAge();
        }
        
        averageAge = totalAgeOfStudents / studentList.size();
        
        if (averageAge > 25)
                {
                    return true;
                }
        else return false;
    }
    
    /**
     * Returns the name of the course and it's code
     * @return 
     */
    public String toString()
    {
        return courseCode + "-" + courseName;
    }
    
    /**
     * This validates the name of the instructor, if it's null, returns null, other wise returns instructor name
     * @return 
     */
    public Instructor getInstructor()
    {
        if(instructor == null)
        {
            return null;
        } 
        else
        {
            return instructor;
        }
    }
       
    /**
     * Returns the time of the class
     * @return 
     */
    public String startTime()
    {
        return classTime.format(DateTimeFormatter.ofPattern("HH:mm", Locale.US));
    }
    
    /**
     * This returns the day of the course
     * @return 
     */
    public DayOfWeek getDayOfCourse()
    {
        return classDate;
    }
    
    /**
     * returns the day and time of the class
     * @return 
     */
    public String getCourseDayAndTime()
    {
        return classDate + "'s, starting at " + classTime.format(DateTimeFormatter.ofPattern("HH:mm", Locale.US));
    }
    
    /**
     * This sets the size of the class, and if it's over 40, sets it to 40
     * @param maxStudents 
     */
    public String setClassSize(int maxStudents)
    {
        if (maxStudents > 40)
        {
            this.maxStudents = 40;
            
            return("Max class size = 40, it has been set to 40");
        } 
        else 
        {
            this.maxStudents = maxStudents;
            return("Success");
        }
    }
    
    /**
     * Returns the max Students allowed in the class
     * @return 
     */
    public int getClassSize()
    {
        return maxStudents;
    }
    
    /**
     * Return the room number
     * @return 
     */
    public String getRoom()
    {
        return room;
    }
    
    /**
     * This returns the prerequisite course code required
     * @return 
     */
    public String getPrerequisite()
    {
        return prerequisite;
    }
    
    /**
     * This returns the Course Code
     * @return 
     */
    public String getCourseCode()
    {
        return courseCode;
    }
}
