package model;

public class User {
    private String name;
    private int ratingPoints;

    public User(String name, int ratingPoints) {
        this.name = name;
        this.ratingPoints = ratingPoints;
    }

    public String getName() {
        return name;
    }

    public int getRatingPoints() {
        return ratingPoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRatingPoints(int ratingPoints) {
        this.ratingPoints = ratingPoints;
    }
}
