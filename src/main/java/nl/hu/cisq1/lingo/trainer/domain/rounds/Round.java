package nl.hu.cisq1.lingo.trainer.domain.rounds;

import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
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

    private String hint;

    @OneToMany
    private final List<Feedback> feedbackHistory = new ArrayList<>();

    public Round() {
    }

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.hint = this.giveInitialHint();
    }

    private String giveInitialHint() {
        //hint = wordToGuess;
        StringBuilder firstletter = new StringBuilder();
        firstletter.append(wordToGuess.substring(0,1));
        for (int i = 0; i < wordToGuess.length() - 1; i++) {
            firstletter.append(".");
        }
        //feedbackHistory.add(Feedback.)
        return firstletter.toString();
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public long getAttemptCount() {
        return feedbackHistory.size();
    }

    public List<Mark> getLastFeedback() {
        return feedbackHistory.get(feedbackHistory.size() - 1).getMarks();
    }

    public boolean isPlayerEliminated() {
        return getAttemptCount() > 5;
    }

    public boolean isPlaying(GameStatus gameStatus) {
        return gameStatus == GameStatus.PLAYING;
    }

    public int getWordLength() {
        return wordToGuess.length();
    }

    public String getHint() {
        return hint;
    }

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", wordToGuess='" + wordToGuess + '\'' +
                ", hint='" + hint + '\'' +
                ", feedbackHistory=" + feedbackHistory +
                '}';
    }
}
