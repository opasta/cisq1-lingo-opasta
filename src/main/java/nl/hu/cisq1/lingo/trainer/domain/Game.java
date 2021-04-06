package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Mark;
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
        String correctWord = round.getWordToGuess();

        if (correctWord.length() != attempt.length()) {
            //wanneer lengte niet toegestaan is
            throw new ActionNotAllowedException();
        }


        if (correctWord.equals(attempt)){
            this.gameStatus = GameStatus.WIN;
        } else {

            Feedback feedback = new Feedback();
            // bepalen welke letter wel en niet voorkomt
            List<Mark> marks = feedback.giveMarks(attempt, correctWord);

            //Nu moet er iets met deze letterlijst worden gedaan

            // moet de hint worden gereturned??
        }

        if (round.isPlayerEliminated() && this.gameStatus == GameStatus.PLAYING){
            // if eliminated --> set status eliminated
            this.gameStatus = GameStatus.ELIMINATED;

        }
    }

    public Long calculateScore() {
        Round round = this.getLastRound();
        score += 5 * (5 - round.getAttemptCount()) + 5;
        return score;
    }

    public Progress showProgress() {
        //de voortgang terruggeven
        if (this.gameStatus.equals(GameStatus.PLAYING)) {
            return new Progress(id, score, rounds, gameStatus);
        }

        return new Progress(id, score, rounds, gameStatus);
    }
}
