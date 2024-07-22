package org.digitnary.traning.restcrudapp.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a student entity.
 * @author Ahmad Al-Masri
 * @since 1.0
 */
@Entity
@Table(name = "students")
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Course> courses;

    /**
     * Default constructor for Student.
     */
    public Student() {}

    /**
     * Constructs a new Student with the specified details.
     *
     * @param firstName the first name of the student
     * @param lastName the last name of the student
     * @param email the email of the student
     */
    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Gets the ID of the Student.
     *
     * @return the ID of the Student
     */
    @Id
    @GeneratedValue(generator = "std-id-generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "std-id-generator", sequenceName = "generator_std_id", allocationSize = 1)
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the Student.
     *
     * @param id the new ID of the Student
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the Student.
     *
     * @return the first name of the Student
     */
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the Student.
     *
     * @param firstName the new first name of the Student
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the Student.
     *
     * @return the last name of the Student
     */
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the Student.
     *
     * @param lastName the new last name of the Student
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email of the Student.
     *
     * @return the email of the Student
     */
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the Student.
     *
     * @param email the new email of the Student
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the courses the Student is enrolled in.
     *
     * @return the courses the Student is enrolled in
     */
    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    public Set<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the courses the Student is enrolled in.
     *
     * @param courses the new courses the Student is enrolled in
     */
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    /**
     * Enrolls the Student in a new course.
     *
     * @param course the course to enroll the Student in
     */
    public void enrollCourse(Course course) {
        if (courses == null) {
            courses = new HashSet<>();
        }
        courses.add(course);
    }

    /**
     * Returns a string representation of the Student.
     *
     * @return a string representation of the Student
     */
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
