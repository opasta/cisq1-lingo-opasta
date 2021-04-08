package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.ActionNotAllowedException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.GameNotFoundException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.InvalidAction;
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
            throw ActionNotAllowedException.withStatus(this.gameStatus);
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
        if (!this.getCurrentRound().isPlaying(this.gameStatus)) {
            throw InvalidAction.cannotGuessWord(this.gameStatus);
        }

        final Round round = this.getCurrentRound();
        assert round != null;
        round.guess(attempt);

        if (round.isPlayerEliminated()) {
            this.gameStatus = GameStatus.ELIMINATED;
        }

        if (round.isWordGuessed()) {
            this.score += round.calculateScore();
            this.gameStatus = GameStatus.WIN;
        }
    }

    private Round getCurrentRound() {
        if (rounds.size() == 0){
            return null;
        }else {
            return rounds.get(rounds.size() - 1);
        }
    }

    public Progress showProgress() {
        //de voortgang terruggeven
        if (this.gameStatus.equals(GameStatus.PLAYING)) {
            return new Progress(id, score, rounds, gameStatus);
        }

        return new Progress(id, score, rounds, gameStatus);
    }
}
