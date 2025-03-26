package AlgoView_Server.global.analysis;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "analysis_id")
    Analysis analysis;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private Integer frequency;

    public Keyword(String keyword, Integer frequency) {
        this.keyword = keyword;
        this.frequency = frequency;
    }
}
