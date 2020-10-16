package ee.taltech.team24backend.service;

import ee.taltech.team24backend.dto.CommentDto;
import ee.taltech.team24backend.exceptions.CommentNotFoundException;
import ee.taltech.team24backend.exceptions.MovieNotFoundException;
import ee.taltech.team24backend.exceptions.NotFoundCommentsForMovie;
import ee.taltech.team24backend.model.Comment;
import ee.taltech.team24backend.model.Movie;
import ee.taltech.team24backend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MovieService movieService;

    private Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    public List<CommentDto> findAll() {
        return commentRepository.findAll().stream()
                .map(this::convertComment)
                .collect(Collectors.toList());
    }

    public List<CommentDto> findByMovieId(Long movieId) {
        List<CommentDto> commentDtoList = commentRepository.findAll().stream()
                .filter(comment -> comment.getMovieId().equals(movieId))
                .map(this::convertComment)
                .collect(Collectors.toList());
        if (commentDtoList.size() == 0) throw new NotFoundCommentsForMovie();
        return commentDtoList;
    }

    public CommentDto saveComment(Movie movie, CommentDto comment) {
        return convertComment(
                commentRepository.save(new Comment(movie.getId(), comment.getUserName(), comment.getCommentText()))
        );
    }

    public CommentDto getCorrectMovie(Long id, CommentDto comment) {
        Movie movie = movieService.findById(id);
        if (movie != null) {
            return saveComment(movie, comment);
        }
        throw new MovieNotFoundException();
    }

    public void deleteComment(Long id) {
        Comment dbComment = findById(id);
        if (dbComment != null) {
            commentRepository.delete(dbComment);
        }
    }

    public CommentDto convertComment(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setMovieId(comment.getMovieId());
        commentDto.setUserName(comment.getUserName());
        commentDto.setCommentText(comment.getCommentText());
        return commentDto;
    }
}
