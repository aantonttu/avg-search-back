package ee.taltech.team24backend.controller;

import ee.taltech.team24backend.dto.MovieDto;
import ee.taltech.team24backend.model.Movie;
import ee.taltech.team24backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("movies")
@RestController
public class MoviesController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieDto> getMovies() {
        return movieService.findAll();
    }


    @GetMapping("{id}")
    public Movie getMovie(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @GetMapping("find")
    public List<MovieDto> getMoviesByName(@RequestParam(value = "name") String name) {
        return movieService.findByName(name);
    }

    @GetMapping("sorted")
    public List<MovieDto> sortMovies(@RequestParam(value = "by", defaultValue = "name") String by,
                                     @RequestParam(value = "order", defaultValue = "asc") String order) {
        return movieService.sorting(by, order);
    }

    @GetMapping("genres")
    public List<MovieDto> getMoviesByGenres(@RequestParam(value = "genre") String genre) {
        return movieService.getMoviesByGenres(genre);
    }

    @GetMapping("allGenres")
    public List<String> getAllGenres() {
        return movieService.getAllGenres();
    }

//    @PostMapping
//    public MovieDto saveMovie(@RequestBody Movie movie) {
//        return movieService.save(movie);
//    }
//
//    @PutMapping("{id}")
//    public void editMovie(@RequestBody Movie newMovie, @PathVariable Long id) {
//        movieService.edit(newMovie, id);
//    }
//
//    @DeleteMapping("{id}")
//    public void deleteMovie(@PathVariable Long id) {
//        movieService.delete(id);
//    }
}
