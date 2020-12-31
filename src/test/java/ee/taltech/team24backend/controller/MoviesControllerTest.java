package ee.taltech.team24backend.controller;

import ee.taltech.team24backend.dto.MovieDto;
import ee.taltech.team24backend.model.Comment;
import ee.taltech.team24backend.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MoviesControllerTest {

    public static final ParameterizedTypeReference<List<MovieDto>> LIST_OF_MOVIES = new ParameterizedTypeReference<>() {
    };
    public static final ParameterizedTypeReference<List<String>> LIST_OF_GENRES = new ParameterizedTypeReference<>() {
    };
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void one_can_query_movies() {
        ResponseEntity<List<MovieDto>> exchange = testRestTemplate.exchange("/movies", HttpMethod.GET, null, LIST_OF_MOVIES);
        List<MovieDto> movies = assertOk(exchange);
        assertFalse(movies.isEmpty());
    }

    @Test
    void one_can_query_for_1_movie() {
        ResponseEntity<MovieDto> exchange = testRestTemplate.exchange("/movies/" + 1,
                HttpMethod.GET, null, MovieDto.class);
        MovieDto movie = assertOk(exchange);
        assertEquals(movie.getId(), 1);
    }

    @Test
    void search_test() {
        ResponseEntity<MovieDto> exchange = testRestTemplate.exchange("/movies/" + 1,
                HttpMethod.GET, null, MovieDto.class);
        MovieDto movie = assertOk(exchange);
        ResponseEntity<List<MovieDto>> exchange_search = testRestTemplate.exchange("/movies?name=" + movie.getName(),
                HttpMethod.GET, null, LIST_OF_MOVIES);
        MovieDto movie_from_search = assertOk(exchange_search).get(0);
        assertEquals(movie.getId(), movie_from_search.getId());
    }


    @Test
    void sorting_relevance_test() {
        //relevance
        ResponseEntity<List<MovieDto>> exchange = testRestTemplate.exchange("/movies?by=relevance&order=desc", HttpMethod.GET, null, LIST_OF_MOVIES);
        List<MovieDto> movies = assertOk(exchange);
        assertEquals(movies.get(0).getId(), 1);
        MovieDto lastAddedMovie = movies.get(movies.size() - 1);

        ResponseEntity<List<MovieDto>> exchange1 = testRestTemplate.exchange("/movies?by=relevance&order=asc", HttpMethod.GET, null, LIST_OF_MOVIES);
        List<MovieDto> movies1 = assertOk(exchange1);
        assertEquals(movies1.get(0).getId(), lastAddedMovie.getId());
    }

    @Test
    void sorting_rating_test() {
        //rating
        ResponseEntity<List<MovieDto>> exchange = testRestTemplate.exchange("/movies?by=rating&order=desc", HttpMethod.GET, null, LIST_OF_MOVIES);
        List<MovieDto> movies = assertOk(exchange);
        MovieDto bestRating = movies.get(0);
        MovieDto worstRating = movies.get(movies.size() - 1);

        ResponseEntity<List<MovieDto>> exchange1 = testRestTemplate.exchange("/movies?by=rating&order=asc", HttpMethod.GET, null, LIST_OF_MOVIES);
        List<MovieDto> movies1 = assertOk(exchange1);
        assertEquals(movies1.get(0).getRating(), worstRating.getRating());
        assertEquals(movies1.get(movies1.size() - 1).getRating(), bestRating.getRating());
    }

    @Test
    void sorting_year_test() {
        ResponseEntity<List<MovieDto>> exchange = testRestTemplate.exchange("/movies?by=year&order=desc", HttpMethod.GET, null, LIST_OF_MOVIES);
        List<MovieDto> movies = assertOk(exchange);
        MovieDto latest = movies.get(0);
        MovieDto oldest = movies.get(movies.size() - 1);
        assertTrue(latest.getYear() >= oldest.getYear());

        ResponseEntity<List<MovieDto>> exchange1 = testRestTemplate.exchange("/movies?by=year&order=asc", HttpMethod.GET, null, LIST_OF_MOVIES);
        List<MovieDto> movies1 = assertOk(exchange1);
        MovieDto oldest1 = movies1.get(0);
        MovieDto latest1 = movies1.get(movies.size() - 1);
        assertEquals(latest.getYear(), latest1.getYear());
        assertEquals(oldest.getYear(), oldest1.getYear());
    }

    @Test
    void movies_genres_test() {
        ResponseEntity<MovieDto> exchange = testRestTemplate.exchange("/movies/" + 1,
                HttpMethod.GET, null, MovieDto.class);
        MovieDto movie = assertOk(exchange);
        ResponseEntity<List<String>> exchange1 = testRestTemplate.exchange("/movies/genres", HttpMethod.GET, null, LIST_OF_GENRES);
        List<String> genres = assertOk(exchange1);
        assertTrue(genres.contains(movie.getGenre()));
    }

    @Test
    void sorting_genre_test() {
        ResponseEntity<List<String>> exchange1 = testRestTemplate.exchange("/movies/genres", HttpMethod.GET, null, LIST_OF_GENRES);
        List<String> genres = assertOk(exchange1);
        ResponseEntity<List<MovieDto>> exchange = testRestTemplate.exchange("/movies?genre=" + genres.get(0), HttpMethod.GET, null, LIST_OF_MOVIES);
        List<MovieDto> movies = assertOk(exchange);
        assertEquals(genres.get(0), movies.get(0).getGenre());
    }

    @Test
    void movies_set_get_test() {
        Movie movie = new Movie();
        assertNotNull(movie);
        // id
        movie.setId((long) -1);
        assertEquals(movie.getId(), -1);
        // description
        movie.setDescription("test");
        assertEquals(movie.getDescription(), "test");
        // duration
        movie.setDuration(150);
        assertEquals(movie.getDuration(), 150);
        // genre
        movie.setGenre("genre");
        assertEquals(movie.getGenre(), "genre");
        // url
        movie.setImgUrl("url");
        assertEquals(movie.getImgUrl(), "url");
        // name
        movie.setName("name");
        assertEquals(movie.getName(), "name");
        // producer
        movie.setProducer("producer");
        assertEquals(movie.getProducer(), "producer");
        // rating
        movie.setRating(10);
        assertEquals(movie.getRating(), 10);
        // year
        movie.setYear(2020);
        assertEquals(movie.getYear(), 2020);
        // comment
        Comment comment = new Comment();
        comment.setId((long) -1);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        movie.setComments(comments);
        assertEquals(movie.getComments().get(0).getId(), -1);
    }

    private <T> T assertOk(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }


}
