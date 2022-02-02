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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SessionDto {

    private Long sessionId;
    private byte[] image;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
//    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;
    private Double price;
    private String title;
    private Long yearOfRelease;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionDto that = (SessionDto) o;
        return Objects.equals(sessionId, that.sessionId) && Arrays.equals(image, that.image) && Objects.equals(date, that.date) && Objects.equals(time, that.time) && Objects.equals(price, that.price) && Objects.equals(title, that.title) && Objects.equals(yearOfRelease, that.yearOfRelease);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(sessionId, date, time, price, title, yearOfRelease);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
