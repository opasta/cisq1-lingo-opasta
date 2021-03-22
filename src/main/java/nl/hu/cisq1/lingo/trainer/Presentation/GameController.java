package nl.hu.cisq1.lingo.trainer.Presentation;


import nl.hu.cisq1.lingo.trainer.Presentation.Dto.GuessDto;
import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.domain.GameNotFoundException;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.trainer.domain.RoundNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/game")
public class GameController {
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public Progress startRound() {
        try {
            Progress progress = this.gameService.findByStatus(GameStatus.WAITING_FOR_ROUND);
            return progress;
        } catch (RoundNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already round started");
        }
    }

    @PostMapping("/guessWord")
    public Progress guessWord(@Valid @RequestBody GuessDto dto) {
        try {
            Progress progress = this.gameService.findByStatus(GameStatus.PLAYING);
            return progress;
        } catch (GameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No word to guess");
        }
    }
}