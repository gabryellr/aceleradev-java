package challenge;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Estacionamento {

    private static final int PONTUACAO_MAXIMA = 20;
    private static final int IDADE_PERMITIDA = 18;
    private static final int LIMITE_DE_VAGAS = 10;
    private static final int IDADE_SENIORIDADE = 55;

    private List<Carro> vagas = new ArrayList<>();

    public void estacionar(Carro carro) {
        validarInfoCarro(carro);
        validarMotorista(carro.getMotorista());

        verificarVagasDisponiveisEremover(carro);
    }

    public int carrosEstacionados() {
        return vagas.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return vagas.contains(carro);
    }

    private void validarInfoCarro(Carro carro) {
        if (carro.getPlaca().isEmpty()) {
            throw new NullPointerException();
        } else if (Objects.isNull(carro.getCor())) {
            throw new NullPointerException();
        } else if (Objects.isNull(carro.getMotorista())) {
            throw new EstacionamentoException("Carro sem motorista!");
        }
    }

    private void verificarVagasDisponiveisEremover(Carro carro) {
        if (vagas.size() >= LIMITE_DE_VAGAS) {
            verificarSenioridadeEremover();
        }
        vagas.add(carro);
    }

    private void verificarSenioridadeEremover() {
        Carro motoristaMenor55 = vagas.stream()
                .filter(car -> car.getMotorista().getIdade() < IDADE_SENIORIDADE)
                .findFirst()
                .orElseThrow(() -> new EstacionamentoException("Todos os motoristas tem mais de 55 anos!"));

        vagas.remove(motoristaMenor55);
    }

    private void validarMotorista(Motorista motorista) {
        if (Objects.isNull(motorista))
            throw new EstacionamentoException("Carro sem motorista não é autorizado estacionar!");

        if (motorista.getHabilitacao().isEmpty())
            throw new NullPointerException("Motorista sem habilitação!");

        if (motorista.getPontos() > PONTUACAO_MAXIMA)
            throw new EstacionamentoException("Limite de pontuação na carteira excedida!");

        if (motorista.getIdade() < IDADE_PERMITIDA)
            throw new EstacionamentoException("Motorista menor de " + IDADE_PERMITIDA + " anos");
    }

}
