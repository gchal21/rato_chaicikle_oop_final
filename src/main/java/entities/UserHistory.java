package entities;

import java.sql.Timestamp;

public class UserHistory {
    private long id;
    private long userId;
    private long quizId;
    private Timestamp startDate;
    private Timestamp endDate;
    private int score;

    public UserHistory(long id, long userId, long quizId, Timestamp startDate, Timestamp endDate, int score) {
        this.id = id;
        this.userId = userId;
        this.quizId = quizId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.score = score;
    }

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public long getQuizId() { return quizId; }
    public void setQuizId(long quizId) { this.quizId = quizId; }
    public Timestamp getStartDate() { return startDate; }
    public void setStartDate(Timestamp startDate) { this.startDate = startDate; }
    public Timestamp getEndDate() { return endDate; }
    public void setEndDate(Timestamp endDate) { this.endDate = endDate; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    @Override
    public String toString() {
        return "ID: " + id + " USER_ID: " + userId + " QUIZ_ID: " + quizId +
                " START_DATE: " + startDate + " END_DATE: " + endDate + " SCORE: " + score;
    }
}