package entities;

import java.sql.Timestamp;

public class User {
    private long id;
    private String username;
    private String hashedPassword;
    private String email;
    private Timestamp createDate;
    private int roleId;

    public User(long id, String username, String hashedPassword, String email,
                Timestamp date, int roleId) {
        this.id = id;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.createDate=date;
        this.roleId = roleId;
    }


    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    /**
     * NOTE:
     * @return hashed password related to the user
     */
    public String getHashedPassword(){
        return hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }
    public int getRoleId() {
        return roleId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "ID: "+id
                +" USERNAME: "+username
                +" HASHED PASSWORD: " +hashedPassword
                +" EMAIL: "+ email
                +" CREATED ON: "+createDate
                +" ROLE ID: "+roleId;
    }
}
