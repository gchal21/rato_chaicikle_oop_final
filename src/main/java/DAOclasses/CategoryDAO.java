package DAOclasses;

import entities.Category;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private final BasicDataSource dataSource;
    private final String CATEGORIES_TABLE = "categories";

    public CategoryDAO(BasicDataSource source) {
        this.dataSource = source;
    }

    public void addCategory(Category category) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO " + CATEGORIES_TABLE + " (name) VALUES (?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, category.getName());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    category.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding category", e);
        }
    }

    public Category getCategory(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM " + CATEGORIES_TABLE + " WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Category(
                            rs.getLong("id"),
                            rs.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving category", e);
        }
        return null;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + CATEGORIES_TABLE)) {
            while (rs.next()) {
                categories.add(new Category(
                        rs.getLong("id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all categories", e);
        }
        return categories;
    }

    public void updateCategory(Category category) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE " + CATEGORIES_TABLE + " SET name = ? WHERE id = ?")) {
            stmt.setString(1, category.getName());
            stmt.setLong(2, category.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating category", e);
        }
    }

    public void deleteCategory(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM " + CATEGORIES_TABLE + " WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting category", e);
        }
    }
}