package AlgoView_Server.global.analysis.service;

import AlgoView_Server.global.analysis.Analysis;
import AlgoView_Server.global.analysis.Keyword;
import AlgoView_Server.global.analysis.dto.KeywordResponseDto;
import AlgoView_Server.global.analysis.repository.AnalysisJpaRepository;
import AlgoView_Server.global.analysis.repository.KeywordJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class KeywordService {
    private final KeywordJpaRepository keywordJpaRepository;
    private final AnalysisJpaRepository analysisJpaRepository;

    public void save(List<KeywordResponseDto> keywords) {

        Set<Long> analysisIds = keywords.stream()
                .map(KeywordResponseDto::getAnalysis_id)
                .collect(Collectors.toSet());

        List<Long> nonExistentIds = analysisIds.stream()
                .filter(id -> !analysisJpaRepository.existsById(id))
                .collect(Collectors.toList());

        if (!nonExistentIds.isEmpty()) {
            throw new EntityNotFoundException("Analysis not found with ids: " + nonExistentIds);
        }

        // 키워드 일괄 저장
        List<Keyword> keywordsToSave = keywords.stream()
                .map(keyword -> {
                    Keyword k = new Keyword(keyword.getKeyword(), keyword.getFrequency());
                    Analysis analysis = analysisJpaRepository.findById(keyword.getAnalysis_id())
                            .orElseThrow(() -> new EntityNotFoundException("Analysis not found"));
                    k.setAnalysis(analysis);
                    return k;
                })
                .collect(Collectors.toList());

        keywordJpaRepository.saveAll(keywordsToSave);

//        for (KeywordResponseDto keyword : keywords) {
//            Keyword k = new Keyword(keyword.getKeyword(), keyword.getFrequency());
//            Analysis analysis = analysisJpaRepository.findById(keyword.getAnalysis_id())
//                    .orElseThrow(() -> new EntityNotFoundException("Analysis not found with id: " + keyword.getAnalysis_id()));
//            k.setAnalysis(analysis);
//            keywordJpaRepository.save(k);
//        }
    }

    public void saveTest(List<KeywordResponseDto> keywords) {
        for (KeywordResponseDto keyword : keywords) {
            Keyword k = new Keyword(keyword.getKeyword(), keyword.getFrequency());
            k.setAnalysis(null);
            keywordJpaRepository.save(k);
        }
    }

    public List<KeywordResponseDto> getKeywords() {
//        List<String> keywords = keywordJpaRepository.findAll().stream()
//                .map(a -> a.getKeyword())
//                .collect(Collectors.toList());
        return keywordJpaRepository.findAll().stream()
                .map(n -> new KeywordResponseDto(n.getKeyword(), n.getFrequency(), n.getAnalysis().getId()))
                .collect(Collectors.toList());

    }
}