package ee.taltech.team24backend.service;

import ee.taltech.team24backend.dto.CommentDto;
import ee.taltech.team24backend.exceptions.CommentNotFoundException;
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

    private Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    public List<CommentDto> findAll() {
        return commentRepository.findAll().stream()
                .map(this::convertComment)
                .collect(Collectors.toList());
    }

    public void saveComment(Long movieId, CommentDto comment) {
        commentRepository.save(new Comment(movieId, comment.getUserName(), comment.getCommentText()));
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

    public void deleteAllMovieComments(Movie movie) {
        for (Comment comment : movie.getComments()) {
            deleteComment(comment.getId());
        }
    }
}
