package entities;

import java.sql.Timestamp;

public class Announcement {
    private long id;
    private long userId;
    private String content;
    private Timestamp createDate;

    public Announcement(long id, long userId, String content, Timestamp createDate) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createDate = createDate;
    }

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Timestamp getCreateDate() { return createDate; }
    public void setCreateDate(Timestamp createDate) { this.createDate = createDate; }

    @Override
    public String toString() {
        return "ID: " + id + " USER_ID: " + userId + " CONTENT: " + content +
                " CREATE_DATE: " + createDate;
    }
}