package nl.hu.cisq1.lingo.trainer.domain.exceptions;

import nl.hu.cisq1.lingo.trainer.domain.GameStatus;

public class ActionNotAllowedException extends RuntimeException {
    public ActionNotAllowedException(String message) {
        super(message);
    }

    public static ActionNotAllowedException withStatus(GameStatus status) {
        String message = String.format("Gamestatus %s not allowed.", status);
        return new ActionNotAllowedException(message);
    }
}
