package model;

import lombok.Data;

@Data
public class User {
    private String name;
    private int ratingPoints;

    public User(String name, int ratingPoints) {
        this.name = name;
        this.ratingPoints = ratingPoints;
    }
}
