package com.alexkristal.cinema.service;

import com.alexkristal.cinema.dto.TicketAndSessionDto;
import com.alexkristal.cinema.dto.TicketDto;
import com.alexkristal.cinema.mapper.TicketAndSessionMapper;
import com.alexkristal.cinema.mapper.TicketMapper;
import com.alexkristal.cinema.model.Session;
import com.alexkristal.cinema.model.Ticket;
import com.alexkristal.cinema.model.User;
import com.alexkristal.cinema.repository.MovieRepository;
import com.alexkristal.cinema.repository.SessionRepository;
import com.alexkristal.cinema.repository.TicketRepository;
import com.alexkristal.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketAndSessionMapper ticketAndSessionMapper;

    public TicketAndSessionDto addNewTicket(Ticket ticket, Long sessionId) {

        if (!sessionRepository.existsSessionById(sessionId)){
            return null;
        }

        ticket.setTicketSession(sessionRepository.getSessionById(sessionId));

        return ticketAndSessionMapper.mapTicketToTicketAndSessionDto(ticketRepository.save(ticket), sessionRepository.getSessionById(sessionId));
    }

    public TicketDto addUserToTicket(Ticket ticket, String userLogin) {

        if (!userRepository.existsByLogin(userLogin)){
            return null;
        }

        ticket.setTicketsUsers(userRepository.getUserByLogin(userLogin));

        return ticketMapper.mapTicketToTicketDto(ticketRepository.save(ticket));
    }

    public List<TicketAndSessionDto> getAllTicketsWithSession () {
        List<Ticket> ticketList = ticketRepository.findAll();
        List<TicketAndSessionDto> ticketAndSessionDtoList = new ArrayList<>();

        if (!ticketList.isEmpty()) {
            for (Ticket ticket : ticketList) {
                Session session = ticket.getTicketSession();
                ticketAndSessionDtoList.add(ticketAndSessionMapper.mapTicketToTicketAndSessionDto(ticket, session));
            }
        } else {
            return null;
        }
        return ticketAndSessionDtoList;
    }

    public List<TicketDto> getAllTicketsWithUser () {
        List<Ticket> ticketsList = ticketRepository.findAll();
        List<TicketDto> ticketsDtoList = new ArrayList<>();

        if (!ticketsList.isEmpty()){
            for (Ticket ticket : ticketsList) {
                ticketsDtoList.add(ticketMapper.mapTicketToTicketDto(ticket));
            }
        }else {
            return null;
        }
        return ticketsDtoList;
    }

    public TicketAndSessionDto getTicketWithSessionByTicketId(Long id) {

        if (!ticketRepository.existsTicketById(id)){
            return null;
        }

        return  ticketAndSessionMapper.mapTicketToTicketAndSessionDto(
                ticketRepository.getById(id),
                ticketRepository.getById(id).getTicketSession());
    }

    public TicketDto getTicketWithUserByTicketId(Long id) {

        if (!ticketRepository.existsTicketById(id)){
            return null;
        }

        return ticketMapper.mapTicketToTicketDto(ticketRepository.getById(id));
    }

    public List<TicketAndSessionDto> getAllTicketsWithSessionBySessionId(Long id) {

        if (!sessionRepository.existsSessionById(id)){
            return null;
        }

        List<Ticket> ticketList = ticketRepository.getAllByTicketSession_Id(id);
        List<TicketAndSessionDto> ticketAndSessionDtoList = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            ticketAndSessionDtoList.add(
                    ticketAndSessionMapper.mapTicketToTicketAndSessionDto(
                            ticket, ticket.getTicketSession()));
        }

        return  ticketAndSessionDtoList;
    }

    public List<TicketDto> getAllTicketsWithUserBySessionId(Long id) {

        if (!sessionRepository.existsSessionById(id)){
            return null;
        }

        List<Ticket> ticketList = ticketRepository.getAllByTicketSession_Id(id);
        List<TicketDto> ticketDtoList = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            ticketDtoList.add(
                    ticketMapper.mapTicketToTicketDto(ticket));
        }

        return  ticketDtoList;
    }

    public List<TicketDto> getAllTicketsWithUserByUserLogin(String login) {

        if (!userRepository.existsByLogin(login)){
            return null;
        }

        List<Ticket> ticketList = ticketRepository.getAllByTicketsUsers_Login(login);
        List<TicketDto> ticketDtoList = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            ticketDtoList.add(
                    ticketMapper.mapTicketToTicketDto(ticket));
        }

        return  ticketDtoList;
    }

    public List<TicketAndSessionDto> getAllTicketsWithSessionByMovieTitle(String movieTitle) {

        if (!movieRepository.existsMoviesByTitle(movieTitle)){
            return null;
        }

        List<Ticket> ticketList = ticketRepository.getAllByTicketSession_SessionMovie_Title(movieTitle);
        List<TicketAndSessionDto> ticketAndSessionDtoList = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            ticketAndSessionDtoList.add(
                    ticketAndSessionMapper.mapTicketToTicketAndSessionDto(
                            ticket, ticket.getTicketSession()));
        }

        return  ticketAndSessionDtoList;
    }

    public List<TicketAndSessionDto> getAllTicketsWithSessionByDate(LocalDate date) {

        if (!sessionRepository.existsSessionByDate(date)){
            return null;
        }

        List<Ticket> ticketsList = ticketRepository.getAllByTicketSession_Date(date);
        List<Session> sessionsList = sessionRepository.getAllByDate(date);
        List<TicketAndSessionDto> ticketsAndSessionDtoList = new ArrayList<>();

        for (Ticket ticket : ticketsList) {

            for (Session session : sessionsList) {
                ticketsAndSessionDtoList.add(ticketAndSessionMapper.mapTicketToTicketAndSessionDto(ticket, session));
            }
        }
        return ticketsAndSessionDtoList;
    }

    public String deleteAllTickets() {
        ticketRepository.deleteAll();
        return "All tickets deleted!";
    }

    public String deleteTicketById (Long id) {
        if (ticketRepository.existsTicketById(id)) {
            ticketRepository.deleteById(id);
            return String.format("Ticket with ID = %d deleted successfully!", id);
        } else {
            return String.format("There is no ticket with ID = %d!", id);
        }
    }

    public String deleteAllTicketsByUserLogin(String login) {
        if (userRepository.existsByLogin(login)) {
            ticketRepository.deleteAllByTicketsUsers_Login(login);
            return String.format("All tickets for user \"%s\" deleted successfully!", login);
        } else {
            return String.format("User with login \"%s\" does not exist!", login);
        }
    }

    public String deleteAllTicketsBySessionId(Long id) {
        if (sessionRepository.existsSessionById(id)) {
            ticketRepository.deleteAllByTicketSession_Id(id);
            return String.format("All tickets for session %d deleted successfully!", id);
        } else {
            return String.format("Session %d does not exist!", id);
        }
    }

    public String deleteAllTicketsByMovieTitle(String movieTitle) {
        if (movieRepository.existsMoviesByTitle(movieTitle)) {
            ticketRepository.deleteAllByTicketSession_SessionMovie_Title(movieTitle);
            return String.format("All tickets for movie \"%s\" deleted successfully!", movieTitle);
        } else {
            return String.format("Movie \"%s\" does not exist!", movieTitle);
        }
    }

    public TicketDto updateTicketById(Long oldId, Ticket ticket) {

        if (ticketRepository.existsTicketById(oldId)) {
            ticket.setId(oldId);
            return ticketMapper.mapTicketToTicketDto(ticketRepository.save(ticket));
        }

        return null;
    }

}
