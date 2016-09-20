package com.epam.training.snake.endpoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.training.snake.util.UserManager;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginServlet.doGet()");
        request.getRequestDispatcher("/login.jsp").include(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginServlet.doPost()");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email + " " + password);
        String redirectURI = request.getContextPath();
        if (UserManager.validateLogin(email, password)) {
            if ("admin@admin.com".equals(email)) {
                redirectURI += "/admin";
                request.getSession(true).setAttribute("isAdmin", true);
            } else {
                redirectURI += "/main";
            }
            request.getSession(true).setAttribute("loggedIn", true);
            request.getSession(true).setAttribute("user", UserManager.getUserByName(email));
        } else {
            redirectURI += "/message.jsp?title=Login unsuccessful!&message=Invalid login credentials!";
        }
        String encodeRedirectURL = response.encodeRedirectURL(redirectURI);
        response.sendRedirect(encodeRedirectURL);
    }

}
