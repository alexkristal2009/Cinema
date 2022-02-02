package com.alexkristal.cinema.controller;

import com.alexkristal.cinema.dto.SessionDto;
import com.alexkristal.cinema.model.Session;
import com.alexkristal.cinema.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/sessions/{movieTitle}")
    public SessionDto addNewSession(@PathVariable String movieTitle, @RequestBody Session session) {
        return sessionService.addNewSession(movieTitle, session);
    }

    @GetMapping("/sessions/{id}")
    public SessionDto getSessionById(@PathVariable Long id) {
        return sessionService.getSessionById(id);
    }

    @GetMapping("/sessions")
    public List<SessionDto> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @GetMapping("/sessions/{date}")
    public List<SessionDto> getAllSessionsByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return sessionService.getAllSessionsByDate(date);
    }

    @GetMapping("/sessions/{price}")
    public List<SessionDto> getAllSessionsByPrice(@PathVariable Double price) {
        return sessionService.getAllSessionsByPrice(price);
    }

    @GetMapping("/sessions/less/{price}")
    public List<SessionDto> getAllSessionsByPriceLessThan(@PathVariable Double price) {
        return sessionService.getAllSessionsByPriceLessThan(price);
    }

    @GetMapping("/sessions/greater/{price}")
    public List<SessionDto> getAllSessionsByPriceGreaterThan(@PathVariable Double price) {
        return sessionService.getAllSessionsByPriceGreaterThan(price);
    }

    @GetMapping("/sessions/{movieTitle}")
    public List<SessionDto> getAllSessionsByMovieTitle(@PathVariable String movieTitle) {
        return sessionService.getAllSessionsByMovieTitle(movieTitle);
    }

    @DeleteMapping("/sessions")
    public String deleteAllSessions() {
        return sessionService.deleteAllSessions();
    }

    @DeleteMapping("/sessions/{id}")
    public String deleteSessionById(@PathVariable Long id) {
        return sessionService.deleteSessionById(id);
    }

    @DeleteMapping("/sessions/{movieTitle}")
    public String deleteAllSessionsByMovieTitle(@PathVariable String movieTitle) {
        return sessionService.deleteAllSessionsByMovieTitle(movieTitle);
    }

    @DeleteMapping("/sessions/{date}")
    public String deleteAllSessionsByDate(@PathVariable LocalDate date) {
        return sessionService.deleteAllSessionsByDate(date);
    }

    @PutMapping("/sessions/{oldSessionId}")
    public SessionDto updateSession(@PathVariable Long oldSessionId, @RequestBody Session newSession) {
        return sessionService.updateSession(oldSessionId, newSession);
    }

}
