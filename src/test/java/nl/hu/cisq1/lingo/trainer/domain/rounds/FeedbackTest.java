package nl.hu.cisq1.lingo.trainer.domain.rounds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
    FeedbackTest() {
    }

    @Test
    @DisplayName("Word is guessed if all letters are correct")
    void wordIsGuessed() {
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        Assertions.assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed if all letters are correct")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        Assertions.assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is invalid if all letters are invalid")
    void wordIsInvalid() {
        Feedback feedback = new Feedback(List.of(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID));
        Assertions.assertTrue(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("Word is valid if not all letters are marked as invalid")
    void wordIsValid() {
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        Assertions.assertFalse(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("Feedback is same if values are same")
    void feedbackIsSame() {
        Feedback feedbackA = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));
        Feedback feedbackB = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));
        Assertions.assertEquals(feedbackA, feedbackB);
    }

    @Test
    @DisplayName("HashCode is generated based on values")
    void hashCodeGenerator() {
        Feedback feedbackA = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));
        Feedback feedbackB = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));
        Assertions.assertEquals(feedbackA.hashCode(), feedbackB.hashCode());
    }

    @Test
    @DisplayName("ToString contains class name")
    void convertedToString() {
        Feedback feedbackA = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));
        Assertions.assertFalse(feedbackA.toString().contains(Feedback.class.getName()));
    }

    @Test
    @DisplayName("Give a hint on a guessed word")
    void giveHint() {
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        //feedback.giveHint("tester", "tester");
        Assertions.assertTrue(feedback.isWordGuessed());

        //Assertions.assertTrue(feedback.isWordGuessed());
    }

//    @ParameterizedTest
//    @MethodSource("provideHintExamples ")
//    static Stream<Arguments> provideHintExamples() {
//        return Stream.of(Arguments.of(A, B, C),Arguments.of(D, E, F));
//    }
}
