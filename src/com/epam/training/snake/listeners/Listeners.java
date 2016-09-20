package com.epam.training.snake.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.epam.training.snake.util.ScoreManager;
import com.epam.training.snake.util.UserManager;

public class Listeners implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("CONTEXT DESTROYED");
        UserManager.saveUsers();
        ScoreManager.saveScores();
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("CONTEXT INITIALIZED");
        UserManager.loadUsers();
        ScoreManager.loadScores();
    }

}
