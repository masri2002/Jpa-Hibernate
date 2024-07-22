package org.digitnary.traning.restcrudapp;

import org.digitnary.traning.restcrudapp.dao.InstructorDao;
import org.digitnary.traning.restcrudapp.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * The main application class for the Spring Boot application that uses JPA and Hibernate.
 * This class is responsible for initializing and running the application, and it contains
 * examples of CRUD operations using the {@link InstructorDao} data access object.
 */
@SpringBootApplication
public class JpaHibernateApplication {

    private final Logger logger = LoggerFactory.getLogger(JpaHibernateApplication.class);
    private InstructorDao instructorDao;

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateApplication.class, args);
    }

    /**
     * Constructor to initialize the JpaHibernateApplication with the {@link InstructorDao}.
     *
     * @param instructorDao the {@link InstructorDao} to be injected
     */
    @Autowired
    public JpaHibernateApplication(InstructorDao instructorDao) {
        this.instructorDao = instructorDao;
    }

    /**
     * Bean definition for {@link CommandLineRunner} to execute CRUD operations on application startup.
     *
     * @param dao the {@link InstructorDao} used for data access operations
     * @return a {@link CommandLineRunner} instance
     */
    @Bean
    public CommandLineRunner commandLineRunner(InstructorDao dao) {
        return runner -> {
            // Uncomment the following lines to perform CRUD operations
            // Instructor instructor1 = new Instructor("Ali1","Saleem","aliSaleem@gmail.com",new InstructorDetail("www.youtube.com/AliSaleem","Gutter"));
            // Course course1 = new Course("java");
            // Course course2 = new Course("C++");
            // Course course3 = new Course("Data structures ");
            // instructor1.addCourse(course1);
            // instructor1.addCourse(course2);
            // instructor1.addCourse(course3);
            // create(instructor1);
            // logger.warn("Found{}", getById(dao).getCourses());
            // logger.info("Founded{}", getById(dao));
            // logger.warn("Course{}", " " + dao.findCourseAndInstructor(2));
            // logger.warn("Founded{}", dao.findInstructorByJoinFetch(1));
            // updateInstructor(dao);
            // updateCourse(dao);
            // delete(1);
            // deleteCourse(dao);
            // createNewCourse(dao);
            // Course course = dao.findCourseAndReviewsById(2);
            // logger.warn("Course{}", course + " " + course.getReviews());
            // createStudent(dao);
            // createCourseWithStudent(dao);
            // findCourseStudentshipsById(dao);
            // findStudentWithHisCourses(dao);
            // addCourseToStudent(dao);
            deleteStudentById(dao);
        };
    }

    /**
     * Deletes a student by ID from the database.
     *
     * @param dao the {@link InstructorDao} used for data access operations
     */
    private void deleteStudentById(InstructorDao dao) {
        int id = 1;
        dao.deleteStudentById(id);
    }

    /**
     * Adds a course to a student and updates the student in the database.
     *
     * @param dao the {@link InstructorDao} used for data access operations
     */
    private void addCourseToStudent(InstructorDao dao) {
        Student student = dao.findStudentsAndHisCoursesById(1);
        Course course = new Course("Spring Boot Basics");
        student.enrollCourse(course);
        dao.updateStudent(student);
    }

    /**
     * Finds a student with their courses by ID and logs the student and their courses.
     *
     * @param dao the {@link InstructorDao} used for data access operations
     */
    private void findStudentWithHisCourses(InstructorDao dao) {
        int id = 1;
        Student student = dao.findStudentsAndHisCoursesById(id);
        logger.warn("Student{}", student);
        logger.warn("Courses{}", student.getCourses());
    }

    /**
     * Finds a course with its students by ID and logs the course and its students.
     *
     * @param dao the {@link InstructorDao} used for data access operations
     */
    private void findCourseStudentshipsById(InstructorDao dao) {
        int id = 1;
        Course course = dao.findCourseWithItsStudentById(id);
        logger.warn("Course{}", course);
        logger.warn("Students{}", course.getStudents());
    }

    /**
     * Creates a new course, adds students to the course, and saves it to the database.
     *
     * @param dao the {@link InstructorDao} used for data access operations
     */
    private void createCourseWithStudent(InstructorDao dao) {
        // Create new Course
        Course course = new Course("Java Script");
        // Create Students
        Student student = new Student("Samer", "Hadi", "samer@emaol.com");
        Student student1 = new Student("Rami", "Ali", "Rami@gmail.com");
        // Add students to course
        course.addStudent(student);
        course.addStudent(student1);
        // Save it to the database
        dao.saveCourse(course);
    }

    /**
     * Creates a new student, enrolls the student in courses, and saves the student to the database.
     *
     * @param dao the {@link InstructorDao} used for data access operations
     */
    private void createStudent(InstructorDao dao) {
        Student student = new Student("Ahmad", "Ali", "ali@gmail.com");
        Course course = new Course("java");
        Course course1 = new Course("C++");
        student.enrollCourse(course1);
        student.enrollCourse(course);
        dao.saveStudent(student);
    }

    /**
     * Creates a new course with reviews and saves it to the database.
     *
     * @param dao the {@link InstructorDao} used for data access operations
     */
    private void createNewCourse(InstructorDao dao) {
        Course course = new Course("Java");
        Review review = new Review("Good Course");
        Review review1 = new Review("Awesome!!!!!");
        course.addReview(review);
        course.addReview(review1);
        dao.saveCourse(course);
    }

    /**
     * Deletes a course by ID from the database.
     *
     * @param dao the {@link InstructorDao} used for data access operations
     */
    private void deleteCourse(InstructorDao dao) {
        int id = 2;
        dao.deleteCourse(id);
    }

    /**
     * Updates a course's title by ID and logs the course before and after the update.
     *
     * @param dao the {@link InstructorDao} used for data access operations
     */
    private void updateCourse(InstructorDao dao) {
        int id = 2;
        Course course = dao.findCourseById(id);
        logger.warn("Before Update{}", course);
        course.setTitle("Ethical Hacking 2");
        dao.updateCourse(course);
        logger.warn("Updated{}", dao.findCourseById(id));
    }

    /**
     * Updates an instructor's details by ID and logs the instructor before and after the update.
     *
     * @param dao the {@link InstructorDao} used for data access operations
     */
    private void updateInstructor(InstructorDao dao) {
        int id = 1;
        Instructor instructor = dao.findInstructorByJoinFetch(id);
        logger.warn("Before Update {}", instructor);
        instructor.setFirstName("Leo");
        instructor.setLastName("Messi");
        dao.update(instructor);
        logger.warn("Updated{}", dao.findInstructorByJoinFetch(id));
    }

    /**
     * Retrieves an instructor by ID from the database.
     *
     * @param dao the {@link InstructorDao} used for data access operations
     * @return the {@link Instructor} with the specified ID
     */
    private Instructor getById(InstructorDao dao) {
        return dao.findById(1);
    }

    /**
     * Deletes an instructor detail by ID and logs the result.
     *
     * @param i the ID of the instructor detail to delete
     */
    private void deleteDetailById(int i) {
        logger.warn("Deleted{}", instructorDao.deleteDetailById(i));
    }

    /**
     * Retrieves and logs an instructor detail by ID.
     *
     * @param i the ID of the instructor detail to retrieve
     */
    private void getDetailById(int i) {
        InstructorDetail detail = instructorDao.findDetailById(i);
        logger.warn("InstructorDetail{} ", detail);
        logger.warn("Instructor{} ", detail.getInstructor().getFirstName());
    }

    /**
     * Deletes an instructor by ID and logs the result.
     *
     * @param instructor1 the ID of the instructor to delete
     * @return true if the instructor was deleted
     */
    private boolean delete(int instructor1) {
        instructorDao.delete(instructor1);
        logger.warn("Deleted " + instructor1);
        return true;
    }

    /**
     * Retrieves an instructor by their YouTube channel URL.
     *
     * @param s the YouTube channel URL
     * @return the {@link Instructor} with the specified YouTube channel
     */
    private Instructor getByYoutube(String s) {
        return instructorDao.findByYoutubeChannel(s);
    }

    /**
     * Creates a new instructor and saves it to the database.
     *
     * @param instructor the {@link Instructor} to create
     */
    private void create(Instructor instructor) {
        instructorDao.create(instructor);
    }
}
