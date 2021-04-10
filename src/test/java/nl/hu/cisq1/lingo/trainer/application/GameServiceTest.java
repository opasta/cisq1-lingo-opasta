package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.GameNotFoundException;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    @Test
    @DisplayName("throws an exception if game not found")
    void gameNotFound() {
        WordService wordService = mock(WordService.class);
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        GameService gameService = new GameService(wordService, gameRepository);
        assertThrows(GameNotFoundException.class, () -> gameService.startNewRound(0L));
        assertThrows(GameNotFoundException.class, () -> gameService.guess(0L, ""));
    }

    @Test
    @DisplayName("starting a new game, so status is playing")
    void startGame() {
        WordService wordService = mock(WordService.class);
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        GameService gameService = new GameService(wordService, gameRepository);
        when(wordService.provideRandomWord(anyInt())).thenReturn("appel");

        Progress progress = gameService.startNewGame();
        assertEquals(progress.getStatus(), GameStatus.PLAYING);
    }

    @Test
    @DisplayName("starting a new round, so length of rounds should increase by 1")
    void startRound() {
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        WordService wordService = mock(WordService.class);
        GameService gameService = new GameService(wordService, gameRepository);
        when(wordService.provideRandomWord(anyInt())).thenReturn("appel");

        Game game = new Game();
        game.setId(1L);
        game.startNewRound("tester");
        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
        gameService.startNewRound(1L);
        assertEquals(game.getRounds().size(), 2);

    }
    @Test
    @DisplayName("when i get a game by id, I should get a game; and the Id should be equal to the one given")
    void getById() {
        WordService wordService = mock(WordService.class);
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        GameService gameService = new GameService(wordService, gameRepository);

        Game game = new Game();
        game.startNewRound("tester");
        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));

        assertEquals(gameService.getGamebyId(1L), game);
    }

    @ParameterizedTest
    @DisplayName("when I guess, feedbackhistory should increase")
    @MethodSource("guessArguments")
    void guess(int timesAttempt, int lengthFeedback, GameStatus gameStatus) {
        WordService wordService = mock(WordService.class);
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        GameService gameService = new GameService(wordService, gameRepository);

        Game game = new Game();
        game.startNewRound("tester");
        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
        Progress progress = new Progress();


        for (int i = 0; i < timesAttempt; i++) {
            progress = gameService.guess(1L, "aapjes");
        }

        assertEquals(gameStatus, progress.getStatus());
        assertEquals(progress.getRounds().get(0).getAttemptCount(), lengthFeedback);
    }

    static Stream<Arguments> guessArguments() {
        return Stream.of(
                Arguments.of(1, 1, GameStatus.PLAYING),
                Arguments.of(2, 2, GameStatus.PLAYING),
                Arguments.of(3, 3, GameStatus.PLAYING),
                Arguments.of(4, 4, GameStatus.PLAYING),
                Arguments.of(5, 5, GameStatus.ELIMINATED)
        );
    }
}