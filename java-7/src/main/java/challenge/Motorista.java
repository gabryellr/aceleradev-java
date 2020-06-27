package challenge;


import java.util.Objects;

public class Motorista {

    private final String nome;

    private final int idade;

    private final int pontos;

    private final String habilitacao;

    private Motorista(String nome, int idade, int pontos, String habilitacao) {
        this.nome = nome;
        this.idade = idade;
        this.pontos = pontos;
        this.habilitacao = habilitacao;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getPontos() {
        return pontos;
    }

    public String getHabilitacao() {
        return habilitacao;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Motorista motorista = (Motorista) o;
        return Objects.equals(habilitacao, motorista.habilitacao);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(habilitacao);
    }

    @Override
    public String toString() {
        return "Motorista{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", pontos=" + pontos +
                ", habilitacao='" + habilitacao + '\'' +
                '}';
    }

    public static MotoristaBuilder builder() {
        return new MotoristaBuilder();
    }


    public static class MotoristaBuilder {

        private static final int PONTUACAO_MINIMA = 0;
        private String nome;

        private int idade;

        private int pontos;

        private String habilitacao;

        private MotoristaBuilder() {
        }

        public MotoristaBuilder withNome(String nome) {
            validarNome(nome);
            this.nome = nome;
            return this;
        }

        public MotoristaBuilder withIdade(int idade) {
            validarIdade(idade);
            this.idade = idade;
            return this;
        }

        public MotoristaBuilder withPontos(int pontos) {
            validarPontuacao(pontos);
            this.pontos = pontos;
            return this;
        }

        public MotoristaBuilder withHabilitacao(String habilitacao) {
            this.habilitacao = habilitacao;
            return this;
        }


        public Motorista build() {
            validarNome(nome);
            validarIdade(idade);
            validarHabilitacao(habilitacao);
            return new Motorista(nome, idade, pontos, habilitacao);
        }

        private void validarIdade(int idade) {
            if (idade < 0) throw new IllegalArgumentException("Idade menor que zero não permitida!");
        }

        private void validarNome(String nome) {
            if (nome.isEmpty()) throw new NullPointerException("Impossivel criar motorista sem nome!");
        }

        private void validarHabilitacao(String habilitacao) {
            if (habilitacao.isEmpty()) throw new NullPointerException("Impossivel criar motorista sem habilitação!");
        }

        private void validarPontuacao(int pontos) {
            if (pontos < PONTUACAO_MINIMA)
                throw new IllegalArgumentException("Não pode ter habilitação com a pontuação negativa!");
        }
    }

}
