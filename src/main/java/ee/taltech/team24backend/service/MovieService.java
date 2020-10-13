package ee.taltech.team24backend.service;

import ee.taltech.team24backend.dto.MovieDto;
import ee.taltech.team24backend.exceptions.InvalidMovieException;
import ee.taltech.team24backend.model.Movie;
import ee.taltech.team24backend.exceptions.MovieNotFoundException;
import ee.taltech.team24backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

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

    public MovieDto save(Movie movie) {
        if (isNotBlank(movie.getName()) && isNotBlank(movie.getDescription()) && isNotBlank(movie.getProducer()) &&
                isNotBlank(movie.getGenre()) && isNotBlank(movie.getImgUrl()) && movie.getDuration() > 0 &&
                movie.getYear() > 0 && movie.getRating() > 0.0) {
            // save will generate id for object
            return convertMovie(movieRepository.save(movie));

        } else throw new InvalidMovieException("Movie is not added! Please input valid params.");
    }

    public void edit(Movie newMovie, Long id) {
        movieRepository.findById(id)
                .map(movie -> {
                    movie.setName(newMovie.getName());
                    movie.setDescription(newMovie.getDescription());
                    movie.setProducer(newMovie.getProducer());
                    movie.setRating(newMovie.getRating());
                    return convertMovie(movieRepository.save(movie));
                })
                .orElseGet(() -> {
                    newMovie.setId(id);
                    return convertMovie(movieRepository.save(newMovie));
                });
    }

    public void delete(Long id) {
        Movie dbMovie = findById(id);
        if (dbMovie != null) {
            movieRepository.delete(dbMovie);
        }
    }

// 4 top rated movies
//
//    public List<MovieDto> getTopRated() {
//        List<MovieDto> movies = movieRepository.findAll().stream()
//                .sorted(Comparator.comparing(Movie::getRating).reversed())
//                .map(this::convertMovie)
//                .collect(Collectors.toList());
//        if (movies.size() < 4) {
//            return movies;
//        } else {
//            return movies.subList(0, 4);
//        }
//    }


    public List<MovieDto> getMoviesByGenres(String genre) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getGenre().toLowerCase().equalsIgnoreCase(genre.toLowerCase()))
                .map(this::convertMovie)
                .collect(Collectors.toList());
    }

    public List<String> getAllGenres(){
        List<String> genres = new ArrayList<>();
        List<MovieDto> movies = findAll();
        for (MovieDto movie : movies) {
            if (!genres.contains(movie.getGenre())){
                genres.add(movie.getGenre());
            }
        } return genres;
    }

    public List<MovieDto> sorting(String by, String order) {
        if (by.equals("name")) {
            if (order.equals("desc")) {
                return movieRepository.findAll().stream()
                        .sorted(Comparator.comparing(Movie::getName).reversed())
                        .map(this::convertMovie)
                        .collect(Collectors.toList());
            } else return movieRepository.findAll().stream()
                    .sorted(Comparator.comparing(Movie::getName))
                    .map(this::convertMovie)
                    .collect(Collectors.toList());
        }
        if (by.equals("added")) {
            if (order.equals("desc")) {
                return movieRepository.findAll().stream()
                        .sorted(Comparator.comparing(Movie::getId))
                        .map(this::convertMovie)
                        .collect(Collectors.toList());
            } else return movieRepository.findAll().stream()
                    .sorted(Comparator.comparing(Movie::getId).reversed())
                    .map(this::convertMovie)
                    .collect(Collectors.toList());
        }
        if (by.equals("rating")) {
            if (order.equals("desc")) {
                return movieRepository.findAll().stream()
                        .sorted(Comparator.comparing(Movie::getRating))
                        .map(this::convertMovie)
                        .collect(Collectors.toList());
            } else return movieRepository.findAll().stream()
                    .sorted(Comparator.comparing(Movie::getRating).reversed())
                    .map(this::convertMovie)
                    .collect(Collectors.toList());
        }
        return movieRepository.findAll().stream()
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
        return movieDto;
    }
}
