package com.alexkristal.cinema.repository;

import com.alexkristal.cinema.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    boolean existsTicketById(Long id);

    List<Ticket> getAllByTicketsUsers_Login(String login);

    List<Ticket> getAllByTicketSession_SessionMovie_Title(String movieTitle);

    List<Ticket> getAllByTicketSession_Id(Long id);

    List<Ticket> getAllByTicketSession_Date(LocalDate date);

    void deleteAllByTicketsUsers_Login(String userLogin);

    void deleteAllByTicketSession_Id(Long id);

    void deleteAllByTicketSession_SessionMovie_Title(String movieTitle);
}
