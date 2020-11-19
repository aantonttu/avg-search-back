package ee.taltech.team24backend;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import ee.taltech.team24backend.apiProcessing.MovieApi;
import ee.taltech.team24backend.apiProcessing.MovieId;
import ee.taltech.team24backend.model.Movie;
import ee.taltech.team24backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MoviesApplicationInit implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {
        List<String> movieTitles = movieRepository.findAll().stream().map(Movie::getName).collect(Collectors.toList());
        List<Movie> movies = new ArrayList<>();
        List<MovieApi> movieApis = getMoviesImdbApi();
        for (MovieApi movieApi : movieApis) {
            if (!movieTitles.contains(movieApi.getTitle().getTitle())) {
                Movie movie = new Movie(
                        movieApi.getTitle().getTitle(), movieApi.getPlotOutline().getText(), movieApi.getPlotOutline().getAuthor(),
                        movieApi.getRatings().getRating(), movieApi.getTitle().getImage().getUrl(), movieApi.getGenres().get(0),
                        movieApi.getTitle().getYear(), movieApi.getTitle().getRunningTimeInMinutes().intValue()
                );
                movies.add(movie);
            }
        }
        movieRepository.saveAll(movies);
    }


    public List<MovieApi> getMoviesImdbApi() throws IOException, UnirestException {
        JsonNode response = Unirest.get("https://rapidapi.p.rapidapi.com/title/get-top-rated-movies")
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "402a7959eemshffc1a7591ada4c0p150a46jsn0f414405106e")
                .asJson()
                .getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray = response.toString();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        MovieId[] idArray = objectMapper.readValue(jsonCarArray, MovieId[].class);
        idArray = Arrays.copyOfRange(idArray, 0, 2);
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
                .header("x-rapidapi-key", "402a7959eemshffc1a7591ada4c0p150a46jsn0f414405106e")
                .asJson()
                .getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray = response.toString();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        return objectMapper.readValue(jsonCarArray, MovieApi.class);
    }


}
