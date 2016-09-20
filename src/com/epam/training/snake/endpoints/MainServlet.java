package com.epam.training.snake.endpoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.training.snake.util.ScoreManager;
import com.epam.training.snake.util.SessionManager;

public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MainServlet.doGet()");
        if (SessionManager.isLoggedIn(request)) {
            request.setAttribute("scores", ScoreManager.getScoresByUser(SessionManager.getUserFromSession(request)));
            request.getRequestDispatcher("/main.jsp").include(request, response);
        } else {
            request.getRequestDispatcher("/").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
