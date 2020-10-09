package ee.taltech.team24backend.service;

import ee.taltech.team24backend.model.Comment;
import ee.taltech.team24backend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findByFilmId(Long filmId) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getFilmId().equals(filmId))
                .collect(Collectors.toList());
    }

//                    movie.setProducer(newMovie.getProducer());
//                    movie.setRating(newMovie.getRating());
//                    return commentRepository.save(movie);
//                })
//                .orElseGet(() -> {
//                    newMovie.setId(id);
//                    return commentRepository.save(newMovie);
//                });
//    }
//
//    public void delete(Long id) {
//        Movie dbMovie = findById(id);
//        if (dbMovie != null) {
//            commentRepository.delete(dbMovie);
//        }
//    }
//
//
//    public List<Movie> getTopRated() {
//        List<Movie> movies = commentRepository.findAll().stream()
//                .sorted(Comparator.comparing(Movie::getRating).reversed())
//                .collect(Collectors.toList());
//        if (movies.size() < 4) {
//            return movies;
//        } else {
//            return movies.subList(0, 4);
//        }
//    }
//
//    public List<Movie> getLatest() {
//        return commentRepository.findAll().stream()
//                .sorted(Comparator.comparing(Movie::getId).reversed())
//                .collect(Collectors.toList());
//    }
//
//    public List<Movie> getMoviesByGenres(String genre) {
//        return commentRepository.findAll().stream()
//                .filter(movie -> movie.getGenres().toLowerCase().equalsIgnoreCase(genre.toLowerCase()))
//                .collect(Collectors.toList());
//    }
//
//    public List<Movie> sorting(String by, String order) {
//        if (by.equals("name")) {
//            if (order.equals("desc")) {
//                return commentRepository.findAll().stream()
//                        .sorted(Comparator.comparing(Movie::getName).reversed())
//                        .collect(Collectors.toList());
//            } else return commentRepository.findAll().stream()
//                    .sorted(Comparator.comparing(Movie::getName))
//                    .collect(Collectors.toList());
//        }
//        if (by.equals("added")) {
//            if (order.equals("desc")) {
//                return commentRepository.findAll().stream()
//                        .sorted(Comparator.comparing(Movie::getId))
//                        .collect(Collectors.toList());
//            } else return commentRepository.findAll().stream()
//                    .sorted(Comparator.comparing(Movie::getId).reversed())
//                    .collect(Collectors.toList());
//        }
//        if (by.equals("rating")) {
//            if (order.equals("desc")) {
//                return commentRepository.findAll().stream()
//                        .sorted(Comparator.comparing(Movie::getRating))
//                        .collect(Collectors.toList());
//            } else return commentRepository.findAll().stream()
//                    .sorted(Comparator.comparing(Movie::getRating).reversed())
//                    .collect(Collectors.toList());
//        }
//        return commentRepository.findAll();
//    }
}
