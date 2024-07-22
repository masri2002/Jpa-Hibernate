package org.digitnary.traning.restcrudapp.dao;

import org.digitnary.traning.restcrudapp.entity.Course;
import org.digitnary.traning.restcrudapp.entity.Instructor;
import org.digitnary.traning.restcrudapp.entity.InstructorDetail;
import org.digitnary.traning.restcrudapp.entity.Student;

import java.util.List;

/**
 * Data Access Object (DAO) interface for Instructor entity.
 * @author Ahmad Al-Masri
 * @since Last Updated in 22/7/2024
 */
public interface InstructorDao {

    /**
     * Creates a new Instructor.
     *
     * @param instructor the instructor to create
     */
    void create(Instructor instructor);

    /**
     * Updates an existing Instructor.
     *
     * @param instructor the instructor to update
     */
    void update(Instructor instructor);

    /**
     * Deletes an Instructor by ID.
     *
     * @param id the ID of the instructor to delete
     */
    void delete(int id);

    /**
     * Finds an Instructor by ID.
     *
     * @param id the ID of the instructor to find
     * @return the found instructor, or null if no instructor found
     */
    Instructor findById(int id);

    /**
     * Finds all Instructors.
     *
     * @return a list of all instructors
     */
    List<Instructor> findAll();

    /**
     * Finds an Instructor by their name.
     *
     * @param firstName the first name of the instructor
     * @param lastName the last name of the instructor
     * @return the found instructor, or null if no instructor found
     */
    Instructor findByName(String firstName, String lastName);

    /**
     * Finds an Instructor by their email.
     *
     * @param email the email of the instructor
     * @return the found instructor, or null if no instructor found
     */
    Instructor findByEmail(String email);

    /**
     * Finds an Instructor by their YouTube channel.
     *
     * @param youtubeChannel the YouTube channel of the instructor
     * @return the found instructor, or null if no instructor found
     */
    Instructor findByYoutubeChannel(String youtubeChannel);

    /**
     * Finds an InstructorDetail by ID.
     *
     * @param id the ID of the instructor detail to find
     * @return the found instructor detail, or null if no detail found
     */
    InstructorDetail findDetailById(int id);

    /**
     * Deletes an InstructorDetail by ID.
     *
     * @param id the ID of the instructor detail to delete
     * @return the deleted instructor detail, or null if no detail found
     */
    InstructorDetail deleteDetailById(int id);

    /**
     * Finds Courses by an Instructor.
     *
     * @param instructor the instructor whose courses to find
     * @return a list of courses taught by the instructor
     */
    List<Course> findByInstructor(Instructor instructor);

    /**
     * Finds a Course by ID.
     *
     * @param id the ID of the course to find
     * @return the found course, or null if no course found
     */
    Course findCourseById(int id);

    /**
     * Finds a Course and its Instructor by Course ID.
     *
     * @param id the ID of the course to find
     * @return the found course with its instructor, or null if no course found
     */
    Course findCourseAndInstructor(int id);

    /**
     * Finds an Instructor by ID and fetches their courses.
     *
     * @param id the ID of the instructor to find
     * @return the found instructor with their courses, or null if no instructor found
     */
    Instructor findInstructorByJoinFetch(int id);

    /**
     * Updates an existing Course.
     *
     * @param course the course to update
     */
    void updateCourse(Course course);

    /**
     * Deletes a Course by ID.
     *
     * @param id the ID of the course to delete
     */
    void deleteCourse(int id);

    /**
     * Saves a new Course.
     *
     * @param course the course to save
     */
    void saveCourse(Course course);

    /**
     * Finds a Course and its Reviews by Course ID.
     *
     * @param id the ID of the course to find
     * @return the found course with its reviews, or null if no course found
     */
    Course findCourseAndReviewsById(int id);

    /**
     * Saves a new Student.
     *
     * @param student the student to save
     */
    void saveStudent(Student student);

    /**
     * Finds a Course with its Students by Course ID.
     *
     * @param id the ID of the course to find
     * @return the found course with its students, or null if no course found
     */
    Course findCourseWithItsStudentById(int id);

    /**
     * Finds a Student and their Courses by Student ID.
     *
     * @param id the ID of the student to find
     * @return the found student with their courses, or null if no student found
     */
    Student findStudentsAndHisCoursesById(int id);

    /**
     * Updates an existing Student.
     *
     * @param student the student to update
     */
    void updateStudent(Student student);

    /**
     * Deletes a Student by ID.
     *
     * @param id the ID of the student to delete
     */
    void deleteStudentById(int id);
}
