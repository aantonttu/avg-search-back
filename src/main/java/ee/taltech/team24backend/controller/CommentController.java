package ee.taltech.team24backend.controller;

import ee.taltech.team24backend.model.Comment;
import ee.taltech.team24backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("comment")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getComments() {
        return commentService.findAll();
    }

    @GetMapping("find")
    public List<Comment> getCommentByFilmId(@RequestParam(value = "movieId") Long movieId) {
        return commentService.findByFilmId(movieId);
    }
}
