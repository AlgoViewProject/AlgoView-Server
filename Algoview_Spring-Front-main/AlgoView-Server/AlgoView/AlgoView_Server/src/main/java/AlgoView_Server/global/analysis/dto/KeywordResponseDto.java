package AlgoView_Server.global.analysis.dto;

import AlgoView_Server.global.analysis.Analysis;
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

    private Long analysis_id;

    public KeywordResponseDto(String keyword, Integer frequency, Long analysis_id) {
        this.keyword = keyword;
        this.frequency = frequency;
        this.analysis_id = analysis_id;
    }
}
