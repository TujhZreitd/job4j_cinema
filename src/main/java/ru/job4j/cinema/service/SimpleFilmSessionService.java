package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.dto.ScheduleDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.HallRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class SimpleFilmSessionService implements FilmSessionService {
    private FilmSessionRepository filmSessionRepository;
    private FilmRepository filmRepository;
    private HallRepository hallRepository;

    public SimpleFilmSessionService(FilmSessionRepository sql2oFilmSessionRepository,
                                    FilmRepository sql2oFilmRepository,
                                    HallRepository sql2oHallRepository) {
        this.filmSessionRepository = sql2oFilmSessionRepository;
        this.filmRepository = sql2oFilmRepository;
        this.hallRepository = sql2oHallRepository;
    }
    private String getFilmName(int id) {
        Map<Integer, Film> filmsWithId = new HashMap<>();
        Collection<Film> films = filmRepository.findAll();
        for (Film film: films) {
            filmsWithId.put(film.getId(), film);
        }
        return filmsWithId.get(id).getName();
    }

    private String getHallName(int id) {
        Map<Integer, Hall> hallsWithId = new HashMap<>();
        Collection<Hall> halls = hallRepository.findAll();
        for (Hall hall: halls) {
            hallsWithId.put(hall.getId(), hall);
        }
        return hallsWithId.get(id).getName();
    }
    @Override
    public Collection<ScheduleDto> findAll() {
        Collection<ScheduleDto> result = new ArrayList<>();
        Collection<FilmSession> sessions = filmSessionRepository.findAll();
        for (FilmSession filmSession : sessions) {
            result.add(
                    new ScheduleDto(
                            filmSession.getId(),
                            getFilmName(filmSession.getFilmId()),
                            getHallName(filmSession.getHallId()),
                            filmSession.getStartTime(),
                            filmSession.getEndTime(),
                            filmSession.getPrice()
                    )
            );
        }
        return result;
    }
}
