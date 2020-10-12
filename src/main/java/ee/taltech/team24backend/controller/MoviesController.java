package ee.taltech.team24backend.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import ee.taltech.team24backend.apiProcessing.MovieId;
import ee.taltech.team24backend.model.Movie;
import ee.taltech.team24backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.Arrays;
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

    @GetMapping("imdb/films")
    public MovieId[] getMoviesImdb() throws IOException, UnirestException {
        JsonNode response = Unirest.get("https://rapidapi.p.rapidapi.com/title/get-top-rated-movies")
                .header("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .header("x-rapidapi-key", "13c5412c92msh5d9f79cb5dfc751p1ccd83jsn0c3f0ebc4708")
                .asJson()
                .getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray = response.toString();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        MovieId[] idArray = objectMapper.readValue(jsonCarArray, MovieId[].class);
        idArray = Arrays.copyOfRange(idArray, 0, 30);



        return idArray;

    }

    @GetMapping("imdb/details")
    public String getMoviesImdbGenre() throws IOException, UnirestException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://rapidapi.p.rapidapi.com/title/get-overview-details?tconst=tt0111161&currentCountry=US")
                .get()
                .addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "13c5412c92msh5d9f79cb5dfc751p1ccd83jsn0c3f0ebc4708")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }

    @GetMapping("imdb/user-reviews")
    public String getMoviesImdbReviews() throws IOException, UnirestException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://rapidapi.p.rapidapi.com/title/get-user-reviews?tconst=tt0944947")
                .get()
                .addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "13c5412c92msh5d9f79cb5dfc751p1ccd83jsn0c3f0ebc4708")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }

    @GetMapping("{id}")
    public Movie getMovie(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @GetMapping("find")
    public List<Movie> getMoviesByName(@RequestParam(value = "name") String name) {
        return movieService.findByName(name);
    }

    @GetMapping("rated")
    public List<Movie> getTopRatedMovies() {
        return movieService.getTopRated();
    }

    @GetMapping("latest")
    public List<Movie> getLatestMovies() {
        return movieService.getLatest();
    }

    @GetMapping("sorted")
    public List<Movie> sortMovies(@RequestParam(value = "by", defaultValue = "name") String by,
                                  @RequestParam(value = "order", defaultValue = "asc") String order) {
        return movieService.sorting(by, order);
    }

    @GetMapping("genres")
    public List<Movie> getMoviesByGenres(@RequestParam(value = "genre") String genre) {
        return movieService.getMoviesByGenres(genre);
    }

    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping("{id}")
    public void editMovie(@RequestBody Movie newMovie, @PathVariable Long id) {
        movieService.edit(newMovie, id);
    }

    @DeleteMapping("{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
    }
}
