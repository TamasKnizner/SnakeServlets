package com.epam.training.snake.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.training.snake.entity.User;

public class SessionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionManager.class);

    public static boolean isLoggedIn(HttpServletRequest request) {

        if (request.getSession(true).getAttribute("loggedIn") == null) {
            return false;
        }
        return (boolean) request.getSession(true).getAttribute("loggedIn");
    }

    public static void logout(HttpServletRequest request) {
        LOGGER.info("Loggin out user {}", getUserFromSession(request));
        request.getSession(true).setAttribute("loggedIn", false);
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("isAdmin");
    }

    public static User getUserFromSession(HttpServletRequest request) {
        User user = null;
        if (isLoggedIn(request)) {
            user = (User) request.getSession().getAttribute("user");
        }
        return user;
    }

    public static boolean isAdmin(HttpServletRequest request) {
        if (request.getSession(true).getAttribute("isAdmin") == null) {
            return false;
        }
        return (boolean) request.getSession(true).getAttribute("isAdmin");
    }

}
