package com.epam.training.snake.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.epam.training.snake.entity.Score;
import com.epam.training.snake.entity.User;

public class ScoreManager {

    private static List<Score> scores = new ArrayList<>();

    public static void saveScores() {
        try {
            FileOutputStream out = new FileOutputStream("scores.ser");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(scores);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Scores saved to scores.ser");
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadScores() {
        try {
            File file = new File("scores.ser");
            if (!file.exists()) {
                return;
            }
            FileInputStream in = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(in);
            scores = (ArrayList<Score>) (ois.readObject());
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Scores loaded from scores.ser");
            printList();
        }
    }

    public static List<Score> getScores() {
        return scores;
    }

    private static void printList() {
        scores.forEach(score -> System.out.println(score.toString()));
    }

    public static boolean addScore(Score score) {
        if (scores.contains(score)) {
            return false;
        }
        scores.add(score);
        return true;
    }
    
    public static void deleteScore(Integer scoreId) {
    	for (Iterator<Score> it = scores.listIterator(); it.hasNext();) {
    	    Score score = it.next();
    	    if (scoreId.equals(score.getId())) {
    	        it.remove();
    	    }
    	}
    }

    public static List<Score> getScoresByUser(User user) {
        List<Score> userScores = new ArrayList<>();
        for (Score s : scores) {
            if (s.getUser().equals(user)) {
                userScores.add(s);
            }
        }
        return userScores;
    }

    public static Integer getNewId() {
        return scores.get(scores.size()-1).getId()+1;
    }
    
    public static String buildScoreTable(User user, boolean isAdmin) {
    	StringBuilder sb = new StringBuilder("");
        List<Score> scores = (isAdmin) ? getScores() : getScoresByUser(user);
        for (Score score : scores) {
            sb.append(buildRow(score, isAdmin));
        }
        return sb.toString();
    }
    
    private static String buildRow(Score score, boolean isAdmin) {
    	StringBuilder sb = new StringBuilder("");
    	sb.append("<tr><td>");
        sb.append(score.getUser().getName());
        sb.append("</td><td>");
        sb.append(score.getScore());
        sb.append("</td><td>");
        sb.append(score.getTimeStamp().toString());
        sb.append("</td>");
        if(isAdmin) {
        	sb.append("<td><button class='btn btn-danger btn-sm' onclick='deleteScore(" + score.getId() + ")'>DELETE</button></td>");
        }
        sb.append("</tr>");
    	return sb.toString();
    }

}
