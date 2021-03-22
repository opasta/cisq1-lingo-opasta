package nl.hu.cisq1.lingo.trainer.domain.rounds;

import nl.hu.cisq1.lingo.trainer.domain.Progress;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private String wordToGuess;
    private int attempts;
    private List<Feedback> feedbackHistory = new ArrayList<>();
    List<Mark> marks =  new ArrayList<>();
    private List<String> hintHistory = new ArrayList<>();

    public Round(String wordToGuess, int attempts){
        this.wordToGuess=wordToGuess;
        this.attempts=attempts;
    }

    public void startNewRound(String wordToGuess) {

    }

    public void guess(String word) {
    //check if possible to guess
        if (attempts == 5){

        }
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
