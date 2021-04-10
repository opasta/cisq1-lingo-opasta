package nl.hu.cisq1.lingo.trainer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Mark;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Round;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Progress {
    private Long id;
    private Long score;
    private List<Round> rounds;
    private GameStatus status;

    public Progress(){}

    public Progress(Long id, Long score, List<Round> rounds, GameStatus status){
        this.id = id;
        this.score = score;
        this.rounds = rounds;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", score=" + score +
                ", rounds=" + rounds +
                ", status=" + status +
                '}';
    }
}
