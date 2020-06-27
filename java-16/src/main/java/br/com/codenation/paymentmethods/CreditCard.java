package br.com.codenation.paymentmethods;

public class CreditCard implements PriceStrategy {

    @Override
    public Double calculate(Double price) {
        Double discountedValue = 0.98;
        return price * discountedValue;
    }

}
