package bttger.webframeworkbenchmarks.spring.model;

public class GetQueryResultError {
    private final int code;
    private final String message;

    public GetQueryResultError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
