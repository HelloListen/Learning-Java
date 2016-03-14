package springinaction;

/**
 * Created by zhangbin on 16/3/14.
 */
public interface Performer {
    void perform() throws PerformanceException;
}

class PerformanceException extends Exception {
    public PerformanceException(String message) {
        super(message);
    }

    public PerformanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
