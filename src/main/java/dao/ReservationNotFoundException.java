package dao;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(String message) {
        super(message);
    }


    public ReservationNotFoundException() {
    }

    public ReservationNotFoundException(Throwable cause) {
        super(cause);
    }

    public ReservationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ReservationNotFoundException(String message, Throwable cause) {
        super(message, cause);

    }


}
