package br.com.codenation.paymentmethods;

public class DebitCard implements PriceStrategy {

    @Override
    public Double calculate(Double price) {
        Double discountedValue = 0.95;
        return price * discountedValue;
    }

}
