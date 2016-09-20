package com.epam.training.snake.endpoints;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.training.snake.entity.Score;
import com.epam.training.snake.entity.User;
import com.epam.training.snake.util.ScoreManager;
import com.epam.training.snake.util.SessionManager;
import com.epam.training.snake.util.UserManager;

public class ScoreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> params = request.getParameterNames();
        System.out.println(request.getAttribute("scoreId"));
        while (params.hasMoreElements()) {
            System.out.println(params.nextElement());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ScoreServlet.doPost()");
        User user = null;
        if (request.getParameter("id") != null) {
            Integer id = Integer.parseInt((String) request.getParameter("id"));
            user = UserManager.getUserById(id);
        } else {
            user = SessionManager.getUserFromSession(request);
        }
        ScoreManager.addScore(new Score(ScoreManager.getNewId(), user, request.getParameter("score"), LocalDate.now()));
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(buildTable(user, SessionManager.isAdmin(request)));
    }

    private String buildTable(User loggedUser, boolean isAdmin) {
        StringBuilder sb = new StringBuilder("");
        List<Score> scores = (isAdmin) ? ScoreManager.getScores() : ScoreManager.getScoresByUser(loggedUser);
        for (Score score : scores) {
            sb.append("<tr><td>");
            sb.append(score.getUser().getName());
            sb.append("</td><td>");
            sb.append(score.getScore());
            sb.append("</td><td>");
            sb.append(score.getTimeStamp().toString());
            sb.append("</td></tr>");
        }
        return sb.toString();
    }

}
