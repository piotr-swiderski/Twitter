package controllers.error;

public class ValidationError {

    private String message;
    private String header;

    public ValidationError(String message, String header) {
        this.message = message;
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
