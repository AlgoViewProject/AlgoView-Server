package AlgoView_Server.domain.review;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

//사용자 후기 저장 테이블
@Entity
@Getter
@NoArgsConstructor
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;
    private String name;

    @Column(nullable = false)
    private String contents;

    public Review(String name, String contents) {
        this.name = name;
        this.contents = contents;
    }
}
