package ee.taltech.team24backend.service;

import ee.taltech.team24backend.dto.CommentDto;
import ee.taltech.team24backend.exceptions.NotFoundCommentsForMovie;
import ee.taltech.team24backend.model.Comment;
import ee.taltech.team24backend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

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

    public CommentDto convertComment(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setMovieId(comment.getMovieId());
        commentDto.setUserName(comment.getUserName());
        commentDto.setCommentText(comment.getCommentText());
        return commentDto;
    }
}
