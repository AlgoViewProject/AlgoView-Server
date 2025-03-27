package AlgoView_Server.global.analysis.repository;

import AlgoView_Server.global.analysis.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisJpaRepository extends JpaRepository<Analysis, Long> {
}
