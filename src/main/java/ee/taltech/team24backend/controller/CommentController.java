package ee.taltech.team24backend.controller;

import ee.taltech.team24backend.dto.CommentDto;
import ee.taltech.team24backend.security.Roles;
import ee.taltech.team24backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Secured(Roles.USER)
@RequestMapping("comments")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<CommentDto> getComments() {
        return commentService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}

