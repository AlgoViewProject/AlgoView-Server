package AlgoView_Server.global.analysis.service;

import AlgoView_Server.global.analysis.domain.Keyword;
import AlgoView_Server.global.analysis.dto.KeywordResponseDto;
import AlgoView_Server.global.analysis.repository.KeywordJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class KeywordService {
    private final KeywordJpaRepository keywordJpaRepository;

    public void save(List<KeywordResponseDto> keywords) {

        // 키워드 일괄 저장
        List<Keyword> keywordsToSave = keywords.stream()
                .map(keyword -> new Keyword(keyword.getKeyword(), keyword.getFrequency(), keyword.getAnalysis_id()))
                .collect(Collectors.toList());

        keywordJpaRepository.saveAll(keywordsToSave);
    }
    public List<KeywordResponseDto> getKeywords() {
        return keywordJpaRepository.findAll().stream()
                .map(n -> new KeywordResponseDto(n.getKeyword(), n.getFrequency(), n.getAnalysis_id()))
                .collect(Collectors.toList());

    }
}