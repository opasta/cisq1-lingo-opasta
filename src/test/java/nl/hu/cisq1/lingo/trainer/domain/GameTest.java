package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exceptions.ActionNotAllowedException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.InvalidAction;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Mark;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Round;
import org.junit.jupiter.api.Assertions;
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

    @ParameterizedTest
    @DisplayName("different gamestatusses")
    @MethodSource("guessStatus")
    void gameStatus(String attempt, GameStatus expected) {
        Game game = new Game();
        game.startNewRound("tester");
        game.guess(attempt);
        assertEquals(game.getGameStatus(), expected);
    }

    static Stream<Arguments> guessStatus() {
        return Stream.of(
                Arguments.of("tester", GameStatus.WIN),
                Arguments.of("takken", GameStatus.PLAYING)
        );
    }

    @Test
    @DisplayName("Gamestatus is not waiting for round, so it will fail")
    //regels 58 - 60 in Game.java
    void emptyFeedback() {
        Game game = new Game();
        game.startNewRound("tester");
        game.guess("tester");
        game.guess("takken");

        Assertions.assertThrows(ActionNotAllowedException.class, () -> {

        });

    }

    @ParameterizedTest
    @DisplayName("is word guessed")
    @MethodSource("guessWord")
    void wordGuessed(String attempt, Boolean win) {
        Round round = new Round("tester");
        round.guess(attempt);
        assertEquals(round.isWordGuessed(), win);
    }

    static Stream<Arguments> guessWord() {
        return Stream.of(
                Arguments.of("tester", true),
                Arguments.of("takken", false)
        );
    }


}