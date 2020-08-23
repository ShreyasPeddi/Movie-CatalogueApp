package io.javabrains.moviecatalogservice.models;

public class Rating {
    private String id;
    private int rating;

    public Rating() {
    }

    public Rating(String id, int rating) {
        this.id = id;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
