package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.ScheduleDto;
import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;

public interface FilmSessionService {
    Collection<ScheduleDto> findAll();
}
