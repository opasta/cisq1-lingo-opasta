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

    public Long startnewGame() {
        Game game = new Game();
        this.gameRepository.save(game);
        return game.getId();
    }

    public Progress startNewRound(Long id) {
        if (gameRepository.findbyId(id).isPresent()){
            Game game = this.gameRepository.findbyId(id);
            //lengte op een of andere manier ophalen, en hieronder in de getNextWordLength  plakken
            String wordToGuess = this.wordService.provideRandomWord(game.getNextWordLength());
            try{
                game.startNewRound(wordToGuess);
                return game.getProgress();
            } catch (Exception e){
                throw e;
            }
        }
        else {
            throw new GameNotFoundException();
        }
    }

    public Progress guessWord(Long id, String guessedWord) {
        if (gameRepository.findbyId(id).isPresent()){
            Game game = this.gameRepository.findbyId(id);
            game.guess(guessedWord);
            return game.getProgress();
        }
        else  {
            throw new GameNotFoundException();
        }

    }

    public Progress findbyId(Long id) {
        Game game = this.gameRepository.findbyId(id);
        return game.getProgress();
    }

    public Progress findByStatus(GameStatus status) {
        List<Game> game = this.gameRepository.findByStatus(status);
        return game.get(0).getProgress();
    }
}
