package nl.hu.cisq1.lingo.trainer.domain;

public class GameNotFoundException extends RuntimeException{
    public static <X extends Throwable> Long withId(Long gameId) {
        return gameId;
    }
}
