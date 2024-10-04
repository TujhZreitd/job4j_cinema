package ru.job4j.cinema.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HallDto {
    private int id;
    private String name;
    private String description;
    private final Map<Integer, List<Integer>> placesHall;
    public HallDto(int id, String name, String description, int rows, int places) {
        this.id = id;
        this.name = name;
        this.description = description;
        placesHall = new HashMap<>();
        int count = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= places; j++) {
                placesHall.put(count++, List.of(i, j));
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Integer, List<Integer>> getPlacesHall() {
        return placesHall;
    }
}
