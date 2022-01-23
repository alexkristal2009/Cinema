package com.alexkristal.cinema.controller;

import com.alexkristal.cinema.dto.TicketAndSessionDto;
import com.alexkristal.cinema.dto.TicketDto;
import com.alexkristal.cinema.model.Ticket;
import com.alexkristal.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/addNewTicket/{sessionId}")
    public TicketAndSessionDto addNewTicket(@RequestBody Ticket ticket, @PathVariable Long sessionId) {
        return ticketService.addNewTicket(ticket, sessionId);
    }

    @PostMapping("/addUserToTicket/{userLogin}")
    public TicketDto addUserToTicket(@RequestBody Ticket ticket, @PathVariable String userLogin) {
        return ticketService.addUserToTicket(ticket, userLogin);
    }

    @GetMapping("/getAllTicketsWithSession")
    public List<TicketAndSessionDto> getAllTicketsWithSession() {
        return ticketService.getAllTicketsWithSession();
    }

    @GetMapping("/getAllTicketsWithUser")
    public List<TicketDto> getAllTicketsWithUser() {
        return ticketService.getAllTicketsWithUser();
    }

    @GetMapping("/getTicketWithSessionByTicketId/{id}")
    public TicketAndSessionDto getTicketWithSessionByTicketId (@PathVariable Long id) {
        return ticketService.getTicketWithSessionByTicketId(id);
    }

    @GetMapping("/getTicketWithUserByTicketId/{id}")
    public TicketDto getTicketWithUserByTicketId (@PathVariable Long id) {
        return ticketService.getTicketWithUserByTicketId(id);
    }

    @GetMapping("/getAllTicketsWithSessionBySessionId/{id}")
    public List<TicketAndSessionDto> getAllTicketsWithSessionBySessionId(@PathVariable Long id) {
        return ticketService.getAllTicketsWithSessionBySessionId(id);
    }

    @GetMapping("/getAllTicketsWithUserBySessionId/{id}")
    public List<TicketDto> getAllTicketsWithUserBySessionId(@PathVariable Long id) {
        return ticketService.getAllTicketsWithUserBySessionId(id);
    }

    @GetMapping("/getAllTicketsWithUserByUserLogin/{login}")
    public List<TicketDto> getAllTicketsWithUserByUserLogin(@PathVariable String login) {
        return ticketService.getAllTicketsWithUserByUserLogin(login);
    }

    @GetMapping("/getAllTicketsWithSessionByMovieTitle/{movieTitle}")
    public List<TicketAndSessionDto> getAllTicketsWithSessionByMovieTitle(@PathVariable String movieTitle) {
        return ticketService.getAllTicketsWithSessionByMovieTitle(movieTitle);
    }

    @GetMapping("/getAllTicketsByDate/{date}")
    public List<TicketAndSessionDto> getAllTicketsWithSessionByDate(@PathVariable LocalDate date) {
        return ticketService.getAllTicketsWithSessionByDate(date);
    }

    @PostMapping("/deleteAllTickets")
    public String deleteAllTickets() {
        return ticketService.deleteAllTickets();
    }

    @PostMapping ("/deleteTicketById/{id}")
    public String deleteTicketById (@PathVariable Long id) {
        return ticketService.deleteTicketById(id);
    }

    @PostMapping ("/deleteAllTicketsByUserLogin/{login}")
    public String deleteAllTicketsByUserLogin (@PathVariable String login) {
        return ticketService.deleteAllTicketsByUserLogin(login);
    }

    @PostMapping ("/deleteAllTicketsBySessionId/{id}")
    public String deleteAllTicketsBySessionId (@PathVariable Long id) {
        return ticketService.deleteAllTicketsBySessionId(id);
    }

    @PostMapping ("/deleteAllTicketsByMovieTitle/{movieTitle}")
    public String deleteAllTicketsByMovieTitle (@PathVariable String movieTitle) {
        return ticketService.deleteAllTicketsByMovieTitle(movieTitle);
    }


    @PostMapping("/updateTicketById/{id}")
    public TicketDto updateTicketById(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ticketService.updateTicketById(id, ticket);
    }

}
