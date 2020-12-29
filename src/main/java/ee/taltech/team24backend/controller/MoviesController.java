package ee.taltech.team24backend.controller;

import ee.taltech.team24backend.dto.MovieDto;
import ee.taltech.team24backend.security.Roles;
import ee.taltech.team24backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    public MovieDto getMovie(@PathVariable Long id) {
        return movieService.convertMovie(movieService.findById(id));
    }

    @GetMapping("find")
    public List<MovieDto> getMoviesByName(@RequestParam(value = "name") String name) {
        return movieService.findByName(name);
    }

    @GetMapping("sorted")
    public List<MovieDto> sortMovies(@RequestParam(value = "by", defaultValue = "name") String by,
                                     @RequestParam(value = "order", defaultValue = "asc") String order,
                                     @RequestParam(value = "genre", required = false) String genre) {
        if (genre != null) return movieService.getMoviesByGenres(genre);
        return movieService.sorting(by, order);
    }

    @GetMapping("genres")
    public List<String> getAllGenres() {
        return movieService.getAllGenres();
    }

    @Secured(Roles.ADMIN)
    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}
