package DAOclasses;

import entities.Review;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDAO {
    private final BasicDataSource dataSource;
    private final String REVIEWS_TABLE = "reviews";

    public ReviewsDAO(BasicDataSource source) {
        this.dataSource = source;
    }

    public void addReview(Review review) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO " + REVIEWS_TABLE +
                             " (userId, review, rating, createDate) VALUES (?, ?, ?, ?)")) {
            stmt.setLong(1, review.getUserId());
            stmt.setString(2, review.getReview());
            stmt.setInt(3, review.getRating());
            stmt.setTimestamp(4, review.getCreateDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding review", e);
        }
    }

    public Review getReview(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM " + REVIEWS_TABLE + " WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Review(
                            rs.getLong("id"),
                            rs.getLong("userId"),
                            rs.getString("review"),
                            rs.getInt("rating"),
                            rs.getTimestamp("createDate")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving review", e);
        }
        return null;
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + REVIEWS_TABLE)) {
            while (rs.next()) {
                reviews.add(new Review(
                        rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getString("review"),
                        rs.getInt("rating"),
                        rs.getTimestamp("createDate")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all reviews", e);
        }
        return reviews;
    }
}