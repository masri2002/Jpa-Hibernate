package org.digitnary.traning.restcrudapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents an instructor in the system.
 *
 * @author Ahmad Al-Masri
 * @since 19/7/2024
 */
@Entity
@Table(name = "Instructor")
public class Instructor {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private InstructorDetail detail;
    private Set<Course> courses;

    /**
     * Gets the ID of the instructor.
     *
     * @return the ID of the instructor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InstructorSeq")
    @SequenceGenerator(name = "InstructorSeq", sequenceName = "Instructor_Sequence", allocationSize = 1)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the instructor.
     *
     * @param id the new ID of the instructor
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the instructor.
     *
     * @return the first name of the instructor
     */
    @Column(name = "First_Name")
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the instructor.
     *
     * @param firstName the new first name of the instructor
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the instructor.
     *
     * @return the last name of the instructor
     */
    @Column(name = "Last_Name")
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the instructor.
     *
     * @param lastName the new last name of the instructor
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email of the instructor.
     *
     * @return the email of the instructor
     */
    @Column(name = "Email")
    @Email
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the instructor.
     *
     * @param email the new email of the instructor
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the detail of the instructor.
     *
     * @return the detail of the instructor
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Instructor_Detail_id")
    public InstructorDetail getDetail() {
        return detail;
    }

    /**
     * Sets the detail of the instructor.
     *
     * @param detail the new detail of the instructor
     */
    public void setDetail(InstructorDetail detail) {
        this.detail = detail;
    }

    /**
     * Gets the courses associated with the instructor.
     *
     * @return the courses associated with the instructor
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "instructor",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    public Set<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the courses associated with the instructor.
     *
     * @param courses the new courses associated with the instructor
     */
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    /**
     * Default constructor for the Instructor class.
     */
    public Instructor() {
    }

    /**
     * Creates a new instructor with the specified details.
     *
     * @param firstName the first name of the instructor
     * @param lastName the last name of the instructor
     * @param email the email of the instructor
     * @param detail the detail of the instructor
     */
    public Instructor(String firstName, String lastName, String email, InstructorDetail detail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.detail = detail;
        this.courses = new HashSet<>();
    }

    /**
     * Adds a course to the instructor's course list.
     *
     * @param course the course to add
     */
    public void addCourse(Course course) {
        if (course == null) {
            System.out.println("You can't add this course");
        } else {
            course.setInstructor(this);
            courses.add(course);
        }
    }

    /**
     * Returns a string representation of the instructor.
     *
     * @return a string representation of the instructor
     */
    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", detail=" + detail +
                ", courses=" + courses +
                '}';
    }
}
