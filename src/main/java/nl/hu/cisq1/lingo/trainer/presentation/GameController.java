package nl.hu.cisq1.lingo.trainer.presentation;


import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.presentation.dto.GuessDto;
import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.GameNotFoundException;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/trainer/game")
public class GameController {
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public Progress newGame() {
        return this.gameService.startNewGame();
    }

    @PostMapping("/{id}/guess")
    public Progress guessWord(@PathVariable Long id, @Valid @RequestBody GuessDto dto) {
        try {
            return this.gameService.guess(id, dto.word);
        } catch (GameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/{id}/newRound")
    public Progress newRound(@PathVariable Long id) {
        try {
            return this.gameService.startNewRound(id);
        } catch (GameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public Game getCurrent(@PathVariable Long id) {
        try {
            return this.gameService.getGamebyId(id);
        } catch (GameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}