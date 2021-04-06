package nl.hu.cisq1.lingo.trainer.domain.rounds;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static nl.hu.cisq1.lingo.trainer.domain.rounds.Mark.ABSENT;
import static nl.hu.cisq1.lingo.trainer.domain.rounds.Mark.CORRECT;

@Getter
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String attempt;

    @ElementCollection
    private List<Mark> marks;

    public Feedback() {}
    public Feedback(String attempt, List<Mark> marks){
        this.attempt = attempt;
        this.marks = marks;
    }

    public boolean isWordGuessed() {
        return this.marks.stream()
                .allMatch(Mark.CORRECT::equals);
    }

    public boolean isWordInvalid() {
        return this.marks.stream()
                .allMatch(Mark.ABSENT::equals);
    }

    public String giveHint(String previousHint) {
        StringBuilder hint = new StringBuilder();

        for (int i = 0; i < previousHint.length(); i++) {
            if (this.marks.get(i).equals(Mark.CORRECT)) {
                hint.append(attempt.charAt(i));
            } else {
                hint.append(previousHint.charAt(i));
            }
        }

        return hint.toString();
    }

    public String giveFirstLetter(String correctWord, String previousHint) {
        StringBuilder hint = new StringBuilder();

        for (int i = 0; i < previousHint.length(); i++) {
            if (this.marks.get(i).equals(Mark.CORRECT)) {
                hint.append(attempt.charAt(i));
            } else {
                hint.append(previousHint.charAt(i));
            }
        }

        return hint.toString();
    }

    public List<Mark> giveMarks(String attempt, String correctWord) {

        List<Mark> marks = new ArrayList<>();

        for (int i = 0; i < correctWord.length(); i++){
            marks.add(ABSENT);
        }

        char[] aWAr = attempt.toCharArray();
        char[] cWAr = correctWord.toCharArray();
        int wordLength = correctWord.length();

        for (int i = 0; i < wordLength; i++) {
            if (aWAr[i] == cWAr[i]) {
                marks.set(i, Mark.CORRECT);
                cWAr[i] = '!';
            }
        }
        for (int i = 0; i < wordLength; i++) {
            for (int j = 0; j < wordLength; j++) {
                if (aWAr[i] == cWAr[j] && marks.get(i) == ABSENT) {
                    marks.set(i, Mark.PRESENT);
                    cWAr[j] = '!';
                }
            }
        }

        return marks;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(marks, feedback.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marks);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "marks=" + marks +
                "attempt: " + attempt +
                "}";
    }

}
