package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import org.sql2o.Connection;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.GenreRepository;

import java.util.*;
@Service
public class SimpleFilmService implements FilmService {
    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;
    public SimpleFilmService(FilmRepository sql2oFilmRepository,
                             GenreRepository sql2oGenreRepository) {
        this.filmRepository = sql2oFilmRepository;
        this.genreRepository = sql2oGenreRepository;
    }

    private String getGenreFilm(int id) {
        Map<Integer, Genre> genresWithId = new HashMap<>();
        Collection<Genre> genres = genreRepository.findAll();
        for (Genre genre : genres) {
            genresWithId.put(genre.getId(), genre);
        }
        return genresWithId.get(id).getName();
    }
    @Override
    public Collection<FilmDto> findAll() {
        Collection<FilmDto> result = new ArrayList<>();
        Collection<Film> films = filmRepository.findAll();
        for (Film film : films) {
            result.add(
                    new FilmDto(
                            film.getId(),
                            film.getName(),
                            film.getDescription(),
                            film.getYear(),
                            film.getMinimalAge(),
                            film.getDurationInMinutes(),
                            getGenreFilm(film.getGenreId())));
        }
        return result;
    }

    @Override
    public Optional<FilmDto> findById(int id) {
        var optionalFilm = filmRepository.findById(id);
        if (optionalFilm.isEmpty()) {
            return Optional.empty();
        }
        Film film = optionalFilm.get();
        return Optional.of(
                new FilmDto(
                        film.getId(),
                        film.getName(),
                        film.getDescription(),
                        film.getYear(),
                        film.getMinimalAge(),
                        film.getDurationInMinutes(),
                        getGenreFilm(film.getGenreId())));
    }
}
