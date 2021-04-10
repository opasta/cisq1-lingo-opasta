package nl.hu.cisq1.lingo.trainer.domain.exceptions;


public class IncorrectAttemptLength extends IllegalArgumentException{
    public IncorrectAttemptLength(String message) {
        super(message);
    }

    public static IncorrectAttemptLength wordLenght(int id) {
        String message = String.format("input with length %s not allowed.", id);
        return new IncorrectAttemptLength(message);
    }
}
