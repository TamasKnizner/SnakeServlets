package com.epam.training.snake.endpoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.training.snake.util.ScoreManager;
import com.epam.training.snake.util.SessionManager;
import com.epam.training.snake.util.UserManager;

public class AdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (SessionManager.isAdmin(request)) {
            request.setAttribute("users", UserManager.getUsers());
            request.setAttribute("scores", ScoreManager.getScores());
            request.getRequestDispatcher("/admin.jsp").include(request, response);
        } else {
            request.getRequestDispatcher("/").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
