package AlgoView_Server.global.analysis.repository;

import AlgoView_Server.global.analysis.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordJpaRepository extends JpaRepository<Keyword, Long> {
}
