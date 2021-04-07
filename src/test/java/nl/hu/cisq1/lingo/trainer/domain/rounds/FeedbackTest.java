package nl.hu.cisq1.lingo.trainer.domain.rounds;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.rounds.Mark.*;
import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
     @Test
    @DisplayName("Word is guessed if all letters are correct")
    void wordIsGuessed() {
        Feedback feedback = new Feedback("woord", List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT));
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed if all letters are correct")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback("woord",List.of(CORRECT, ABSENT, CORRECT, CORRECT, CORRECT));
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is invalid if all letters are ABSENT")
    void wordIsInvalid() {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT));
        assertTrue(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("Word is valid if not all letters are marked as invalid")
    void wordIsValid() {
        Feedback feedback = new Feedback("woord", List.of(CORRECT, ABSENT, CORRECT, CORRECT, CORRECT));
        assertFalse(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("Feedback is same if values are same")
    void feedbackIsSame() {
        Feedback feedbackA = new Feedback("woord", List.of(CORRECT, CORRECT));
        Feedback feedbackB = new Feedback("woord", List.of(CORRECT, CORRECT));
        assertEquals(feedbackA, feedbackB);
    }

    @Test
    @DisplayName("Empty Feedback is same as empty feedback")
    void emptyFeedback() {
        Feedback feedbackA = new Feedback();
        Feedback feedbackB = new Feedback();
        assertEquals(feedbackA, feedbackB);
    }

    @Test
    @DisplayName("HashCode is generated based on values")
    void hashCodeGenerator() {
        Feedback feedbackA = new Feedback("woord", List.of(CORRECT, CORRECT));
        Feedback feedbackB = new Feedback("woord", List.of(CORRECT, CORRECT));
        assertEquals(feedbackA.hashCode(), feedbackB.hashCode());
    }

    @Test
    @DisplayName("ToString contains class name")
    void convertedToString() {
        Feedback feedbackA = new Feedback("woord", List.of(CORRECT, CORRECT));
        assertFalse(feedbackA.toString().contains(Feedback.class.getName()));
    }

    @ParameterizedTest
    @DisplayName("give hint based on attempt, marks and previous hint")
    @MethodSource("hintExamples")
    void giveHint(String attempt, List<Mark> marks, String previousHint, String expected) {
        Feedback feedback = new Feedback(attempt, marks);
        String hint = feedback.giveHint(previousHint);
        assertEquals(expected, hint);
    }

    static Stream<Arguments> hintExamples() {
        return Stream.of(
                Arguments.of("woord", List.of(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT), "w....", "w...."),
                Arguments.of("woord", List.of(CORRECT, PRESENT, ABSENT, ABSENT, ABSENT), "wo...", "wo..."),
                Arguments.of("woord", List.of(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT), "wo...", "wo..."),
                Arguments.of("woord", List.of(CORRECT, CORRECT, CORRECT, ABSENT, ABSENT), "wo...", "woo.."),
                Arguments.of("woord", List.of(CORRECT, CORRECT, CORRECT, ABSENT, CORRECT), "wo...", "woo.d")
        );
    }

    @ParameterizedTest
    @DisplayName("give marks, with correctword and the attempt")
    @MethodSource("wordExamples")
    void giveMarks(String attempt, List<Mark> marksExpected, String correctWord) {
        Feedback feedback = new Feedback();
        List<Mark> marks = feedback.giveMarks(attempt, correctWord);
        assertEquals(marksExpected, marks);
    }

    static Stream<Arguments> wordExamples() {
        return Stream.of(

                Arguments.of("waait", List.of(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT), "woord"),
                Arguments.of("wierp", List.of(CORRECT, ABSENT, ABSENT, CORRECT, ABSENT), "woord"),
                Arguments.of("wroeg", List.of(CORRECT, PRESENT, CORRECT, ABSENT, ABSENT), "woord"),
                Arguments.of("worst", List.of(CORRECT, CORRECT, PRESENT, ABSENT, ABSENT), "woord"),
                Arguments.of("ooooo", List.of(ABSENT, CORRECT, CORRECT, ABSENT, ABSENT), "woord"),
                Arguments.of("woord", List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT), "woord")
        );
    }




}
