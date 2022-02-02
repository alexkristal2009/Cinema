package com.alexkristal.cinema.service;

import com.alexkristal.cinema.dto.MovieDto;
import com.alexkristal.cinema.mapper.MovieMapper;
import com.alexkristal.cinema.model.Movie;
import com.alexkristal.cinema.model.Session;
import com.alexkristal.cinema.repository.MovieRepository;
import com.alexkristal.cinema.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */


@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private MovieMapper movieMapper;

    public MovieDto addNewMovie(MultipartFile image, String title, Long yearOfRelease,
                                String genre, String description) throws IOException {

        if (image == null
                && title.isBlank()
                && yearOfRelease < 1900
                && yearOfRelease > LocalDate.now().getYear() + 5
                && genre.isBlank()
                && description.isBlank()
        ){
            return null;
        }

        // проверка на есть ли уже фильм с таким названием
        if (movieRepository.existsMoviesByTitle(title)){
            return null;
        }

        Movie movie = new Movie();

        try {
            assert image != null;
            movie.setImage(image.getBytes());
            movie.setTitle(title);
            movie.setYearOfRelease(yearOfRelease);
            movie.setGenre(genre);
            movie.setDescription(description);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieMapper.mapMovieToMovieDto(movieRepository.save(movie));
    }

    public List<MovieDto> getAllMovies() {

        List<Movie> moviesList = movieRepository.findAll();
        List<MovieDto> moviesDtoList = new ArrayList<>();

        if (!moviesList.isEmpty()) {

            for (Movie movie : moviesList) {

                moviesDtoList.add(movieMapper.mapMovieToMovieDto(movie));

            }
        } else {
            return null;
        }
        return moviesDtoList;
    }

    public MovieDto getMovieByMovieTitle(String movieTitle) {
        return movieMapper.mapMovieToMovieDto(movieRepository.getMovieByTitle(movieTitle));
    }

    public List<MovieDto> getAllMoviesByGenre(String genre) {

        if (!movieRepository.existsMoviesByGenre(genre)) {
            return null;
        }

        List<Movie> moviesList = movieRepository.getAllByGenre(genre);

        if (moviesList.isEmpty()) {
            return null;
        }

        List<MovieDto> moviesDtoList = new ArrayList<>();


        for (Movie movie : moviesList) {

            moviesDtoList.add(movieMapper.mapMovieToMovieDto(movie));

        }
        return moviesDtoList;
    }

    public List<MovieDto> getAllMoviesByDate(LocalDate date) {

        if (!sessionRepository.existsSessionByDate(date)) {
            return null;
        }

        List<Session> sessionList = sessionRepository.getAllByDate(date);
        List<MovieDto> movieDtoList = new ArrayList<>();

        for (Session session : sessionList) {
            movieDtoList.add(movieMapper.mapMovieToMovieDto(session.getSessionMovie()));
        }

        return movieDtoList;
    }

    public String deleteAllMovies() {
        movieRepository.deleteAll();
        return "All movies deleted!";
    }

    public String deleteMovieByMovieTitle(String movieTitle) {

        if (movieRepository.existsMoviesByTitle(movieTitle)) {

            movieRepository.deleteMovieByTitle(movieTitle);

            return String.format("Movie \"%s\" deleted successfully!", movieTitle);

        } else {

            return String.format("There is no movie \"%s\"", movieTitle);

        }
    }

    public String deleteAllMoviesByGenre(String genre) {

        if (movieRepository.existsMoviesByGenre(genre)) {

            movieRepository.deleteAllByGenre(genre);

            return String.format("Movies with genre \"%s\" deleted", genre);

        } else {
            return String.format("There are no movie with genre \"%s\"", genre);
        }
    }

    public MovieDto updateMovieByMovieTitle(String oldMovieTitle, MultipartFile newImage, String newTitle,
                                            Long newYearOfRelease, String newGenre, String newDescription) throws IOException {

        if (oldMovieTitle.isBlank()
                && newImage == null
                && newTitle.isBlank()
                && newYearOfRelease < 1900
                && newYearOfRelease > LocalDate.now().getYear() + 5
                && newGenre.isBlank()
                && newDescription.isBlank()
        ){
            return null;
        }

        if (!movieRepository.existsMoviesByTitle(oldMovieTitle)) {
            return null;
        }


        Movie oldMovie = movieRepository.getMovieByTitle(oldMovieTitle);

        Movie newMovie = new Movie();

        newMovie.setId(oldMovie.getId());
        assert newImage != null;
        newMovie.setImage(newImage.getBytes());
        newMovie.setTitle(newTitle);
        newMovie.setYearOfRelease(newYearOfRelease);
        newMovie.setGenre(newGenre);
        newMovie.setDescription(newDescription);

        return movieMapper.mapMovieToMovieDto(movieRepository.save(newMovie));
    }

}
