package com.epam.training.snake.endpoints;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.training.snake.entity.Score;
import com.epam.training.snake.entity.User;
import com.epam.training.snake.util.ScoreManager;
import com.epam.training.snake.util.SessionManager;
import com.epam.training.snake.util.UserManager;

public class ScoreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ScoreServlet.doPost()");
        User user = getUser(request);
        ScoreManager.addScore(new Score(ScoreManager.getNewId(), user, request.getParameter("score"), LocalDate.now()));
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String table = ScoreManager.buildScoreTable(user, SessionManager.isAdmin(request));
        response.getWriter().write(table);
    }

	private User getUser(HttpServletRequest request) {
		User user = null;
        if (request.getParameter("id") != null) {
            Integer id = Integer.parseInt((String) request.getParameter("id"));
            user = UserManager.getUserById(id);
        } else {
            user = SessionManager.getUserFromSession(request);
        }
		return user;
	}

}
