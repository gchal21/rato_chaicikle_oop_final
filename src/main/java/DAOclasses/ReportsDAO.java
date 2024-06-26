package DAOclasses;

import entities.Report;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportsDAO {
    private final BasicDataSource dataSource;
    private final String REPORTS_TABLE = "reports";

    public ReportsDAO(BasicDataSource source) {
        this.dataSource = source;
    }

    public void addReport(Report report) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO " + REPORTS_TABLE +
                             " (userId, quizId, problem, createDate) VALUES (?, ?, ?, ?)")) {
            stmt.setLong(1, report.getUserId());
            stmt.setLong(2, report.getQuizId());
            stmt.setString(3, report.getProblem());
            stmt.setTimestamp(4, report.getCreateDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding report", e);
        }
    }

    public Report getReport(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM " + REPORTS_TABLE + " WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Report(
                            rs.getLong("id"),
                            rs.getLong("userId"),
                            rs.getLong("quizId"),
                            rs.getString("problem"),
                            rs.getTimestamp("createDate")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving report", e);
        }
        return null;
    }

    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + REPORTS_TABLE)) {
            while (rs.next()) {
                reports.add(new Report(
                        rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getLong("quizId"),
                        rs.getString("problem"),
                        rs.getTimestamp("createDate")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all reports", e);
        }
        return reports;
    }

    // Additional methods for update and delete can be added here
}