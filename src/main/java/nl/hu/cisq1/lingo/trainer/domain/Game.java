package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Feedback;

import javax.persistence.*;

@Entity
@Getter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Lob
    private Long score;


    public Progress getProgress() {
        return new Progress(null, score, null, null, null);
    }

    public long startNewGame() {
        return Long.parseLong(null);
    }

    public void startNewRound(String wordToGuess) {

    }

    public Integer getNextWordLength( int oldLength) {
        int newLength = oldLength + 1;
        if (newLength == 8){
            newLength = 5;
        }
        return newLength;
    }

    public Feedback guess(String word) {
        return null;
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

    public Game get() {
        return null;
    }
}
