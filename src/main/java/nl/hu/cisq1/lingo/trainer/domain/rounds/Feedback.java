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

    public Feedback(List<Mark> marks){
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

    public List<Mark> giveHint(String wordToGuess, String guessed) {
        List<Mark> marks2 = new ArrayList<Mark>();
        for (int i = 0; i < stringSplitter(wordToGuess).size(); i++){
            Mark mark = Mark.INVALID;
            if (stringSplitter(wordToGuess).get(i).equals(stringSplitter(guessed).get(i))){
                mark = Mark.CORRECT;
            }else if (null == null){
                //reden om te kijken of aanwezig
                mark = Mark.PRESENT;

            }
            marks2.add(mark);
            // geen idee waarom ik mark2 moet gebruiken

        }

        //Loop werkt alleen voor correct & incorrect
        //marks.set(1, Mark.);

        return marks2;
    }

    public List<String> stringSplitter(String wordToSplit) {
        List<String> splittedWord = new ArrayList<String>(Arrays.asList(wordToSplit.split("(?!^)")));
        return splittedWord;
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
