package servlets.home;

import entities.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    //should come from back
    private String userName;
    private String currentSection;



    public void init() throws ServletException {
        currentSection = "Announcements";

        //DUMMY DATA
        userName = "Aslan Abashidze";
        createMessagesDummyData();
        createTablesDummyData();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        manageSection(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("userName", userName);
        request.setAttribute("messages", messages);
        request.setAttribute("currentSection", currentSection);
        request.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request, response);
    }


    private List<Message> messages;
    private List<String> announcements;
    private List<String> popularQuizzes;
    private List<String> friendsActivities;
    private List<String> recentlyCreatedQuizzes;
    private List<String> myRecentlyTakenQuizzes;
    private List<String> myRecentlyCreatedQuizzes;

    private void manageSection(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String section = request.getParameter("section");
        if (section != null) {
            currentSection = section;
        }else{
            currentSection = "Announcements";
        }

        switch (currentSection) {
            case "Popular Quizzes":
                request.setAttribute("Popular Quizzes", popularQuizzes);
                break;
            case "My Recently Created Quizzes":
                request.setAttribute("My Recently Created Quizzes", myRecentlyCreatedQuizzes);
                break;
            case "Recently Created Quizzes":
                request.setAttribute("Recently Created Quizzes", recentlyCreatedQuizzes);
                break;
            case "Friends Activities":
                request.setAttribute("Friends Activities", friendsActivities);
                break;
            case "My Recently Taken Quizzes":
                request.setAttribute("My Recently Taken Quizzes", myRecentlyTakenQuizzes);
                break;
            default:
                request.setAttribute("Announcements", announcements);
        }

        String ajaxRequest = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(ajaxRequest)) {
            response.setContentType("application/json");
            response.getWriter().write("{\"currentSection\":\"" + currentSection + "\"}");
        } else {
            request.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request, response);
        }
    }

    private void createMessagesDummyData(){
        Message m1 = new Message("Tengiz Kitovani", "Rafer xar", new Timestamp(System.currentTimeMillis()));
        Message m2 = new Message("Eduard Shevardnadze", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", new Timestamp(System.currentTimeMillis()));
        Message m3 = new Message("Mikheil Saakashvili", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters.", new Timestamp(System.currentTimeMillis()));
        Message m4 = new Message("Gogita Gogolidze", "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form.", new Timestamp(System.currentTimeMillis()));
        Message m5 = new Message("Sasha Vereli", "Saghol chemi", new Timestamp(System.currentTimeMillis()));

        Message m6 = new Message("Tengiz Kitovani", "Rafer xar", new Timestamp(System.currentTimeMillis()));
        Message m7 = new Message("Eduard Shevardnadze", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", new Timestamp(System.currentTimeMillis()));
        Message m8 = new Message("Mikheil Saakashvili", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters.", new Timestamp(System.currentTimeMillis()));
        Message m9 = new Message("Gogita Gogolidze", "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form.", new Timestamp(System.currentTimeMillis()));
        Message m10 = new Message("Sasha Vereli", "Saghol chemi", new Timestamp(System.currentTimeMillis()));

        messages = Arrays.asList(new Message[]{m1, m2, m3, m4, m5, m6, m7, m8, m9, m10});
//        messages = Arrays.asList(new Message[]{m1, m2, m3, m4});
//        messages = new ArrayList<>();
    }

    private void createTablesDummyData(){
        this.popularQuizzes = new ArrayList<>();
        this.announcements = new ArrayList<>();
        this.myRecentlyCreatedQuizzes = new ArrayList<>();
        this.recentlyCreatedQuizzes = new ArrayList<>();
        this.friendsActivities = new ArrayList<>();
        this.myRecentlyTakenQuizzes =  new ArrayList<>();
        announcements.add("Announcement 1");
        announcements.add("Announcement 2");
        popularQuizzes.add("Quiz 1");
        popularQuizzes.add("Quiz 2");
        friendsActivities.add("Friend 1 took Quiz 3");
        friendsActivities.add("Friend 2 created Quiz 4");
        recentlyCreatedQuizzes.add("Quiz 5");
        recentlyCreatedQuizzes.add("Quiz 6");
        myRecentlyTakenQuizzes.add("Quiz 7");
        myRecentlyTakenQuizzes.add("Quiz 8");
        myRecentlyCreatedQuizzes.add("Quiz 9");
        myRecentlyCreatedQuizzes.add("Quiz 10");
    }

}
