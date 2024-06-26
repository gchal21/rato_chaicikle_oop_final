package DAOclasses;

import entities.Announcement;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementsDAO {
    private final BasicDataSource dataSource;
    private final String ANNOUNCEMENTS_TABLE = "announcements";

    public AnnouncementsDAO(BasicDataSource source) {
        this.dataSource = source;
    }

    public void addAnnouncement(Announcement announcement) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO " + ANNOUNCEMENTS_TABLE +
                             " (userId, content, createDate) VALUES (?, ?, ?)")) {
            stmt.setLong(1, announcement.getUserId());
            stmt.setString(2, announcement.getContent());
            stmt.setTimestamp(3, announcement.getCreateDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding announcement", e);
        }
    }

    public Announcement getAnnouncement(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM " + ANNOUNCEMENTS_TABLE + " WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Announcement(
                            rs.getLong("id"),
                            rs.getLong("userId"),
                            rs.getString("content"),
                            rs.getTimestamp("createDate")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving announcement", e);
        }
        return null;
    }

    public List<Announcement> getAllAnnouncements() {
        List<Announcement> announcements = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + ANNOUNCEMENTS_TABLE)) {
            while (rs.next()) {
                announcements.add(new Announcement(
                        rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getString("content"),
                        rs.getTimestamp("createDate")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all announcements", e);
        }
        return announcements;
    }
}