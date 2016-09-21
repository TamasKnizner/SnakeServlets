package com.epam.training.snake.endpoints;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.training.snake.entity.Score;
import com.epam.training.snake.entity.User;
import com.epam.training.snake.util.ScoreManager;
import com.epam.training.snake.util.SessionManager;
import com.epam.training.snake.util.UserManager;

public class ScoreServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreServlet.class);

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getUser(request);
        String score = request.getParameter("score");
        boolean isAdmin = SessionManager.isAdmin(request);
        LOGGER.info("POST, isAdmin: {}, user: {}, score: {}", isAdmin, user.toString(), score);
        ScoreManager.addScore(new Score(ScoreManager.getNewId(), user, score, LocalDate.now()));
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String table = ScoreManager.buildScoreTable(user, isAdmin);
        response.getWriter().write(table);
    }

    private User getUser(HttpServletRequest request) {
        User user = null;
        if (request.getParameter("id") != null) {
            Integer id = Integer.parseInt((String) request.getParameter("id"));
            user = UserManager.getUserById(id);
        } else {
            user = SessionManager.getUserFromSession(request);
        }
        return user;
    }

}
