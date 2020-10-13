package ee.taltech.team24backend.apiProcessing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Title {

    @JsonProperty("image")
    private Image image;
    @JsonProperty("runningTimeInMinutes")
    private BigDecimal runningTimeInMinutes;
    @JsonProperty("title")
    private String title;
    @JsonProperty("year")
    private Integer year;

}
