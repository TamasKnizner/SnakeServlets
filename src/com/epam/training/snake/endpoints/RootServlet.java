package com.epam.training.snake.endpoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.training.snake.util.SessionManager;

public class RootServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootServlet.class);

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectURL = "/";
        boolean isAdmin = SessionManager.isAdmin(request);
        boolean loggedIn = SessionManager.isLoggedIn(request);
        LOGGER.info("GET, loggedIn: {}, isAdmin: {}", loggedIn, isAdmin);
        if (loggedIn) {
            if (isAdmin) {
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
