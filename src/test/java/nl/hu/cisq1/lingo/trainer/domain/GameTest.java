package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exceptions.ActionNotAllowedException;
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
    void gameStatus(int numberofLoops, String attempt, GameStatus expected) {
        game.startNewRound("tester");
        for (int i = 0; i < numberofLoops; i++) {
            game.guess(attempt);
        }
        assertEquals(game.getGameStatus(), expected);
    }

    static Stream<Arguments> guessStatus() {
        return Stream.of(
                Arguments.of(0, "takken", GameStatus.PLAYING),
                Arguments.of(1, "tester", GameStatus.WIN),
                Arguments.of(1, "takken", GameStatus.PLAYING),
                Arguments.of(5, "takken", GameStatus.ELIMINATED)
        );
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


    @Test
    @DisplayName("id getter is same as id getter for GAME")
    void idGetterGame() {
        Game gameA = new Game();
        assertEquals(game.getId(), gameA.getId());
    }

    @Test
    @DisplayName("round getter is same as round getter")
    void roundGetter() {
        Game gameA = new Game();
        assertEquals(game.getRounds(), gameA.getRounds());
    }

    @Test
    @DisplayName("game showProgress is equal to empty showProgress")
    void showProgress() {
        Game gameA = new Game();
        assertEquals(game.showProgress().toString(), gameA.showProgress().toString());
    }

    @Test
    @DisplayName("game showProgress is equal to empty showProgress, also when playingGame")
    void showProgressPlaying() {
        Game gameA = new Game();
        gameA.startNewRound("aapje");
        game.startNewRound("aapje");
        assertEquals(game.showProgress().toString(), gameA.showProgress().toString());
    }

    @Test
    @DisplayName("set Id test")
    void setId() {
        game.setId(1L);
        assertEquals(game.getId(), 1L);
    }



}