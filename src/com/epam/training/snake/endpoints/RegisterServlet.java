package com.epam.training.snake.endpoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.training.snake.entity.User;
import com.epam.training.snake.util.UserManager;

public class RegisterServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServlet.class);

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("GET");
        request.getRequestDispatcher("/register.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LOGGER.info("POST, name: {}, email: {}, password: {}", name, email, password);
        String redirectURI = request.getContextPath() + createResponse(request, name, email, password);
        String encodeRedirectURL = response.encodeRedirectURL(redirectURI);
        response.sendRedirect(encodeRedirectURL);
    }

    private String createResponse(HttpServletRequest request, String name, String email, String password) {
        String redirectURI = "/message.jsp?";
        if (name != "" && email != "" && password != "") {
            User newUser = new User(UserManager.getNewId(), name, email, password);
            if (UserManager.addUser(newUser)) {
                redirectURI += "title=Registration successful!&message= ";
                request.getSession(true).setAttribute("loggedIn", true);
                request.getSession(true).setAttribute("user", UserManager.getUserByName(email));
            } else {
                redirectURI += "title=Registration unsuccessful!&message=User already exists!";
            }
        } else {
            redirectURI += "title=Registration unsuccessful!&message=Missing data!";
        }
        return redirectURI;
    }

}
