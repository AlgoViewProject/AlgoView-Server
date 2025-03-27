package AlgoView_Server.domain.news.dto;

import AlgoView_Server.global.analysis.Analysis;
import AlgoView_Server.global.analysis.Keyword;
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
    private Keyword keyword;

    public void setKeyword(KeywordResponseDto keywordResponseDto, Analysis analysis) {
        this.keyword = new Keyword(keywordResponseDto.getKeyword(), keywordResponseDto.getFrequency());
        this.keyword.setAnalysis(analysis);
    }
}