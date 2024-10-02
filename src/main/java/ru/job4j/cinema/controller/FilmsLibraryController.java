package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.FilmService;

@Controller
@RequestMapping("/filmsLibrary")
public class FilmsLibraryController {
    private final FilmService filmService;

    public FilmsLibraryController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("filmsLibrary", filmService.findAll());
        return "filmsLibrary/list";
    }
}
