package ee.taltech.team24backend.service;

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

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findByFilmId(Long movieId) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getMovieId().equals(movieId))
                .collect(Collectors.toList());
    }
}
