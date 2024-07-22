package org.digitnary.traning.restcrudapp.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.digitnary.traning.restcrudapp.dao.InstructorDao;
import org.digitnary.traning.restcrudapp.entity.Course;
import org.digitnary.traning.restcrudapp.entity.Instructor;
import org.digitnary.traning.restcrudapp.entity.InstructorDetail;
import org.digitnary.traning.restcrudapp.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import java.util.List;
/**
 * Implementation of the InstructorDao interface.
 * Provides methods to perform CRUD operations on Instructor, Course, and Student entities.
 * @author Ahmad Al-Masri
 * @since last updated in 22/7/2024
 */
@Repository
@Transactional
public class InstructorDaoImpl implements InstructorDao {
    private final EntityManager em;
    @Autowired
    public InstructorDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Instructor instructor) {
        em.persist(instructor);
    }

    @Override
    public void update(Instructor instructor) {
      em.merge(instructor);
    }

    @Override
    public void delete(int id) {
        Instructor managedInstructor =findInstructorByJoinFetch(id);
        if (managedInstructor != null) {
             managedInstructor.getCourses().
                     forEach((course)-> course.setInstructor(null));
            em.remove(managedInstructor);
        }

    }

    @Override
    public Instructor findById(int id) {
        return em.find(Instructor.class, id);
    }

    @Override
    public List<Instructor> findAll() {
        return em.createQuery("from Instructor", Instructor.class).getResultList();
    }

    @Override
    public Instructor findByName(String firstName, String lastName) {
        return em.createQuery("from Instructor where firstName=:fN and lastName=:lN",Instructor.class).
                setParameter("fN", firstName).
                setParameter("lN", lastName).
                getSingleResult();
    }

    @Override
    public Instructor findByEmail(String email) {
        return em.createQuery("from Instructor where email=:e",Instructor.class).
                setParameter("e", email).getSingleResult();
    }

    @Override
    public Instructor findByYoutubeChannel(String youtubeChannel) {
        return em.createQuery(
                        "SELECT i FROM Instructor i JOIN i.detail id WHERE id.youtubeChannel = :youtube", Instructor.class)
                .setParameter("youtube", youtubeChannel)
                .getSingleResult();
    }

    @Override
    public InstructorDetail findDetailById(int id) {
        return em.find(InstructorDetail.class, id);
    }

    @Override
    public InstructorDetail deleteDetailById(int id) {
        InstructorDetail instructorDetail = em.find(InstructorDetail.class, id);
        if(instructorDetail != null) {
            instructorDetail.getInstructor().setDetail(null);
            em.remove(instructorDetail);
            return instructorDetail;
        }
        return null;
    }

    @Override
    public List<Course> findByInstructor(Instructor i) {
        return em.createQuery("from Course where instructor.id =:i",Course.class).setParameter("i",i.getId())
                .getResultList();
    }

    @Override
    public Course findCourseById(int id) {
        return em.find(Course.class,id);
    }

    @Override
    public Course findCourseAndInstructor(int id) {
        Course course = findCourseById(id);
        findById(course.getInstructor().getId());
        return course;
    }

    public Instructor findInstructorByJoinFetch(int id) {
        return em.createQuery("SELECT i from Instructor i " +
                "JOIN FETCH i.courses " + "where i.id=:data" , Instructor.class )
                .setParameter("data",id).getSingleResult();
    }

    @Override
    public void updateCourse(Course course) {
        em.merge(course);
    }

    @Override
    public void deleteCourse(int id) {
        Course course = findCourseById(id);
        em.remove(course);
    }

    @Override
    public void saveCourse(Course course) {
        em.persist(course);
    }

    @Override
    public Course findCourseAndReviewsById(int id) {
        return em.createQuery("SELECT c from Course c "+
                "JOIN FETCH c.reviews "+"where c.id=:data",Course.class).
                setParameter("data",id).getSingleResult();
    }

    @Override
    public void saveStudent(Student student) {
        em.persist(student);
    }

    @Override
    public Course findCourseWithItsStudentById(int id) {
        return em.createQuery("SELECT c from Course c "+
                "JOIN FETCH c.students "+"where c.id = :data",Course.class).
                setParameter("data",id).getSingleResult();
    }

    @Override
    public Student findStudentsAndHisCoursesById(int id) {
        return em.createQuery("SELECT s FROM Student s "+
                "JOIN FETCH s.courses "+"where s.id=:data",Student.class)
                .setParameter("data",id).getSingleResult();
    }

    @Override
    public void updateStudent(Student student) {
        em.merge(student);
    }

    @Override
    public void deleteStudentById(int id) {
        em.remove(findStudentsAndHisCoursesById(id));
    }
}
