package com.alexkristal.cinema.mapper;

import com.alexkristal.cinema.dto.TicketAndSessionDto;
import com.alexkristal.cinema.model.Session;
import com.alexkristal.cinema.model.Ticket;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@Component
public class TicketAndSessionMapper {

    public TicketAndSessionDto mapTicketToTicketAndSessionDto(Ticket ticket, Session session){
        return TicketAndSessionDto.builder()
                .ticketId(ticket.getId())
                .seats(ticket.getSeats())
                .line(ticket.getLine())
                .image(session.getSessionMovie().getImage())
                .title(session.getSessionMovie().getTitle())
                .price(session.getPrice())
                .date(session.getDate())
                .time(session.getTime())
                .build();
    }

}
