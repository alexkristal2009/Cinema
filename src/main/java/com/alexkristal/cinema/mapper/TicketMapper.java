package com.alexkristal.cinema.mapper;

import com.alexkristal.cinema.dto.TicketDto;
import com.alexkristal.cinema.model.Ticket;
import lombok.Builder;
import org.springframework.stereotype.Component;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Builder
@Component
public class TicketMapper {

    public TicketDto mapTicketToTicketDto(Ticket ticket) {
        return TicketDto.builder()
                .ticketId(ticket.getId())
                .login(ticket.getTicketsUsers().getLogin())
                .seats(ticket.getSeats())
                .line(ticket.getLine())
                .image(ticket.getTicketSession().getSessionMovie().getImage())
                .title(ticket.getTicketSession().getSessionMovie().getTitle())
                .price(ticket.getTicketSession().getPrice())
                .date(ticket.getTicketSession().getDate())
                .time(ticket.getTicketSession().getTime())
                .build();
    }

}
