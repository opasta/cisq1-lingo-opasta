package nl.hu.cisq1.lingo.trainer.domain.rounds;

import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.IncorrectAttemptLength;

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
        StringBuilder firstletter = new StringBuilder();
        firstletter.append(wordToGuess.substring(0,1));
        for (int i = 0; i < wordToGuess.length() - 1; i++) {
            firstletter.append(".");
        }
        return firstletter.toString();
    }

    public void guess(String attempt) {
        String correctWord = this.getWordToGuess();
        if (attempt.length() != correctWord.length()) {
            throw IncorrectAttemptLength.wordLenght(attempt.length());
        }
        Feedback feedback = new Feedback();
        List<Mark> marks = feedback.giveMarks(attempt, correctWord);
        feedbackHistory.add(new Feedback(attempt, marks));
    }

    public int calculateScore() {
        return 5 * (5 - feedbackHistory.size()) + 5;
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
        return getAttemptCount() == 5;
    }

    public boolean isWordGuessed() {
        List<Mark> lastFeedback = this.getLastFeedback();
        return !lastFeedback.contains(Mark.ABSENT) && !lastFeedback.contains(Mark.PRESENT);
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
