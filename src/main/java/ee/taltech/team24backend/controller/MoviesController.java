package ee.taltech.team24backend.controller;

import ee.taltech.team24backend.dto.CommentDto;
import ee.taltech.team24backend.dto.MovieDto;
import ee.taltech.team24backend.security.Roles;
import ee.taltech.team24backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping({"movies", "movies2"})
@RestController
public class MoviesController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieDto> getMovies(@RequestParam(value = "by", required = false) String by,
                                    @RequestParam(value = "order", required = false) String order,
                                    @RequestParam(value = "genre", required = false) String genre,
                                    @RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            if (!name.isBlank()) {
                return movieService.findByName(name);
            }
        } else if (genre != null) {
            return movieService.getMoviesByGenres(genre);
        } else if (by != null) {
            return movieService.sorting(by, order);
        }
        return movieService.findAll();
    }

    @GetMapping("{id}")
    public MovieDto getMovie(@PathVariable Long id) {
        return movieService.convertMovie(movieService.findById(id));
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

    @Secured(Roles.USER)
    @PostMapping("{id}/comments")
    public MovieDto saveComment(@PathVariable Long id, @RequestBody CommentDto comment) {
        return movieService.saveComment(id, comment);
    }
}
