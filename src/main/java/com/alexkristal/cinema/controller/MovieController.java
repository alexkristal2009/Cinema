package com.alexkristal.cinema.controller;

import com.alexkristal.cinema.dto.MovieDto;
import com.alexkristal.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addNewMovie")
    public MovieDto addNewMovie(@RequestParam("image") MultipartFile image,
                                @RequestParam("title") String title,
                                @RequestParam("yearOfRelease") Long yearOfRelease,
                                @RequestParam("genre") String genre,
                                @RequestParam("description") String description) throws IOException {

        return movieService.addNewMovie(image, title, yearOfRelease, genre, description);
    }

    @GetMapping("/getAllMovies")
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/getMovieByMovieTitle/{movieTitle}")
    public MovieDto getMovieByMovieTitle(@PathVariable String movieTitle) {
        return movieService.getMovieByMovieTitle(movieTitle);
    }

    @GetMapping("/getAllMoviesByGenre/{genre}")
    public List<MovieDto> getAllMoviesByGenre(@PathVariable String genre) {
        return movieService.getAllMoviesByGenre(genre);
    }

    @GetMapping("/getAllMoviesByDate/{date}")
    public List<MovieDto> getAllMoviesByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return movieService.getAllMoviesByDate(date);
    }

    @PostMapping("/deleteAllMovies")
    public String deleteAllMovies() {
        return movieService.deleteAllMovies();
    }

    @PostMapping("/deleteMovieByMovieTitle/{movieTitle}")
    public String deleteMovieByMovieTitle(@PathVariable String movieTitle) {
        return movieService.deleteMovieByMovieTitle(movieTitle);
    }

    @PostMapping("/deleteAllMoviesByGenre/{genre}")
    public String deleteMoviesByGenre(@PathVariable String genre) {
        return movieService.deleteAllMoviesByGenre(genre);
    }

    @PostMapping("/updateMovieByMovieTitle/{oldMovieTitle}")
    public MovieDto updateMovieByMovieTitle(@PathVariable("oldMovieTitle") String oldMovieTitle,
                                            @RequestParam("newImage") MultipartFile newImage,
                                            @RequestParam("newTitle") String newTitle,
                                            @RequestParam("newYearOfRelease") Long newYearOfRelease,
                                            @RequestParam("newGenre") String newGenre,
                                            @RequestParam("newDescription") String newDescription) throws IOException {

        return movieService.updateMovieByMovieTitle(oldMovieTitle, newImage, newTitle, newYearOfRelease, newGenre, newDescription);
    }

}
