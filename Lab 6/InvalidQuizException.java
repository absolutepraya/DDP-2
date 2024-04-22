import java.io.IOException;

public class InvalidQuizException extends IOException {
    public InvalidQuizException(String message) {
        super(message);
    }
}