package nl.hu.cisq1.lingo.trainer.domain.rounds;

import nl.hu.cisq1.lingo.trainer.domain.Progress;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Round {
    public static final int MAX_ATTEMPTS = 5;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String wordToGuess;

    @OneToMany
    private final List<Feedback> feedbackHistory = new ArrayList<>();

    public Round(){}
    public Round(String wordToGuess){
        this.wordToGuess=wordToGuess;
    }






    public void startNewRound(String wordToGuess) {

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
}
