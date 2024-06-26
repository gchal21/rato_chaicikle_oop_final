package DAOclasses;

import entities.UserHistory;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserHistoryDAO {
    private final BasicDataSource dataSource;
    private final String USER_HISTORY_TABLE = "user_history";

    public UserHistoryDAO(BasicDataSource source) {
        this.dataSource = source;
    }

    public void addUserHistory(UserHistory history) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO " + USER_HISTORY_TABLE +
                             " (userId, quizId, startDate, endDate, score) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setLong(1, history.getUserId());
            stmt.setLong(2, history.getQuizId());
            stmt.setTimestamp(3, history.getStartDate());
            stmt.setTimestamp(4, history.getEndDate());
            stmt.setInt(5, history.getScore());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding user history", e);
        }
    }

    public UserHistory getUserHistory(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM " + USER_HISTORY_TABLE + " WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserHistory(
                            rs.getLong("id"),
                            rs.getLong("userId"),
                            rs.getLong("quizId"),
                            rs.getTimestamp("startDate"),
                            rs.getTimestamp("endDate"),
                            rs.getInt("score")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user history", e);
        }
        return null;
    }

    public List<UserHistory> getAllUserHistories() {
        List<UserHistory> histories = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + USER_HISTORY_TABLE)) {
            while (rs.next()) {
                histories.add(new UserHistory(
                        rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getLong("quizId"),
                        rs.getTimestamp("startDate"),
                        rs.getTimestamp("endDate"),
                        rs.getInt("score")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all user histories", e);
        }
        return histories;
    }
}