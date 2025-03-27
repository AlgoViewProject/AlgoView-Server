package AlgoView_Server.global.analysis.service;

import AlgoView_Server.global.analysis.Analysis;
import AlgoView_Server.global.analysis.dto.AnalysisResponseDto;
import AlgoView_Server.global.analysis.repository.AnalysisJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalysisService {
    private final AnalysisJpaRepository analysisJpaRepository;

    public void save(Analysis analysis) {
        analysisJpaRepository.save(analysis);
    }

    public AnalysisResponseDto getAnalysisById(Long id) {
        Analysis analysis = analysisJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Analysis not found with id: " + id));
        return new AnalysisResponseDto(analysis.getId());
    }
}
