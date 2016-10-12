package registrationsystem;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * This sets up the Course with lab constructor and class
 * @author Lee_G
 */
public class CourseWithLab extends Course
{
    private Instructor labTech;
    private String labCode, labRoom, prerequisite;
    private DayOfWeek labDate;
    private LocalTime labTime;
    
    /**
     * This is the Course with Lab constructor
     * @param instructor
     * @param courseCode
     * @param courseName
     * @param room
     * @param classDate
     * @param classTime
     * @param maxStudents
     * @param labTech
     * @param labRoom
     * @param labDate
     * @param labTime 
     */
    public CourseWithLab(Instructor instructor, String courseCode, String courseName, String room, DayOfWeek classDate, LocalTime classTime, int maxStudents, Instructor labTech, String labRoom,
            DayOfWeek labDate, LocalTime labTime)
    {
        super(instructor, courseCode, courseName, room, classDate, classTime, maxStudents);
        this.labCode = super.courseCode + "-LAB";
        this.labDate = labDate;
        this.labRoom = labRoom;
        setLabTech(labTech);
        setLabTime(labTime);
        
    }
    
    /**
     * This is the Course with lab constructor that requires a prerequisite
     * @param instructor
     * @param courseCode
     * @param courseName
     * @param room
     * @param classDate
     * @param classTime
     * @param maxStudents
     * @param prerequisite
     * @param labTech
     * @param labRoom
     * @param labDate
     * @param labTime 
     */
    public CourseWithLab(Instructor instructor, String courseCode, String courseName, String room, DayOfWeek classDate, LocalTime classTime, 
            int maxStudents, String prerequisite, Instructor labTech, String labRoom,
            DayOfWeek labDate, LocalTime labTime)
    {
        super(instructor, courseCode, courseName, room, classDate, classTime, maxStudents);
        this.labCode = super.courseCode + "-LAB";
        this.labDate = labDate;
        this.labRoom = labRoom;
        setLabTech(labTech);
        setLabTime(labTime);
        this.prerequisite = prerequisite;
    }
    
     /**
     * returns the day and time of the lab
     * @return 
     */
    public String getLabClassAndTime()
    {
        return "room: " + labRoom +", "+ labDate + " starting at " + labTime.format(DateTimeFormatter.ofPattern("HH:mm", Locale.US));
    }
    
    /**
     * This sets the time of the lab, making sure that it's between 8 and 18, 
     * if not it throws an illegal argument
     * @param labTime 
     */
    public void setLabTime(LocalTime labTime)
    {      
        if(labTime.getHour() >= 8 && labTime.getHour() <= 18)
        {
            if(labTime.getHour() == 18 && labTime.getMinute() > 0)
            {
                throw new IllegalArgumentException("The lab start time must be between 08:00-18:00");
            }
            else
            this.labTime = labTime;
        }
        else 
        throw new IllegalArgumentException("The lab start time must be between 08:00-18:00");
    }
    
    /**
     * This checks if the lab tech has the qualifications to host the lab, 
     * and if so sets them as the lab tech of the course, if not it throws an illegal argument
     * @param labTech 
     */
    public void setLabTech (Instructor labTech)
    {
        if(labTech.canTeach(labCode))
        {
            this.labTech = labTech;
        } 
        else
        throw new IllegalArgumentException("The Lab Tech is not qualified to host the lab");
    }
    
    /**
     * returns the course code and course name of the lab as a string
     * @return 
     */
    public String toString()
    {
        return courseCode + "-" + courseName + " with lab";
    }
    
     /**
     * This validates the name of the lab tech, if it's null, returns null, 
     * other wise returns lab tech's name
     * @return 
     */
    public Instructor getLabTech()
    {
        if(labTech == null)
        {
            return null;
        } 
        else
        {
            return labTech;
        }
    }
    
    /**
     * This returns the prerequisite course code
     * @return 
     */
    public String getPrerequisite()
    {
        return prerequisite;
    }
}
