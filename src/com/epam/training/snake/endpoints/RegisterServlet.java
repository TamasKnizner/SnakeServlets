package com.epam.training.snake.endpoints;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.training.snake.entity.User;
import com.epam.training.snake.util.UserManager;

public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RegisterServlet.doGet()");
        request.getRequestDispatcher("/register.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RegisterServlet.doPost()");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(name + " " + email + " " + password);
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
