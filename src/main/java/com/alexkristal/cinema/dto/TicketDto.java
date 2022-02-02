package com.alexkristal.cinema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketDto {

    private Long ticketId;
    private String login; // это уникальное поле чтобы опознать юзера
    private Long seats;
    private Long line;
    private byte[] image;
    private String title;
    private Double price;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
//    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDto ticketDto = (TicketDto) o;
        return Objects.equals(ticketId, ticketDto.ticketId) && Objects.equals(login, ticketDto.login) && Objects.equals(seats, ticketDto.seats) && Objects.equals(line, ticketDto.line) && Arrays.equals(image, ticketDto.image) && Objects.equals(title, ticketDto.title) && Objects.equals(price, ticketDto.price) && Objects.equals(date, ticketDto.date) && Objects.equals(time, ticketDto.time);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ticketId, login, seats, line, title, price, date, time);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
