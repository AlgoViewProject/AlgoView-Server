package AlgoView_Server.global.analysis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Analysis {
    @Id
    @GeneratedValue
    @Column(name = "analysis_id")
    private Long id;
}
