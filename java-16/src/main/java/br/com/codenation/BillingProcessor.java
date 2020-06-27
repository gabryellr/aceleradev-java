package br.com.codenation;

import br.com.codenation.paymentmethods.PaymentMethod;

import static java.util.Optional.ofNullable;

public class BillingProcessor {

    public Double calculate(Order order) {
        return ofNullable(order.getPaymentMethod())
                .map(PaymentMethod::getPaymentStrategy)
                .orElseThrow(() -> new RuntimeException("Payment method not implemented"))
                .calculate(order.getPrice());
    }

}