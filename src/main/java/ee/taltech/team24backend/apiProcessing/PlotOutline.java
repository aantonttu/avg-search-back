package ee.taltech.team24backend.apiProcessing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlotOutline {

    @JsonProperty("author")
    private String author;
    @JsonProperty("text")
    private String text;

}
