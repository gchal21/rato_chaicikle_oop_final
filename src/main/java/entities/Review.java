package entities;

import java.sql.Timestamp;

public class Review {
    private long id;
    private long userId;
    private String review;
    private int rating;
    private Timestamp createDate;

    public Review(long id, long userId, String review, int rating, Timestamp createDate) {
        this.id = id;
        this.userId = userId;
        this.review = review;
        this.rating = rating;
        this.createDate = createDate;
    }

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public Timestamp getCreateDate() { return createDate; }
    public void setCreateDate(Timestamp createDate) { this.createDate = createDate; }

    @Override
    public String toString() {
        return "ID: " + id + " USER_ID: " + userId + " REVIEW: " + review +
                " RATING: " + rating + " CREATE_DATE: " + createDate;
    }
}