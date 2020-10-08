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
                new Movie("Harry Potter 1", "Film about 1111", "Evelin Halling1111", 9.8f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller"),
                new Movie("Harry Potter 2", "Film about 2222", "Evelin Halling2222", 9.5f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller"),
                new Movie("Harry Potter 3", "Film about 3333", "Evelin Halling3333", 9.1f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller"),
                new Movie("Harry Potter 4", "Film about 4444", "Evelin Halling4444", 9.7f, "https://cs7.pikabu.ru/post_img/big/2019/05/23/9/1558626426166757449.jpg", "thriller")

        );
        movieRepository.saveAll(movies);
    }
}
