package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.GameNotFoundException;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameService {
    private WordService wordService;
    private SpringGameRepository gameRepository;

    public GameService(WordService wordService, SpringGameRepository gameRepository) {
        this.wordService = wordService;
        this.gameRepository = gameRepository;
    }

    public Progress startNewGame() {
        //Complete
        String wordToGuess = this.wordService.provideRandomWord(5);

        Game game = new Game();
        game.startNewRound(wordToGuess);
        this.gameRepository.save(game);

        return game.showProgress();
    }

    public Progress startNewRound(Long gameId) {

        Game game = getGamebyId(gameId);

        Integer nextLength = game.getNextWordLength();
        String wordToGuess = this.wordService.provideRandomWord(nextLength);
        game.startNewRound(wordToGuess);
        this.gameRepository.save(game);

        return game.showProgress();
    }

    private Game getGamebyId(Long gameId) {
        return this.gameRepository.findById(gameId)
                .orElseThrow(()->GameNotFoundException.withId(gameId));
    }

    public Progress guess(Long gameId, String guessedWord) {

            Game game = this.gameRepository.findById(gameId)
                    .orElseThrow();
            //        .orElseThrow(new GameNotFoundException.withId(gameId));
            //vraag over de withId, die je wilt gooien

            //word eerst nog valideren??


            game.guess(guessedWord);
            this.gameRepository.save(game);

            if (game.getGameStatus().equals(GameStatus.WIN)){
                //score berekenen
            }


            return game.showProgress();

    }
}
