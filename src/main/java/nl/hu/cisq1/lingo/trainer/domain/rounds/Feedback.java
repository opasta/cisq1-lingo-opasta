package nl.hu.cisq1.lingo.trainer.domain.rounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Feedback {
    private List<Mark> marks;
    private int attempt;

    public Feedback(List<Mark> marks){
        this.marks=marks;
    }

    public boolean isWordGuessed() {
        return this.marks.stream()
                .allMatch(Mark.CORRECT::equals);
    }

    public boolean isWordInvalid() {
        return this.marks.stream()
                .allMatch(Mark.INVALID::equals);
    }

    public List<Mark> giveHint(int attempt , String wordToGuess, String guessed) {
        List<String> separateGuessing = new ArrayList<String>(Arrays.asList(guessed.split("(?!^)")));
        List<String> separateCorrect = new ArrayList<String>(Arrays.asList(wordToGuess.split("(?!^)")));
        attempt += 1;
        Mark mark = Mark.INVALID;
        for (int counter = 0; counter < separateGuessing.size(); counter++){
            if (separateGuessing.get(counter).equals(separateCorrect.get(counter))){
                mark = Mark.CORRECT;
                //separateGuessing.remove(counter);
            }
            marks.add(mark);


        }

        //Loop werkt alleen voor correct & incorrect
        //marks.set(1, Mark.);





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
