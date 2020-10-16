package ee.taltech.team24backend.service;

import ee.taltech.team24backend.dto.MovieDto;
import ee.taltech.team24backend.model.Movie;
import ee.taltech.team24backend.exceptions.MovieNotFoundException;
import ee.taltech.team24backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CommentService commentService;

    public List<MovieDto> findAll() {
        return movieRepository.findAll()
                .stream().map(this::convertMovie)
                .collect(Collectors.toList());
    }

    public List<MovieDto> findByName(String name) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getName().toLowerCase().startsWith(name.toLowerCase()))
                .map(this::convertMovie)
                .collect(Collectors.toList());
    }

    public Movie findById(Long id) throws RuntimeException {
        return movieRepository.findById(id).orElseThrow(MovieNotFoundException::new);
    }

    public List<MovieDto> getMoviesByGenres(String genre) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getGenre().toLowerCase().equalsIgnoreCase(genre.toLowerCase()))
                .map(this::convertMovie)
                .collect(Collectors.toList());
    }

    public List<String> getAllGenres() {
        List<String> genres = new ArrayList<>();
        List<MovieDto> movies = findAll();
        for (MovieDto movie : movies) {
            if (!genres.contains(movie.getGenre())) {
                genres.add(movie.getGenre());
            }
        }
        return genres;
    }

    public List<MovieDto> sorting(String by, String order) {
        if (by.equals("name")) {
            if (order.equals("desc")) {
                return getMoviesByNameDesc();
            } else {
                return getMoviesByNameAsc();
            }
        }
        if (by.equals("relevance")) {
            if (order.equals("desc")) {
                return getRelevantMoviesDesc();
            } else {
                return getRelevantMoviesAsc();
            }
        }
        if (by.equals("rating")) {
            if (order.equals("desc")) {
                return getMoviesByRatingDesc();
            } else {
                return getMoviesByRatingAsc();
            }
        }
        if (by.equals("year")) {
            if (order.equals("desc")) {
                return getMoviesByYearDesc();
            } else {
                return getMoviesByYearAsc();
            }
        }
        return findAll();
    }

    private List<MovieDto> getMoviesByYearAsc() {
        return movieRepository.findAll().stream()
                .sorted(Comparator.comparing(Movie::getYear))
                .map(this::convertMovie)
                .collect(Collectors.toList());
    }

    private List<MovieDto> getMoviesByYearDesc() {
        return movieRepository.findAll().stream()
                .sorted(Comparator.comparing(Movie::getYear).reversed())
                .map(this::convertMovie)
                .collect(Collectors.toList());
    }

    private List<MovieDto> getMoviesByRatingAsc() {
        return movieRepository.findAll().stream()
                .sorted(Comparator.comparing(Movie::getRating))
                .map(this::convertMovie)
                .collect(Collectors.toList());
    }

    private List<MovieDto> getMoviesByRatingDesc() {
        return movieRepository.findAll().stream()
                .sorted(Comparator.comparing(Movie::getRating).reversed())
                .map(this::convertMovie)
                .collect(Collectors.toList());
    }

    private List<MovieDto> getRelevantMoviesAsc() {
        return movieRepository.findAll().stream()
                .sorted(Comparator.comparing(Movie::getId).reversed())
                .map(this::convertMovie)
                .collect(Collectors.toList());
    }

    private List<MovieDto> getMoviesByNameAsc() {
        return movieRepository.findAll().stream()
                .sorted(Comparator.comparing(Movie::getName))
                .map(this::convertMovie)
                .collect(Collectors.toList());
    }

    private List<MovieDto> getMoviesByNameDesc() {
        return movieRepository.findAll().stream()
                .sorted(Comparator.comparing(Movie::getName).reversed())
                .map(this::convertMovie)
                .collect(Collectors.toList());
    }

    private List<MovieDto> getRelevantMoviesDesc() {
        return movieRepository.findAll().stream()
                .sorted(Comparator.comparing(Movie::getId))
                .map(this::convertMovie)
                .collect(Collectors.toList());
    }

    public MovieDto convertMovie(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        movieDto.setDescription(movie.getDescription());
        movieDto.setDuration(movie.getDuration());
        movieDto.setGenre(movie.getGenre());
        movieDto.setImgUrl(movie.getImgUrl());
        movieDto.setProducer(movie.getProducer());
        movieDto.setRating(movie.getRating());
        movieDto.setYear(movie.getYear());
        movieDto.setComments(movie.getComments().stream().map(comment -> commentService.convertComment(comment)).collect(Collectors.toList()));
        return movieDto;
    }
}
