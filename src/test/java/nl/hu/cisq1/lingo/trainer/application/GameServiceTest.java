package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.rounds.Feedback;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.rounds.Mark.CORRECT;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {


    @Test
    void startNewGame() {
        WordService wordService = mock(WordService.class);
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        GameService gameService = new GameService(wordService, gameRepository);
        when(wordService.provideRandomWord(anyInt())).thenReturn("appel");

        System.out.println(gameService.startNewGame());
        //assertEquals(gameService.startNewGame(), GameService.class);
    }

    @Test

    //need to mock default id's for everything
    void startNewRound() {
        WordService wordService = mock(WordService.class);
        SpringGameRepository gameRepository = mock(SpringGameRepository.class);
        GameService gameService = new GameService(wordService, gameRepository);
        when(wordService.provideRandomWord(anyInt())).thenReturn("appel");

        //System.out.println(gameService.startNewRound(1L));
    }

    @Test
    void guess() {
    }
}