package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.FilmSessionService;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    private final FilmSessionService filmSessionService;
    private final FilmService filmService;

    public ScheduleController(FilmSessionService filmSessionService, FilmService filmService) {
        this.filmSessionService = filmSessionService;
        this.filmService = filmService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("schedule", filmSessionService.findAll());
        return "schedule/list";
    }
}
