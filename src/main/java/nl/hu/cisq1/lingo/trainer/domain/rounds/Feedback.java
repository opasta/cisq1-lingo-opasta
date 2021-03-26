package nl.hu.cisq1.lingo.trainer.domain.rounds;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
                .allMatch(Mark.INVALID::equals);
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
