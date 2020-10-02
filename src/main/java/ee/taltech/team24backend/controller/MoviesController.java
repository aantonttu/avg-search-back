package ee.taltech.team24backend.controller;

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
    public List<Movie> getMovies() {
        return movieService.findAll();
    }

    @GetMapping("{id}")
    public Movie getMovie(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @GetMapping("find/{name}")
    public List<Movie> getMoviesByName(@PathVariable String name) {
        return movieService.findByName(name);
    }

    @GetMapping("rated")
    public List<Movie> getTopRatedMovies(){
        return movieService.getTopRated();
    }

    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping("{id}")
    public void editMovie(@RequestBody Movie newMovie, @PathVariable Long id){
        movieService.edit(newMovie, id);
    }

    @DeleteMapping("{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
    }

}
