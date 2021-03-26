package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Round;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long score = 0L;

    @Enumerated
    private GameStatus gameStatus = GameStatus.WAITING_FOR_ROUND;

    @OneToMany
    @JoinColumn
    @Cascade(CascadeType.ALL)
    private final List<Round> rounds = new ArrayList<>();

    public void startNewRound(String wordToGuess) {
        if (!this.gameStatus.equals(GameStatus.WAITING_FOR_ROUND)) {
            throw new ActionNotAllowedException();
        }

        this.rounds.add(new Round(wordToGuess));
        this.gameStatus = GameStatus.PLAYING;
    }

    public Integer getNextWordLength() {
        int newLength = this.getLastRound().getWordLength() + 1;

        if (newLength == 8 || newLength < 5) {
            newLength = 5;
        }

        return newLength;
    }

    private Round getLastRound() {
        return rounds.get(rounds.size() - 1);
    }

    public void guess(String attempt) {
        if (!this.gameStatus.equals(GameStatus.PLAYING)) {
            throw new ActionNotAllowedException();
        }

        // current round -> guess(attempt)
        Round round = this.getLastRound();

        // if eliminated --> set status eliminated
    }


    public Progress showProgress() {
        return null;
    }


    public boolean isPlayerEliminated() {
        return true;
    }

    public boolean isPlaying() {
        return true;
    }

    public boolean isPresent() {
        return true;
    }
}
