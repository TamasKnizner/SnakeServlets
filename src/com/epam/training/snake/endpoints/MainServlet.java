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

public class MainServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainServlet.class);

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean loggedIn = SessionManager.isLoggedIn(request);
        LOGGER.info("GET, loggedIn: {}", loggedIn);
        if (loggedIn) {
            request.setAttribute("scores", ScoreManager.getScoresByUser(SessionManager.getUserFromSession(request)));
            request.getRequestDispatcher("/main.jsp").include(request, response);
        } else {
            request.getRequestDispatcher("/").forward(request, response);
        }

    }

}
