package AlgoView_Server.global.analysis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@NoArgsConstructor
public class AnalysisResponseDto {
    private Long id;

    public AnalysisResponseDto(Long id) {
        this.id = id;
    }
}
