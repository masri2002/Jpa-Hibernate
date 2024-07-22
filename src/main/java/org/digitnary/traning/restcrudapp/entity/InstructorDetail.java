package org.digitnary.traning.restcrudapp.entity;

import jakarta.persistence.*;

/**
 * Represents the detailed information of an instructor.
 * @author Ahmad Al-Masri
 * @since 19/7/2024
 */
@Entity
@Table(name = "Instructor_Detail")
public class InstructorDetail {

    private int id;
    private String youtubeChannel;
    private String hobby;
    private Instructor instructor;

    /**
     * Default constructor for InstructorDetail.
     */
    public InstructorDetail() {
    }

    /**
     * Constructs a new InstructorDetail with the specified YouTube channel and hobby.
     *
     * @param youtubeChannel the YouTube channel of the instructor
     * @param hobby the hobby of the instructor
     */
    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    /**
     * Gets the ID of the InstructorDetail.
     *
     * @return the ID of the InstructorDetail
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InstructorDetailSeq")
    @SequenceGenerator(name = "InstructorDetailSeq", sequenceName = "InstructorDetail_Sequence", allocationSize = 1)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the InstructorDetail.
     *
     * @param id the new ID of the InstructorDetail
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the YouTube channel of the instructor.
     *
     * @return the YouTube channel of the instructor
     */
    @Column(name = "Youtube_Channel")
    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    /**
     * Sets the YouTube channel of the instructor.
     *
     * @param youtubeChannel the new YouTube channel of the instructor
     */
    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    /**
     * Gets the hobby of the instructor.
     *
     * @return the hobby of the instructor
     */
    @Column(name = "Hobby")
    public String getHobby() {
        return hobby;
    }

    /**
     * Sets the hobby of the instructor.
     *
     * @param hobby the new hobby of the instructor
     */
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    /**
     * Gets the associated Instructor.
     *
     * @return the associated Instructor
     */
    @OneToOne(mappedBy = "detail", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Sets the associated Instructor.
     *
     * @param instructor the new associated Instructor
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * Returns a string representation of the InstructorDetail.
     *
     * @return a string representation of the InstructorDetail
     */
    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
