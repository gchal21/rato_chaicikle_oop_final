package DAOclasses;

import entities.Achievement;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;


//todo merge DAO-s together and call them UserDao
//TODO TEST THIS CLASS
public class UserAchievementsDAO {
    private final BasicDataSource dataSource;

    public UserAchievementsDAO(BasicDataSource source){
        this.dataSource = source;
    }


    /**
     * @param userId
     * @return list of achievement object related to user
     */
    public ArrayList<Achievement> getUserAchievements(long userId){
        ArrayList<Achievement> allAchievements=new ArrayList<>();
        try {
            Connection conn=dataSource.getConnection();
            String query = "SELECT a.name, a.description, a.imageUrl, ua.acquireDate " +
                    "FROM user_achievements ua " +
                    "JOIN achievements a ON ua.achievementId = a.id " +
                    "WHERE ua.userId = ?;";
            PreparedStatement statement=conn.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                String description = rs.getString("description");
                String imageUrl = rs.getString("imageUrl");
                Timestamp acquireDate = rs.getTimestamp("acquireDate");

                Achievement achievement = new Achievement(name, description, imageUrl, acquireDate);
                allAchievements.add(achievement);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return allAchievements;
    }


    /**
     *
     * @param userId long
     * @param achievementId id of the existing achievement
     */
    public void addUserAchievement(long userId, int achievementId){
        try {
            Connection conn=dataSource.getConnection();
            String query = "INSERT INTO user_achievements (userId, achievementId) VALUES (?, ?);";
            PreparedStatement statement= conn.prepareStatement(query); //todo check this is query string ok?
            statement.setLong(1,userId);
            statement.setInt(2, achievementId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * removes given achievement for the given user
     *
     * NOTE: PDF STATES THAT ONCE USER RECEIVES AN ACHIEVEMENT
     * BADGE, IT DOESN'T GO AWAY BUT STILL WE HAVE IT
     * (MAYBE CONSIDER FOR EXTENSION)
     * @param userId
     * @param achievementId
     */
    public void removeUserAchievement(long userId, int achievementId){
        try {
            Connection conn=dataSource.getConnection();
            String query = "DELETE FROM user_achievements WHERE userId = ? AND achievementId = ?;";
            PreparedStatement statement= conn.prepareStatement(query); //todo check this is query string ok?
            statement.setLong(1,userId);
            statement.setInt(2, achievementId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






}
