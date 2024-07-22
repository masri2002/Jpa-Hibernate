package org.digitnary.traning.restcrudapp.entity;

import jakarta.persistence.*;

/**
 * Represents a review for a course.
 * @author Ahmad Al-Masri
 * @since 20/7/2024
 */
@Entity
@Table(name = "course_reviews")
public class Review {

    private int id;
    private String comment;

    /**
     * Default constructor for Review.
     */
    public Review() {
    }

    /**
     * Constructs a new Review with the specified comment.
     *
     * @param comment the comment for the review
     */
    public Review(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the ID of the Review.
     *
     * @return the ID of the Review
     */
    @Id
    @GeneratedValue(generator = "review-generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "review-generator", sequenceName = "course_review_generator", allocationSize = 1)
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the Review.
     *
     * @param id the new ID of the Review
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the comment for the Review.
     *
     * @return the comment for the Review
     */
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment for the Review.
     *
     * @param comment the new comment for the Review
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Returns a string representation of the Review.
     *
     * @return a string representation of the Review
     */
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
