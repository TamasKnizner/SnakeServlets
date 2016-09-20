package com.epam.training.snake.endpoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.training.snake.util.SessionManager;

public class RootServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RootServlet.doGet()");
        String redirectURL = "/";
        System.out.println("Logged In: " + SessionManager.isLoggedIn(request));
        if (SessionManager.isLoggedIn(request)) {
            if (SessionManager.isAdmin(request)) {
                redirectURL += "admin";
            } else {
                redirectURL += "main";
            }
        } else {
            redirectURL += "login";
        }
        request.getRequestDispatcher(redirectURL).forward(request, response);
    }

}
