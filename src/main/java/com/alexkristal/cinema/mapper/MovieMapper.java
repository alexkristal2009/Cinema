package com.alexkristal.cinema.mapper;

import com.alexkristal.cinema.dto.MovieDto;
import com.alexkristal.cinema.model.Movie;
import lombok.Builder;
import org.springframework.stereotype.Component;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Builder
@Component
public class MovieMapper {

    public MovieDto mapMovieToMovieDto(Movie movie) {
        return MovieDto.builder()
                .image(movie.getImage())
                .title(movie.getTitle())
                .yearOfRelease(movie.getYearOfRelease())
                .genre(movie.getGenre())
                .description(movie.getDescription())
                .build();
    }

}
