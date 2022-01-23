package com.alexkristal.cinema.dto;

import lombok.*;

import java.util.Arrays;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieDto {

    private byte[] image;
    private String title;
    private Long yearOfRelease;
    private String genre;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDto movieDto = (MovieDto) o;
        return Arrays.equals(image, movieDto.image) && Objects.equals(title, movieDto.title) && Objects.equals(yearOfRelease, movieDto.yearOfRelease) && Objects.equals(genre, movieDto.genre) && Objects.equals(description, movieDto.description);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, yearOfRelease, genre, description);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
