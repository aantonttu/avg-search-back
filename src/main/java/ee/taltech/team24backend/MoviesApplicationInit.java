package ee.taltech.team24backend;

import ee.taltech.team24backend.model.Movie;
import ee.taltech.team24backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MoviesApplicationInit implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Movie> movies = List.of(
                new Movie("Harry Potter 1", "Film about 1111", "Evelin Halling1111", 9.8f),
                new Movie("Harry Potter 2", "Film about 2222", "Evelin Halling2222", 9.5f),
                new Movie("Harry Potter 3", "Film about 3333", "Evelin Halling3333", 9.7f)

        );
        movieRepository.saveAll(movies);
    }
}
