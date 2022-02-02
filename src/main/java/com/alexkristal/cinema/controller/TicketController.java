package com.alexkristal.cinema.controller;

import com.alexkristal.cinema.dto.TicketAndSessionDto;
import com.alexkristal.cinema.dto.TicketDto;
import com.alexkristal.cinema.model.Ticket;
import com.alexkristal.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/tickets/{sessionId}")
    public TicketAndSessionDto addNewTicket(@RequestBody Ticket ticket, @PathVariable Long sessionId) {
        return ticketService.addNewTicket(ticket, sessionId);
    }

    @PutMapping("/tickets/{userLogin}")
    public TicketDto addUserToTicket(@RequestBody Ticket ticket, @PathVariable String userLogin) {
        return ticketService.addUserToTicket(ticket, userLogin);
    }

    @GetMapping("/tickets/sessions")
    public List<TicketAndSessionDto> getAllTicketsWithSession() {
        return ticketService.getAllTicketsWithSession();
    }

    @GetMapping("/tickets/users")
    public List<TicketDto> getAllTicketsWithUser() {
        return ticketService.getAllTicketsWithUser();
    }

    @GetMapping("/tickets/sessions/{id}")
    public TicketAndSessionDto getTicketWithSessionByTicketId (@PathVariable Long id) {
        return ticketService.getTicketWithSessionByTicketId(id);
    }

    @GetMapping("/tickets/users/{id}")
    public TicketDto getTicketWithUserByTicketId (@PathVariable Long id) {
        return ticketService.getTicketWithUserByTicketId(id);
    }

    @GetMapping("/tickets/sessions/sessions/{id}")
    public List<TicketAndSessionDto> getAllTicketsWithSessionBySessionId(@PathVariable Long id) {
        return ticketService.getAllTicketsWithSessionBySessionId(id);
    }

    @GetMapping("/tickets/users/sessions/{id}")
    public List<TicketDto> getAllTicketsWithUserBySessionId(@PathVariable Long id) {
        return ticketService.getAllTicketsWithUserBySessionId(id);
    }

    @GetMapping("/tickets/users/users/{login}")
    public List<TicketDto> getAllTicketsWithUserByUserLogin(@PathVariable String login) {
        return ticketService.getAllTicketsWithUserByUserLogin(login);
    }

    @GetMapping("/tickets/sessions/{movieTitle}")
    public List<TicketAndSessionDto> getAllTicketsWithSessionByMovieTitle(@PathVariable String movieTitle) {
        return ticketService.getAllTicketsWithSessionByMovieTitle(movieTitle);
    }

    @GetMapping("/tickets/sessions/{date}")
    public List<TicketAndSessionDto> getAllTicketsWithSessionByDate(@PathVariable LocalDate date) {
        return ticketService.getAllTicketsWithSessionByDate(date);
    }

    @DeleteMapping("/tickets")
    public String deleteAllTickets() {
        return ticketService.deleteAllTickets();
    }

    @DeleteMapping ("/tickets/{id}")
    public String deleteTicketById (@PathVariable Long id) {
        return ticketService.deleteTicketById(id);
    }

    @DeleteMapping ("/tickets/users/{login}")
    public String deleteAllTicketsByUserLogin (@PathVariable String login) {
        return ticketService.deleteAllTicketsByUserLogin(login);
    }

    @DeleteMapping ("/tickets/sessions/{id}")
    public String deleteAllTicketsBySessionId (@PathVariable Long id) {
        return ticketService.deleteAllTicketsBySessionId(id);
    }

    @DeleteMapping ("/tickets/movies/{movieTitle}")
    public String deleteAllTicketsByMovieTitle (@PathVariable String movieTitle) {
        return ticketService.deleteAllTicketsByMovieTitle(movieTitle);
    }

    @PutMapping("/tickets/{id}")
    public TicketDto updateTicketById(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ticketService.updateTicketById(id, ticket);
    }

}
