package com.epam.training.snake.endpoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.training.snake.util.ScoreManager;
import com.epam.training.snake.util.SessionManager;
import com.epam.training.snake.util.UserManager;

public class AdminServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServlet.class);

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAdmin = SessionManager.isAdmin(request);
        LOGGER.info("GET, isAdmin: {}", isAdmin);
        if (isAdmin) {
            request.setAttribute("users", UserManager.getUsers());
            request.setAttribute("scores", ScoreManager.getScores());
            request.getRequestDispatcher("/admin.jsp").include(request, response);
        } else {
            request.getRequestDispatcher("/").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAdmin = SessionManager.isAdmin(request);
        LOGGER.info("POST, isAdmin: {}", isAdmin);
        if (SessionManager.isAdmin(request)) {
            Integer scoreId = Integer.parseInt(request.getParameter("scoreId"));
            LOGGER.info("Deleting score with ID {}", scoreId);
            ScoreManager.deleteScore(scoreId);
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            String table = ScoreManager.buildScoreTable(SessionManager.getUserFromSession(request), SessionManager.isAdmin(request));
            response.getWriter().write(table);
        }
    }

}
