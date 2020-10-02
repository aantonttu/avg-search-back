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
                new Movie("Antlers", "Description", "Scott Cooper", 9.8f),
                new Movie("Candyman", "Description", "Nia DaCosta", 6.3f),
                new Movie("Ghostbusters: Afterlife", "Description", "Jason Reitman", 7.5f),
                new Movie("Halloween Kills", "Description", "David Gordon Green", 9.5f),
                new Movie("Morbius", "Description", "Daniel Espinosa", 7.9f),
                new Movie("Spiral", "Description", "Darren Lynn Bousman", 8.1f),
                new Movie("The Forever Purge", "Description", "Everardo Gout", 3.7f),
                new Movie("Wendell and Wild", "Description", "Henry Selick", 4.5f),
                new Movie("Halloween Ends", "Description", "David Gordon Green", 6.8f),
                new Movie("Dark Harvest", "Description", "David Slade", 9.1f),
                new Movie("Escape Room 2", "Description", "Adam Robitel", 9.7f)

        );
        movieRepository.saveAll(movies);
    }
}
