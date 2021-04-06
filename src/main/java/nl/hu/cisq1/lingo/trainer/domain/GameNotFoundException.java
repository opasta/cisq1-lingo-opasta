package nl.hu.cisq1.lingo.trainer.domain;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(String message) {
        super(message);
    }

    public static GameNotFoundException withId(Long gameId) {
        String message = String.format("Game with id %d not found.", gameId);
        return new GameNotFoundException(message);
    }
    //404 error
}
