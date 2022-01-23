package com.alexkristal.cinema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketAndSessionDto {

    private Long ticketId;
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
        TicketAndSessionDto that = (TicketAndSessionDto) o;
        return Objects.equals(ticketId, that.ticketId) && Objects.equals(seats, that.seats) && Objects.equals(line, that.line) && Arrays.equals(image, that.image) && Objects.equals(title, that.title) && Objects.equals(price, that.price) && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ticketId, seats, line, title, price, date, time);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
