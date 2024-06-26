package DAOclasses;

import entities.Friendship;
import enums.FriendshipStatus;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//TODO FINISH THIS CLASS AND MERGE DAO CLASSES RELATED TO USERS INTO ONE CLASS
//TODO TEST THIS CLASS
public class FriendshipDAO {
    private final BasicDataSource dataSource;


    public FriendshipDAO(BasicDataSource source){
        this.dataSource = source;
    }


    /**
     * send friend request from senderId to receiverId (status of the request is 'PENDING')
     * @param senderId
     * @param receiverId
     */
    public void sendFriendRequest(long senderId, long receiverId){
        try {
            Connection conn=dataSource.getConnection();
            String query = "INSERT INTO friendship (senderId, receiverId, status) VALUES (?, ?, ?);";
            //todo check the status (make it enum or something)
            PreparedStatement statement= conn.prepareStatement(query);
            statement.setLong(1,senderId);
            statement.setLong(2, receiverId);
            statement.setString(3, FriendshipStatus.PENDING.name()); //todo check this

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * accepts the request or declines it depending on how isAccepted parameter is set
     * @param senderId
     * @param receiverId
     * @param isAccepted
     */
    public void acceptOrDeclineFriendRequest(long senderId, long receiverId, boolean isAccepted){
        try {
            Connection conn=dataSource.getConnection();

            String query = "UPDATE friendship SET status = ? WHERE senderId = ? AND receiverId = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            if(isAccepted){
                statement.setString(1, FriendshipStatus.ACCEPTED.name());

            }else{
                statement.setString(1, FriendshipStatus.DECLINED.name());

            }
            statement.setLong(2, senderId);
            statement.setLong(3, receiverId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * returns list of ids of the request sender users
     * friendship status is 'PENDING' at this moment
     * @param userId the user that received friend request
     * @return list of senders' ids
     */
    public ArrayList<Long> getReceivedRequests(long userId){
        ArrayList<Long> senders = new ArrayList<>();

        try {
            Connection conn=dataSource.getConnection();
            String query = "SELECT f.senderId FROM friendship f " +
                    "WHERE f.receiverId = ? AND f.status = ? ; ";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, userId);
            statement.setString(2, FriendshipStatus.PENDING.name());


            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                long senderId=resultSet.getLong("senderId");
                senders.add(senderId);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return senders;
    }



    /**
     * returns list of Frienship object-s related to the given userId
     * if boolean parameter acceptedRequest is
     * true -then we return friendship-s with 'ACCEPTED' status,
     * if false -we return "DECLINED" requests
     * @param userId
     * @param acceptedRequest
     * @return
     */
    public ArrayList<Friendship> getRequests(long userId, boolean acceptedRequest){
        ArrayList<Friendship> friendships = new ArrayList<>();

        try {
            Connection conn=dataSource.getConnection();
            String query = "SELECT f.senderId, f.receiverId, f.status, f.sendDate, f.executionDate FROM friendship f " +
                    "WHERE (f.senderId = ? OR f.receiverId = ?) AND f.status = ? ; ";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, userId);
            statement.setLong(2, userId);
            if(acceptedRequest){
                statement.setString(3, FriendshipStatus.ACCEPTED.name());
            }else{
                statement.setString(3, FriendshipStatus.DECLINED.name());
            }
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Friendship newFriendship=new Friendship(
                        resultSet.getLong("senderId"),
                        resultSet.getLong("receiverId"),
                        resultSet.getString("status"),
                        resultSet.getTimestamp("sendDate"),
                        resultSet.getTimestamp("executionDate"));

                friendships.add(newFriendship);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return friendships;
    }


    /**
     * returns list of Friendship object - where receiver user is always given userId
     * and the request status is 'PENDING' right now
     * @param userId
     * @return
     */
    public ArrayList<Friendship> getReceivedRequestInfo(long userId){
        ArrayList<Friendship> friendships = new ArrayList<>();

        try {
            Connection conn=dataSource.getConnection();
            String query = "SELECT f.senderId, f.receiverId, f.status, f.sendDate, f.executionDate FROM friendship f " +
                    "WHERE f.receiverId = ? AND f.status = ? ; ";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, userId);
            statement.setString(2, FriendshipStatus.PENDING.name());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Friendship newFriendship=new Friendship(
                        resultSet.getLong("senderId"),
                        resultSet.getLong("receiverId"),
                        resultSet.getString("status"),
                        resultSet.getTimestamp("sendDate"),
                        resultSet.getTimestamp("executionDate"));

                friendships.add(newFriendship);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return friendships;
    }


    /**
     * returns list of given user's 'friends' if acceptedRequest is set true
     * else returns list of ids of the user's that declined request (either them
     * or given user declined)
     * @param userId
     * @param acceptedRequest
     * @return id-s (long)
     */
    public ArrayList<Long> getFriendsIds(long userId, boolean acceptedRequest){
        ArrayList<Long> friends = new ArrayList<>();

        try {
            Connection conn=dataSource.getConnection();
            String query = "SELECT f.senderId, f.receiverId FROM friendship f " +
                    "WHERE (f.senderId = ? OR f.receiverId = ?) AND f.status = ? ; ";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, userId);
            statement.setLong(2, userId);
            if(acceptedRequest){
                statement.setString(3, FriendshipStatus.ACCEPTED.name());
            }else{
                statement.setString(3, FriendshipStatus.DECLINED.name());
            }
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                long friendId_1=resultSet.getLong("senderId");
                long friendId_2=resultSet.getLong("receiverId");
                if(friendId_1==userId){
                    friends.add(friendId_2);
                }else{
                    friends.add(friendId_1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return friends;
    }




}
