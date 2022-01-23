package com.alexkristal.cinema.model;

import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image")
    @Lob
    private byte[] image;

    @Column(name = "title")
    private String title;

    @Column(name = "year_of_release")
    private Long yearOfRelease;

    @Column(name = "genre")
    private String genre;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY, mappedBy = "sessionMovie")
    private Set<Session> movieSession;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Arrays.equals(image, movie.image) && Objects.equals(title, movie.title) && Objects.equals(yearOfRelease, movie.yearOfRelease) && Objects.equals(genre, movie.genre) && Objects.equals(description, movie.description) && Objects.equals(movieSession, movie.movieSession);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, yearOfRelease, genre, description, movieSession);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", movieSession=" + movieSession +
                '}';
    }
}
