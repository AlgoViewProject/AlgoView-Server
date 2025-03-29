package AlgoView_Server.global.analysis.domain;

import AlgoView_Server.global.domain.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Keyword extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long id;

    @Column(nullable = false)
    private String analysis_id;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private Integer frequency;

    public Keyword( String keyword, Integer frequency, String analysis_id) {
        this.analysis_id = analysis_id;
        this.keyword = keyword;
        this.frequency = frequency;
    }
}
