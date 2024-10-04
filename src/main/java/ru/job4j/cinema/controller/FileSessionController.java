package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.HallService;

@Controller
@RequestMapping("/schedule")
public class FileSessionController {
    private final FilmSessionService filmSessionService;
    private final FilmService filmService;
    private final HallService hallService;


    public FileSessionController(FilmSessionService filmSessionService, FilmService filmService, HallService hallService) {
        this.filmSessionService = filmSessionService;
        this.filmService = filmService;
        this.hallService = hallService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("schedule", filmSessionService.findAll());
        return "schedule/list";
    }

    @GetMapping("/{id}")
    public String getBuyPageFilmSession(Model model, @PathVariable int id) {
        var filmSessionOptional = filmSessionService.findById(id);
        if (filmSessionOptional.isEmpty()) {
            model.addAttribute("message", "Сессия с указанным идентификатором не найдена");
            return "errors/404";
        }
        FilmSessionDto filmSession = filmSessionOptional.get();
        model.addAttribute("filmSession", filmSession);
        model.addAttribute("hall", hallService.findById(filmSession.getHallId()));
        return "schedule/buy";
    }

    /*@PostMapping("/buy")
    public String buyTicket(@ModelAttribute FilmSessionDto, @RequestParam MultipartFile file, Model model) {
        try {

        }
    }*/

}
