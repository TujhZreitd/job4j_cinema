package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.sql2o.Sql2oException;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.service.TicketService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/schedule")
public class FileSessionController {
    private final FilmSessionService filmSessionService;
    private final FilmService filmService;
    private final HallService hallService;
    private final TicketService ticketService;


    public FileSessionController(FilmSessionService filmSessionService,
                                 FilmService filmService,
                                 HallService hallService,
                                 TicketService ticketService) {
        this.filmSessionService = filmSessionService;
        this.filmService = filmService;
        this.hallService = hallService;
        this.ticketService = ticketService;
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

    @PostMapping("/buy")
    public String buyTicket(@ModelAttribute FilmSessionDto filmSessionDto, Model model, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return "schedule/ticket";
            }
            int userId = user.getId();
            ticketService.save(new Ticket(filmSessionDto.getId(), filmSessionDto.getRow(), filmSessionDto.getPlace(), userId));
            model.addAttribute("filmSession", filmSessionDto);
            return "schedule/ticket";
        } catch (Sql2oException e) {
            model.addAttribute("message", "Не удалось приобрести билет на выбранное место, вероятно оно уже занято. Перейдите на страницу покупки билетов и попробуйте снова.");
            return "errors/404";
        }
    }

    /*@GetMapping("/ticket")
    public String getTicket(Model model) {

        return "schedule/ticket";
    }*/

}
