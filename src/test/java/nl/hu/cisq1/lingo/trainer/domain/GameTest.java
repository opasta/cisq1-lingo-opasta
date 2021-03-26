package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.rounds.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.rounds.Mark.*;
import static nl.hu.cisq1.lingo.trainer.domain.rounds.Mark.CORRECT;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void startNewRound() {
    }

    @ParameterizedTest
    @DisplayName("Calculate next word length")
    @MethodSource("getNextWordLength")
    void giveLength(String attempt, int expected) {
        Game game = new Game();
        game.startNewRound(attempt);
        assertEquals(game.getNextWordLength(), expected);
    }

    static Stream<Arguments> getNextWordLength() {
        return Stream.of(
                Arguments.of("bieren", 7),
                Arguments.of("woord", 6),
                Arguments.of("aap", 5),
                Arguments.of("biertje", 5)
        );
    }

    @Test
    void guess() {
    }

    @Test
    void showProgress() {
    }

    @Test
    void isPlayerEliminated() {
    }

    @Test
    void isPlaying() {
    }

    @Test
    void isPresent() {
    }

    @Test
    void getId() {
    }

    @Test
    void getScore() {
    }

    @Test
    void getGameStatus() {
    }

    @Test
    void getRounds() {
    }
}