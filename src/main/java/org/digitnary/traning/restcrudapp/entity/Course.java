package org.digitnary.traning.restcrudapp.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a course in the system.
 *
 * @author Ahmad AlMasri
 * @since  19/7/2024
 */
@Entity
@Table(name = "Courses")
public class Course {

    private int id;
    private String title;
    private Instructor instructor;
    private List<Review> reviews;
    private Set<Student> students;

    /**
     * Creates a new course with the specified title.
     *
     * @param title the title of the course
     */
    public Course(String title) {
        this.title = title;
    }

    /**
     * Creates a new course.
     */
    public Course() {
    }

    /**
     * Gets the ID of the course.
     *
     * @return the ID of the course
     */
    @Id
    @GeneratedValue(generator = "CourseIdGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CourseIdGenerator", sequenceName = "Course_Id_Generator", allocationSize = 1)
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the course.
     *
     * @param id the new ID of the course
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the title of the course.
     *
     * @return the title of the course
     */
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the course.
     *
     * @param title the new title of the course
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the instructor of the course.
     *
     * @return the instructor of the course
     */
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, fetch = FetchType.LAZY)
    @JoinColumn(name = "Instructor_id")
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Sets the instructor of the course.
     *
     * @param instructor the new instructor of the course
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * Gets the reviews of the course.
     *
     * @return the reviews of the course
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Sets the reviews of the course.
     *
     * @param reviews the new reviews of the course
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Gets the students enrolled in the course.
     *
     * @return the students enrolled in the course
     */
    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    public Set<Student> getStudents() {
        return students;
    }

    /**
     * Sets the students enrolled in the course.
     *
     * @param students the new students enrolled in the course
     */
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    /**
     * Adds a review to the course.
     *
     * @param review the review to add
     */
    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    /**
     * Adds a student to the course.
     *
     * @param student the student to add
     */
    public void addStudent(Student student) {
        if (students == null) {
            students = new HashSet<>();
        }
        students.add(student);
    }

    /**
     * Returns a string representation of the course.
     *
     * @return a string representation of the course
     */
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
