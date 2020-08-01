package bttger.webframeworkbenchmarks.spring.model;

public class QueryError extends Exception {

    private final int code;

    public QueryError(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
