package com.alexkristal.cinema.repository;

import com.alexkristal.cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsMoviesByTitle(String movieTitle);

    boolean existsMoviesByGenre(String genre);

    Movie getMovieByTitle(String movieTitle);

    List<Movie> getAllByGenre(String genre);

    void deleteMovieByTitle(String movieTitle);

    void deleteAllByGenre(String genre);
}
