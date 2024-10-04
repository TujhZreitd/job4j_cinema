package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.HallRepository;

import java.util.*;

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

    private String getHallNameWithDesc(int id) {
        Map<Integer, Hall> hallsWithId = new HashMap<>();
        Collection<Hall> halls = hallRepository.findAll();
        for (Hall hall: halls) {
            hallsWithId.put(hall.getId(), hall);
        }
        return hallsWithId.get(id).getName() + " " + "(" + hallsWithId.get(id).getDescription() + ")";
    }

    private String getDescFilm(int id) {
        Map<Integer, String> descFilmWithFilmId = new HashMap<>();
        Collection<Film> films = filmRepository.findAll();
        for (Film film: films) {
            descFilmWithFilmId.put(film.getId(), film.getDescription());
        }
        return descFilmWithFilmId.get(id);
    }

    private int getFileIdFilm(int id) {
        Map<Integer, Integer> fileIdWithFilmId = new HashMap<>();
        Collection<Film> films = filmRepository.findAll();
        for (Film film: films) {
            fileIdWithFilmId.put(film.getId(), film.getFileId());
        }
        return fileIdWithFilmId.get(id);
    }

    @Override
    public Collection<FilmSessionDto> findAll() {
        Collection<FilmSessionDto> result = new ArrayList<>();
        Collection<FilmSession> sessions = filmSessionRepository.findAll();
        for (FilmSession filmSession : sessions) {
            result.add(
                    new FilmSessionDto(
                            filmSession.getId(),
                            getFilmName(filmSession.getFilmId()),
                            getDescFilm(filmSession.getFilmId()),
                            getHallNameWithDesc(filmSession.getHallId()),
                            filmSession.getStartTime(),
                            filmSession.getEndTime(),
                            filmSession.getPrice(),
                            getFileIdFilm(filmSession.getFilmId()),
                            filmSession.getHallId()
                    )
            );
        }
        return result;
    }

    @Override
    public Optional<FilmSessionDto> findById(int id) {
        Optional<FilmSession> filmSessionOptional = filmSessionRepository.findById(id);
        if (filmSessionOptional.isPresent()) {
            FilmSession filmSession = filmSessionOptional.get();
            FilmSessionDto oneFilmSession = new FilmSessionDto(
                    filmSession.getId(),
                    getFilmName(filmSession.getFilmId()),
                    getDescFilm(filmSession.getFilmId()),
                    getHallNameWithDesc(filmSession.getHallId()),
                    filmSession.getStartTime(),
                    filmSession.getEndTime(),
                    filmSession.getPrice(),
                    getFileIdFilm(filmSession.getFilmId()),
                    filmSession.getHallId()
            );
            return Optional.of(oneFilmSession);
        }
        return Optional.empty();
    }
}
