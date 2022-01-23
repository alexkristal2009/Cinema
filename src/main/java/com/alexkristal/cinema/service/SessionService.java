package com.alexkristal.cinema.service;

import com.alexkristal.cinema.dto.SessionDto;
import com.alexkristal.cinema.mapper.SessionMapper;
import com.alexkristal.cinema.model.Session;
import com.alexkristal.cinema.repository.MovieRepository;
import com.alexkristal.cinema.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionMapper sessionMapper;

    @Autowired
    private MovieRepository movieRepository;


    public SessionDto addNewSession(String movieTitle, Session session) {

        if (!movieRepository.existsMoviesByTitle(movieTitle)) {
            return null;
        }

        session.setSessionMovie(movieRepository.getMovieByTitle(movieTitle));

        return sessionMapper.mapSessionToSessionDto(sessionRepository.save(session));
    }

    public SessionDto getSessionById(Long id) {

        if (sessionRepository.existsSessionById(id)) {

            return sessionMapper.mapSessionToSessionDto(sessionRepository.getSessionById(id));
        }

        return null;
    }

    public List<SessionDto> getAllSessions() {

        List<Session> sessionsList = sessionRepository.findAll();
        List<SessionDto> sessionDtoList = new ArrayList<>();

        if (!sessionsList.isEmpty()) {
            for (Session s : sessionsList) {
                sessionDtoList.add(sessionMapper.mapSessionToSessionDto(s));
            }
        } else {
            return null;
        }
        return sessionDtoList;
    }

    public List<SessionDto> getAllSessionsByDate(LocalDate date){

        List<Session> sessionsList;
        List<SessionDto> sessionDtoList = new ArrayList<>();

        if (sessionRepository.existsSessionByDate(date)) {

            sessionsList = sessionRepository.getAllByDate(date);

            for (Session session : sessionsList) {

                if (session.getDate().equals(date)) {
                    sessionDtoList.add(sessionMapper.mapSessionToSessionDto(session));
                }

            }
        } else {
            return null;
        }
        return sessionDtoList;
    }

    public List<SessionDto> getAllSessionsByPrice(Double price) {

        List<Session> sessionsList;
        List<SessionDto> sessionDtoList = new ArrayList<>();

        if (sessionRepository.existsSessionByPrice(price)) {

            sessionsList = sessionRepository.getAllByPrice(price);

            for (Session session : sessionsList) {

                sessionDtoList.add(sessionMapper.mapSessionToSessionDto(session));
            }

        } else {
            return null;
        }
        return sessionDtoList;
    }

    public List<SessionDto> getAllSessionsByPriceLessThan(Double price){

        List<Session> sessionsList;
        List<SessionDto> sessionDtoList = new ArrayList<>();

        if (sessionRepository.existsSessionByPrice(price)) {

            sessionsList = sessionRepository.getAllByPriceLessThan(price);

            for (Session session : sessionsList) {

                sessionDtoList.add(sessionMapper.mapSessionToSessionDto(session));

            }
        } else {
            return null;
        }
        return sessionDtoList;
    }

    public List<SessionDto> getAllSessionsByPriceGreaterThan(Double price) {

        List<Session> sessionsList;
        List<SessionDto> sessionDtoList = new ArrayList<>();

        if (sessionRepository.existsSessionByPrice(price)) {

            sessionsList = sessionRepository.getAllByPriceGreaterThan(price);

            for (Session session : sessionsList) {

                sessionDtoList.add(sessionMapper.mapSessionToSessionDto(session));

            }
        } else {
            return null;
        }
        return sessionDtoList;
    }

    public List<SessionDto> getAllSessionsByMovieTitle(String movieTitle) {

        List<Session> sessionsList;
        List<SessionDto> sessionDtoList = new ArrayList<>();

        if (movieRepository.existsMoviesByTitle(movieTitle)) {

            sessionsList = sessionRepository.getAllBySessionMovie_Title(movieTitle);

            for (Session session : sessionsList) {

                sessionDtoList.add(sessionMapper.mapSessionToSessionDto(session));

            }
        } else {
            return null;
        }
        return sessionDtoList;
    }

    public String deleteAllSessions() {
        sessionRepository.deleteAll();
        return "All sessions deleted!";
    }

    public String deleteSessionById(Long id) {

        if (sessionRepository.existsSessionById(id)) {

            sessionRepository.deleteById(id);

            return String.format("Session with ID - %d deleted successfully!", id);

        } else {

            return String.format("There is no session with ID - %d.", id);

        }
    }

    public String deleteAllSessionsByMovieTitle(String movieTitle) {

        if (movieRepository.existsMoviesByTitle(movieTitle)) {

            sessionRepository.deleteAllBySessionMovie_Title(movieTitle);

            return String.format("All sessions with movie \"%s\" deleted", movieTitle);

        } else {

            return String.format("There are no sessions with movie \"%s\"", movieTitle);

        }
    }

    public String deleteAllSessionsByDate(LocalDate date) {

        if (!sessionRepository.existsSessionByDate(date)) {

            sessionRepository.deleteAllByDate(date);

            return String.format("All sessions \"%s\" deleted successfully", date);

        } else {

            return String.format("There is no session with date \"%s\"", date);

        }
    }

    public SessionDto updateSession(Long oldSessionId, Session newSession){

        if (sessionRepository.existsSessionById(oldSessionId)) {

            newSession.setId(oldSessionId);

            return sessionMapper.mapSessionToSessionDto(sessionRepository.save(newSession));

        }
        return null;
    }

}
