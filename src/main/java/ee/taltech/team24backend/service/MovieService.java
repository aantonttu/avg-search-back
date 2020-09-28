package ee.taltech.team24backend.service;

import ee.taltech.team24backend.exceptions.InvalidMovieException;
import ee.taltech.team24backend.model.Movie;
import ee.taltech.team24backend.exceptions.MovieNotFoundException;
import ee.taltech.team24backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findByName(String name) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getName().toLowerCase().startsWith(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Movie findById(Long id) throws RuntimeException {
        return movieRepository.findById(id).orElseThrow(MovieNotFoundException::new);
    }

    public Movie save(Movie movie) {
        if (movie.getName() != null && movie.getDescription() != null && movie.getProducer() != null) {
            // save will generate id for object
            return movieRepository.save(movie);

        } else throw new InvalidMovieException("Movie is not added! Please input valid ");

    }

    public void delete(Long id) {
        Movie dbMovie = findById(id);
        movieRepository.delete(dbMovie);
    }


}
