package entities;

import java.sql.Timestamp;

public class Report {
    private long id;
    private long userId;
    private long quizId;
    private String problem;
    private Timestamp createDate;

    public Report(long id, long userId, long quizId, String problem, Timestamp createDate) {
        this.id = id;
        this.userId = userId;
        this.quizId = quizId;
        this.problem = problem;
        this.createDate = createDate;
    }

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public long getQuizId() { return quizId; }
    public void setQuizId(long quizId) { this.quizId = quizId; }
    public String getProblem() { return problem; }
    public void setProblem(String problem) { this.problem = problem; }
    public Timestamp getCreateDate() { return createDate; }
    public void setCreateDate(Timestamp createDate) { this.createDate = createDate; }

    @Override
    public String toString() {
        return "ID: " + id + " USER_ID: " + userId + " QUIZ_ID: " + quizId +
                " PROBLEM: " + problem + " CREATE_DATE: " + createDate;
    }
}