package bttger.webframeworkbenchmarks.spring.model;

public class GetCalculated {
    private final int fibonacci;

    public GetCalculated(int fibonacci) {
        this.fibonacci = fibonacci;
    }

    public int getFibonacci() {
        return fibonacci;
    }
}
