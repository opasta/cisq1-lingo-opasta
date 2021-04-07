package nl.hu.cisq1.lingo.trainer.domain.exceptions;

import nl.hu.cisq1.lingo.trainer.domain.GameStatus;

public class InvalidAction extends IllegalArgumentException{
    public InvalidAction(String message) {
        super(message);
    }

    public static InvalidAction cannotGuessWord(GameStatus status) {
        String message = String.format("Game with status %s not allowed.", status);
        return new InvalidAction(message);
    }
}
