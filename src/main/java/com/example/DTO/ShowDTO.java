package com.example.DTO;

public class ShowDTO {
    private String showTime;
    private Double price;
    private Long movieId;
    private Long theaterId;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Long theaterId) {
        this.theaterId = theaterId;
    }

    public ShowDTO(String showTime, Double price, Long movieId, Long theaterId) {
        this.showTime = showTime;
        this.price = price;
        this.movieId = movieId;
        this.theaterId = theaterId;
    }
    public ShowDTO() {
    }
}
