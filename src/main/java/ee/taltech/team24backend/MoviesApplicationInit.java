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
                new Movie("Antlers", "Description", "Scott Cooper", 9.8f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller"),
                new Movie("Candyman", "Description", "Nia DaCosta", 6.3f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller"),
                new Movie("Ghostbusters: Afterlife", "Description", "Jason Reitman", 7.5f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller"),
                new Movie("Halloween Kills", "Description", "David Gordon Green", 9.5f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller"),
                new Movie("Morbius", "Description", "Daniel Espinosa", 7.9f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller"),
                new Movie("Spiral", "Description", "Darren Lynn Bousman", 8.1f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller"),
                new Movie("The Forever Purge", "Description", "Everardo Gout", 3.7f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller"),
                new Movie("Wendell and Wild", "Description", "Henry Selick", 4.5f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller"),
                new Movie("Halloween Ends", "Description", "David Gordon Green", 6.8f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller"),
                new Movie("Dark Harvest", "Description", "David Slade", 9.1f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller"),
                new Movie("Escape Room 21", "Description", "Adam Robitel", 7f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "comedy"),
                new Movie("Escape Room 22", "Description", "Adam Robitel", 9.1f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "comedy"),
                new Movie("Escape Room 23", "Description", "Adam Robitel", 9.4f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "comedy"),
                new Movie("Escape Room 24", "Description", "Adam Robitel", 9.2f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "comedy"),
                new Movie("Escape Room 25", "Description", "Adam Robitel", 9.7f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "comedy"),
                new Movie("Escape Room 26", "Description", "Adam Robitel", 9.0f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "comedy"),
                new Movie("Escape Room 26", "Description", "Adam sitel", 9f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "comedy")
        );
        movieRepository.saveAll(movies);
    }
}
