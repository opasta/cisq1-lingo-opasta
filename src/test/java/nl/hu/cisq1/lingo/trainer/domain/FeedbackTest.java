package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.rounds.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("Word is guessed if all letters are correct")
    void wordIsGuessed(){
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed if all letters are correct")
    void wordIsNotGuessed(){
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is invalid if all letters are invalid")
    void wordIsInvalid(){
        Feedback feedback = new Feedback(List.of(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID));
        assertTrue(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("Word is valid if not all letters are marked as invalid")
    void wordIsValid(){
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertFalse(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("Feedback is same if values are same")
    void feedbackIsSame(){
        Feedback feedbackA = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));
        Feedback feedbackB = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));

        assertEquals(feedbackA, feedbackB);
    }

    @Test
    @DisplayName("HashCode is generated based on values")
    void hashCodeGenerator(){
        Feedback feedbackA = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));
        Feedback feedbackB = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));

        assertEquals(feedbackA.hashCode(), feedbackB.hashCode());
    }

    @Test
    @DisplayName("ToString contains class name")
    void convertedToString(){
        Feedback feedbackA = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));
        assertFalse(feedbackA.toString().contains(Feedback.class.getName()));
    }

}