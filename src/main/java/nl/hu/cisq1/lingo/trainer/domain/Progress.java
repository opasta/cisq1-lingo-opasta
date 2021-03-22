package nl.hu.cisq1.lingo.trainer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Round;

@AllArgsConstructor
@Getter
public class Progress {
    private Long id;
    private Long score;
    private Feedback hints;
    private Round roundNumber;
    private GameStatus status;

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", score=" + score +
                ", hints=" + hints +
                ", roundNumber=" + roundNumber +
                ", status=" + status +
                '}';
    }
}
