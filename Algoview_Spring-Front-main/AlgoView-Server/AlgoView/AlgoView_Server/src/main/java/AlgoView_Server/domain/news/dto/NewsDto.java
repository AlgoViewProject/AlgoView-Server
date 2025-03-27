package AlgoView_Server.domain.news.dto;

import AlgoView_Server.global.analysis.dto.KeywordResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class NewsDto {
    private String title;
    private String description;
    private String link;
    private String keyword;

    public void setKeyword(KeywordResponseDto keywordResponseDto) {
        this.keyword = keywordResponseDto.getKeyword();
    }
}