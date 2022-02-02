package com.alexkristal.cinema.mapper;

import com.alexkristal.cinema.dto.SessionDto;
import com.alexkristal.cinema.model.Session;
import lombok.Builder;
import org.springframework.stereotype.Component;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Builder
@Component
public class SessionMapper {

    public SessionDto mapSessionToSessionDto(Session session) {
        return SessionDto.builder()
                .sessionId(session.getId())
                .image(session.getSessionMovie().getImage())
                .date(session.getDate())
                .time(session.getTime())
                .price(session.getPrice())
                .title(session.getSessionMovie().getTitle())
                .yearOfRelease(session.getSessionMovie().getYearOfRelease())
                .build();
    }

}
