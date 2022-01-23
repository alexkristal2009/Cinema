package com.alexkristal.cinema.controller;

import com.alexkristal.cinema.dto.SessionDto;
import com.alexkristal.cinema.model.Session;
import com.alexkristal.cinema.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/addNewSession/{movieTitle}")
    public SessionDto addNewSession(@PathVariable String movieTitle, @RequestBody Session session) {
        return sessionService.addNewSession(movieTitle, session);
    }

    @GetMapping("/getSessionById/{id}")
    public SessionDto getSessionById(@PathVariable Long id) {
        return sessionService.getSessionById(id);
    }

    @GetMapping("/getAllSessions")
    public List<SessionDto> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @GetMapping("/getAllSessionsByDate/{date}")
    public List<SessionDto> getAllSessionsByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return sessionService.getAllSessionsByDate(date);
    }

    @GetMapping("/getAllSessionsByPrice/{price}")
    public List<SessionDto> getAllSessionsByPrice(@PathVariable Double price) {
        return sessionService.getAllSessionsByPrice(price);
    }

    @GetMapping("/getAllSessionsByPriceLessThan/{price}")
    public List<SessionDto> getAllSessionsByPriceLessThan(@PathVariable Double price) {
        return sessionService.getAllSessionsByPriceLessThan(price);
    }

    @GetMapping("/getAllSessionsByPriceGreaterThan/{price}")
    public List<SessionDto> getAllSessionsByPriceGreaterThan(@PathVariable Double price) {
        return sessionService.getAllSessionsByPriceGreaterThan(price);
    }

    @GetMapping("/getAllSessionsByMovieTitle/{movieTitle}")
    public List<SessionDto> getAllSessionsByMovieTitle(@PathVariable String movieTitle) {
        return sessionService.getAllSessionsByMovieTitle(movieTitle);
    }

    @PostMapping("/deleteAllSessions")
    public String deleteAllSessions() {
        return sessionService.deleteAllSessions();
    }

    @PostMapping("/deleteSessionById/{id}")
    public String deleteSessionById(@PathVariable Long id) {
        return sessionService.deleteSessionById(id);
    }

    @PostMapping("/deleteAllSessionsByMovieTitle/{movieTitle}")
    public String deleteAllSessionsByMovieTitle(@PathVariable String movieTitle) {
        return sessionService.deleteAllSessionsByMovieTitle(movieTitle);
    }

    @PostMapping("/deleteAllSessionsByDate/{date}")
    public String deleteAllSessionsByDate(@PathVariable LocalDate date) {
        return sessionService.deleteAllSessionsByDate(date);
    }

    @PostMapping("/updateSession/{oldSessionId}")
    public SessionDto updateSession(@PathVariable Long oldSessionId, @RequestBody Session newSession) {
        return sessionService.updateSession(oldSessionId, newSession);
    }

}
