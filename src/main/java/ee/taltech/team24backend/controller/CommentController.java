package ee.taltech.team24backend.controller;

import ee.taltech.team24backend.dto.CommentDto;
import ee.taltech.team24backend.model.Comment;
import ee.taltech.team24backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("comments")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<CommentDto> getComments() {
        return commentService.findAll();
    }

    @GetMapping("find")
    public List<CommentDto> getCommentByFilmId(@RequestParam(value = "movieId") Long movieId) {
        return commentService.findByMovieId(movieId);
    }
}
