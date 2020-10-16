package ee.taltech.team24backend.controller;

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
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentControllerTest {

    public static final ParameterizedTypeReference<List<Comment>> LIST_OF_COMMENTS = new ParameterizedTypeReference<>() {
    };
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void add_comment_test() {
        Comment commentToAdd = new Comment("testUser", "testText");
        ResponseEntity<Comment> exchange = testRestTemplate.exchange("/comments/1", HttpMethod.POST, new HttpEntity<>(commentToAdd), Comment.class);
        Comment comment = assertOk(exchange);
        assertNotNull(comment);
    }

    @Test
    void comments_exits() {
        Comment commentToAdd = new Comment("testUser", "testText");
        testRestTemplate.exchange("/comments/1", HttpMethod.POST, new HttpEntity<>(commentToAdd), Comment.class);
        testRestTemplate.exchange("/comments/1", HttpMethod.POST, new HttpEntity<>(commentToAdd), Comment.class);
        ResponseEntity<List<Comment>> exchange = testRestTemplate.exchange("/comments", HttpMethod.GET, null, LIST_OF_COMMENTS);
        List<Comment> comments = assertOk(exchange);
        assertFalse(comments.isEmpty());
    }

    @Test
    void delete_comment() {
        Comment commentToAdd = new Comment("testUser", "testText");
        testRestTemplate.exchange("/comments/1", HttpMethod.POST, new HttpEntity<>(commentToAdd), Comment.class);
        ResponseEntity<List<Comment>> exchange = testRestTemplate.exchange("/comments", HttpMethod.GET, null, LIST_OF_COMMENTS);
        assertNotNull(exchange.getBody());
        int size1 = exchange.getBody().size();
        Long commentId = exchange.getBody().get(0).getId();
        testRestTemplate.exchange("/comments/" + commentId, HttpMethod.DELETE, null, Comment.class);
        ResponseEntity<List<Comment>> exchange1 = testRestTemplate.exchange("/comments", HttpMethod.GET, null, LIST_OF_COMMENTS);
        Integer size2 = Objects.requireNonNull(exchange1.getBody()).size();
        assertEquals(size1-1 ,size2);
    }


    private <T> T assertOk(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }
}
