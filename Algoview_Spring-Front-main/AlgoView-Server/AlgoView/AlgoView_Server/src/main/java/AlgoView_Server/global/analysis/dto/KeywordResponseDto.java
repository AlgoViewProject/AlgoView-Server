package AlgoView_Server.global.analysis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@NoArgsConstructor
public class KeywordResponseDto {
    private String keyword;

    private Integer frequency;

    private String analysis_id;

    public KeywordResponseDto(String keyword, Integer frequency, String analysis_id) {
        this.keyword = keyword;
        this.frequency = frequency;
        this.analysis_id = analysis_id;
    }
}
