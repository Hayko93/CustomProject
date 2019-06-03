package am.client.market.exceptions;

public class DataBaseException extends Exception {
    private static final long serialVersionUID = 3388786973124545454L;


    private DataBaseException() {
        super();
    }

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseException(Throwable cause) {
        super(cause);
    }

    protected DataBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
