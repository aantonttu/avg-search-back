package ee.taltech.team24backend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {
    private Long id;
    private String name;
    private String description;
    private String producer;
    private float rating;
    private String imgUrl;
    private String genre;
    private Integer year;
    private Integer duration;
}
