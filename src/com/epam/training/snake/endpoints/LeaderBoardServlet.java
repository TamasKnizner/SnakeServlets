package com.epam.training.snake.endpoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.training.snake.util.ScoreManager;

public class LeaderBoardServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaderBoardServlet.class);

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("GET");
        request.setAttribute("topten", ScoreManager.getTopTen());
        request.getRequestDispatcher("/leaderboard.jsp").include(request, response);
    }

}
