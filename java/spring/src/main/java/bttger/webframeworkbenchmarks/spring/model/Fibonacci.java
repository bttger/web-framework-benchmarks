package bttger.webframeworkbenchmarks.spring.model;

public class Fibonacci {

    public static int getFibonacciNumber(int number) {
        if (number <= 1) return 1;
        return getFibonacciNumber(number - 1) + getFibonacciNumber(number - 2);
    }

}
