package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

    public static final int FIBONACCI_LIMIT = 350;

    public static List<Integer> fibonacci() {
        List<Integer> fibonacciList = new ArrayList<>();

        fibonacciList.add(0);
        fibonacciList.add(1);

        int count = 0;
        int fibonnaciNumber = 0;

        while (fibonnaciNumber <= FIBONACCI_LIMIT) {
            int firstNumber = fibonacciList.get(count);
            int secondNumber = fibonacciList.get(count + 1);
            fibonnaciNumber = firstNumber + secondNumber;
            fibonacciList.add(fibonnaciNumber);

            count++;
        }
        return fibonacciList;
    }

    public static Boolean isFibonacci(Integer a) {
        return fibonacci().contains(a);
    }

}