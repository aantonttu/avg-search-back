package ee.taltech.team24backend.apiProcessing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ratings {

    @JsonProperty("rating")
    private Double rating;
    @JsonProperty("ratingCount")
    private BigDecimal ratingCount;
    @JsonProperty("topRank")
    private Integer topRank;
}
