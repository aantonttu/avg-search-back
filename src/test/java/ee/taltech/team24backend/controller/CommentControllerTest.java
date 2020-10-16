package ee.taltech.team24backend.controller;

import ee.taltech.team24backend.dto.MovieDto;
import ee.taltech.team24backend.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentControllerTest {

    public static final ParameterizedTypeReference<List<Comment>> LIST_OF_COMMENTS = new ParameterizedTypeReference<>() {
    };
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void comments_exits() {
        ResponseEntity<List<Comment>> exchange = testRestTemplate.exchange("/comments", HttpMethod.GET, null, LIST_OF_COMMENTS);
        List<Comment> comments = assertOk(exchange);
        assertFalse(comments.isEmpty());
    }

    @Test
    void add_comment_test() {
        Comment commentToAdd = new Comment();
        ResponseEntity<Comment> exchange = testRestTemplate.exchange("/comments/1", HttpMethod.POST, new HttpEntity<>(commentToAdd), Comment.class);
        Comment comment = assertOk(exchange);
        assertNotNull(comment);
    }

    @Test
    void find_comment_for_film() {
        ResponseEntity<List<Comment>> exchange = testRestTemplate.exchange("/comments/find?movieId=1", HttpMethod.GET, null, LIST_OF_COMMENTS);
        List<Comment> comments = assertOk(exchange);
        assertNotNull(comments);
    }


    private <T> T assertOk(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }
}
