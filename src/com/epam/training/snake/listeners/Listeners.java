package com.epam.training.snake.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.training.snake.util.ScoreManager;
import com.epam.training.snake.util.UserManager;

public class Listeners implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listeners.class);

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LOGGER.info("Context destroyed");
        UserManager.saveUsers();
        ScoreManager.saveScores();
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOGGER.info("Context initialized");
        UserManager.loadUsers();
        ScoreManager.loadScores();
    }

}
