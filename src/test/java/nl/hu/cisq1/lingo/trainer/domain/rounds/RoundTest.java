package nl.hu.cisq1.lingo.trainer.domain.rounds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.rounds.Mark.ABSENT;
import static nl.hu.cisq1.lingo.trainer.domain.rounds.Mark.CORRECT;
import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    Round round = new Round("tester");

    @Test
    @DisplayName("Word is guessed")
    void wordIsGuessed() {
        round.guess("tester");
        assertTrue(round.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed")
    void wordIsNotGuessed() {
        round.guess("takken");
        assertFalse(round.isWordGuessed());
    }



    @ParameterizedTest
    @DisplayName("Calculate score")
    @MethodSource("calculate")
    void calculator(int score, int length) {
        for (int i = 0; i < length; i++) {
            round.guess("gokker");
        }
        assertEquals(round.calculateScore(), score);
    }

    static Stream<Arguments> calculate() {
        return Stream.of(
                Arguments.of(25, 1),
                Arguments.of(20, 2),
                Arguments.of(15, 3),
                Arguments.of(10, 4),
                Arguments.of(5, 5)
        );
    }

    @Test
    @DisplayName("Empty Round is same as empty Round")
    void emptyRound() {
        Round roundA = new Round();
        assertEquals(round, roundA);
    }
}