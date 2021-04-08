package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exceptions.ActionNotAllowedException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.InvalidAction;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game = new Game();

    @ParameterizedTest
    @DisplayName("Calculate next word length")
    @MethodSource("getNextWordLength")
    void giveLength(String attempt, int expected) {

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
    void invalidAction() {
        game.startNewRound("tester");
        game.guess("tester");

        assertThrows(InvalidAction.class,
                ()->{game.guess("takken");} );

    }

    @Test
    @DisplayName("cannot start a new round when statis is not WAITING_FOR_ROUND")
    void actionNotAllowed() {
        game.startNewRound("tester");

        assertThrows(ActionNotAllowedException.class,
                ()->{game.startNewRound("woordje");;} );

    }

    @Test
    @DisplayName("GameStatus is by default WAITING_FOR_ROUND")
    void gameStatus() {
        Game gameA = new Game();
        assertEquals(game.getGameStatus(), gameA.getGameStatus());
    }

}