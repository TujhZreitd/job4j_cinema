package ru.job4j.cinema.dto;

import java.time.LocalDateTime;

public class FilmSessionDto {
    private int id;
    private String filmName;
    private String description;
    private String hallName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int price;
    private int fileId;
    private int hallId;
    private int row;
    private int place;
    public FilmSessionDto() {

    }

    public FilmSessionDto(int id, String filmName, String description, String hallName, LocalDateTime startTime, LocalDateTime endTime, int price, int fileId, int hallId) {
        this.id = id;
        this.filmName = filmName;
        this.description = description;
        this.hallName = hallName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.fileId = fileId;
        this.hallId = hallId;
    }

    public FilmSessionDto(int id, String filmName, String description, String hallName, LocalDateTime startTime,
                          LocalDateTime endTime, int price, int fileId, int hallId, int row, int place) {
        this.id = id;
        this.filmName = filmName;
        this.description = description;
        this.hallName = hallName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.fileId = fileId;
        this.hallId = hallId;
        this.row = row;
        this.place = place;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
