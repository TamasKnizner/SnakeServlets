package com.epam.training.snake.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Score implements Serializable {

    private static final long serialVersionUID = 6450607607155140810L;

    private Integer id;
    private User user;
    private Integer score;
    private LocalDate timeStamp;

    public Score(Integer id, User user, String score, LocalDate timeStamp) {
        this.id = id;
        this.user = user;
        this.score = Integer.parseInt(score);
        this.timeStamp = timeStamp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((score == null) ? 0 : score.hashCode());
        result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Score other = (Score) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (score == null) {
            if (other.score != null)
                return false;
        } else if (!score.equals(other.score))
            return false;
        if (timeStamp == null) {
            if (other.timeStamp != null)
                return false;
        } else if (!timeStamp.equals(other.timeStamp))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }
    
    public Integer getId() {
		return id;
	}

	public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public Integer getScore() {
        return score;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Score [user=" + user + ", score=" + score + ", timeStamp=" + timeStamp + "]";
    }

}
