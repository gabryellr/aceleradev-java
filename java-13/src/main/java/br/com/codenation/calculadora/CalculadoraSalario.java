package br.com.codenation.calculadora;


public class CalculadoraSalario {

    public long calcularSalarioLiquido(double salarioBase) {
        if (salarioBase < 1039) {
            return 0;
        }
        double salarioComDesconto = calcularDescontos(salarioBase);
        return Math.round(calculaIRRF(salarioComDesconto));
    }

    private double calculaIRRF(double salarioComDescontoInss) {
        if (salarioComDescontoInss > 3000 && salarioComDescontoInss <= 6000) {
            return calcularPrimeiraFaixaIRRF(salarioComDescontoInss);
        } else if (salarioComDescontoInss > 6000) {
            return calcularSegundaFaixaIRRF(salarioComDescontoInss);
        } else {
            return salarioComDescontoInss;
        }
    }

    private double calcularDescontos(double salarioBase) {
        if (salarioBase <= 1500) {
            return calcularPrimeiraFaixaINSS(salarioBase);
        } else if (salarioBase <= 4000) {
            return calcularSegundaFaixaINSS(salarioBase);
        } else {
            return calcularTerceiraFaixaINSS(salarioBase);
        }
    }

    private double calcularTerceiraFaixaINSS(double inss) {
        return inss - (inss * 0.11);
    }

    private double calcularSegundaFaixaINSS(double inss) {
        return inss - (inss * 0.09);
    }

    private double calcularPrimeiraFaixaINSS(double inss) {
        return inss - (inss * 0.08);
    }

    private double calcularSegundaFaixaIRRF(double irrf) {
        return irrf - (irrf * 0.15);
    }

    private double calcularPrimeiraFaixaIRRF(double irrf) {
        return irrf - (irrf * 0.075);
    }

}