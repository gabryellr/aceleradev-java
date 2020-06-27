package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object object) {
        return calcular(object, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object object) {
        return calcular(object, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object object) {
        return somar(object).subtract(subtrair(object));
    }

    private BigDecimal calcular(Object classe, Class anotacao) {
        BigDecimal result = BigDecimal.ZERO;

        for (Field field : classe.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(anotacao) && field.getType().equals(BigDecimal.class)) {
                try {
                    result = result.add((BigDecimal) field.get(classe));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

}
