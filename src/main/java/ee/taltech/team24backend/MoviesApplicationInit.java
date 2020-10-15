package ee.taltech.team24backend;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import ee.taltech.team24backend.apiProcessing.MovieApi;
import ee.taltech.team24backend.apiProcessing.MovieId;
import ee.taltech.team24backend.model.Comment;
import ee.taltech.team24backend.model.Movie;
import ee.taltech.team24backend.repository.MovieRepository;
import ee.taltech.team24backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MoviesApplicationInit implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CommentService commentService;


    @Override
    public void run(String... args) throws Exception {
        List<Movie> movies = new ArrayList<>();
        List<MovieApi> movieApis = getMoviesImdbApi();
        for (MovieApi movieApi : movieApis) {
            Movie movie = new Movie(
                    movieApi.getTitle().getTitle(), movieApi.getPlotOutline().getText(), movieApi.getPlotOutline().getAuthor(),
                    movieApi.getRatings().getRating(), movieApi.getTitle().getImage().getUrl(), movieApi.getGenres().get(0),
                    movieApi.getTitle().getYear(), movieApi.getTitle().getRunningTimeInMinutes().intValue()
            );
            movies.add(movie);
        }
        List<Comment> comments = List.of(
                new Comment("Anton Antonov", "10/10 oh my god."),
                new Comment("Vladislav Poljakov", "like it"),
                new Comment("German Hanmamedov", "best film ever")
        );

        movieRepository.saveAll(movies);
        for (Comment comment : comments) {
            commentService.saveComment(movies.get(0), comment);
        }

    }

    public List<MovieApi> getMoviesImdbApi() throws IOException, UnirestException {
        JsonNode response = Unirest.get("https://rapidapi.p.rapidapi.com/title/get-top-rated-movies")
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "6f41664104mshe3abdd360fd220fp1ec8fdjsne2c0b9497d64")
                .asJson()
                .getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray = response.toString();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        MovieId[] idArray = objectMapper.readValue(jsonCarArray, MovieId[].class);
        idArray = Arrays.copyOfRange(idArray, 0, 1);
        List<MovieApi> moviesApi = new ArrayList<>();
        for (MovieId movieId : idArray) {
            String[] id = movieId.getId().split("/");
            moviesApi.add(this.getMovieDetailApi(id[id.length - 1]));
        }
        return moviesApi;
    }

    public MovieApi getMovieDetailApi(String movieId) throws IOException, UnirestException {
        JsonNode response = Unirest.get("https://rapidapi.p.rapidapi.com/title/get-overview-details?tconst=" + movieId)
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "6f41664104mshe3abdd360fd220fp1ec8fdjsne2c0b9497d64")
                .asJson()
                .getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray = response.toString();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        return objectMapper.readValue(jsonCarArray, MovieApi.class);
    }


}
