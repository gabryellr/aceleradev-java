package br.com.codenation.paymentmethods;

public class Transfer implements PriceStrategy {

    @Override
    public Double calculate(Double price) {
        Double discountedValue = 0.92;
        return price * discountedValue;
    }

}
