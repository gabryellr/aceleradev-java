package br.com.codenation;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DesafioMeuTimeApplicationTest {

    private DesafioMeuTimeApplication meusTimes;

    @Before
    public void setUp() {
        meusTimes = new DesafioMeuTimeApplication();
    }

    @Test
    public void incluirTime() {
        meusTimes.incluirTime(1L, "Meu Time", LocalDate.now(), "Branco", "Preto");
        assertThat(meusTimes.buscarNomeTime(1L)).isEqualTo("Meu Time");
    }

    @Test
    public void incluirTimeComIdDuplicado() {
        meusTimes.incluirTime(1L, "Meu Time", LocalDate.now(), "Branco", "Preto");

        assertThatThrownBy(() -> meusTimes
                .incluirTime(1L, "Meu Outro Time", LocalDate.now(), "Verde", "Amarelo"))
                .isExactlyInstanceOf(IdentificadorUtilizadoException.class);
    }

    @Ignore
    @Test
    public void incluirTimeComParametroNulo() {
        assertThatThrownBy(() -> meusTimes.incluirTime(null, "Meu Time", LocalDate.now(), "Branco", "Preto"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageEndingWith("Todos os parâmetros são obrigatórios");
        assertThatThrownBy(() -> meusTimes.incluirTime(1L, null, LocalDate.now(), "Branco", "Preto"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageEndingWith("Todos os parâmetros são obrigatórios");
        assertThatThrownBy(() -> meusTimes.incluirTime(1L, "Meu Time", null, "Branco", "Preto"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageEndingWith("Todos os parâmetros são obrigatórios");
        assertThatThrownBy(() -> meusTimes.incluirTime(1L, "Meu Time", LocalDate.now(), null, "Preto"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageEndingWith("Todos os parâmetros são obrigatórios");
        assertThatThrownBy(() -> meusTimes.incluirTime(1L, "Meu Time", LocalDate.now(), "Branco", null))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageEndingWith("Todos os parâmetros são obrigatórios");
    }

    @Test
    public void incluirJogador() {
        meusTimes.incluirTime(1L, "Meu Time", LocalDate.now(), "Branco", "Preto");
        meusTimes.incluirJogador(1L, 1L, "Jogador", LocalDate.now(), 95, BigDecimal.valueOf(200000));
        assertThat(meusTimes.buscarNomeJogador(1L)).isEqualTo("Jogador");
    }

    @Test
    public void incluirJogadorDuplicadoNoMesmoTime() {
        meusTimes.incluirTime(1L, "Meu Time", LocalDate.now(), "Branco", "Preto");
        meusTimes.incluirJogador(1L, 1L, "Jogador", LocalDate.now(), 95, BigDecimal.valueOf(200000));
        assertThatThrownBy(() -> meusTimes
                .incluirJogador(1L, 1L, "OutroJogador", LocalDate.now(), 90, BigDecimal.valueOf(250000)))
                .isExactlyInstanceOf(IdentificadorUtilizadoException.class);
    }

    @Test
    public void incluirJogadorDuplicadoEmOutroTime() {
        meusTimes.incluirTime(1L, "Meu Time A", LocalDate.now(), "Branco", "Preto");
        meusTimes.incluirJogador(1L, 1L, "Jogador", LocalDate.now(), 95, BigDecimal.valueOf(200000));
        meusTimes.incluirTime(2L, "Meu Time B", LocalDate.now(), "Vemelho", "Preto");
        assertThatThrownBy(() -> meusTimes
                .incluirJogador(1L, 2L, "OutroJogador", LocalDate.now(), 90, BigDecimal.valueOf(250000)))
                .isExactlyInstanceOf(IdentificadorUtilizadoException.class);
    }

    @Test
    public void incluirJogadorEmTimeInexistente() {
        assertThatThrownBy(() -> meusTimes
                .incluirJogador(1L, 1L, "Jogador", LocalDate.now(), 90, BigDecimal.valueOf(250000)))
                .isExactlyInstanceOf(TimeNaoEncontradoException.class);
    }

    @Ignore
    @Test
    public void incluirJogadorComParamentroNulo() {
        assertThatThrownBy(() -> meusTimes
                .incluirJogador(null, 1L, "Jogador", LocalDate.now(), 95, BigDecimal.valueOf(200000)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Todos os parâmetros são obrigatórios");
        assertThatThrownBy(() -> meusTimes
                .incluirJogador(1L, null, "Jogador", LocalDate.now(), 95, BigDecimal.valueOf(200000)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Todos os parâmetros são obrigatórios");
        assertThatThrownBy(() -> meusTimes
                .incluirJogador(1L, 1L, null, LocalDate.now(), 95, BigDecimal.valueOf(200000)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Todos os parâmetros são obrigatórios");
        assertThatThrownBy(() -> meusTimes
                .incluirJogador(1L, 1L, "Jogador", null, 95, BigDecimal.valueOf(200000)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Todos os parâmetros são obrigatórios");
        assertThatThrownBy(() -> meusTimes
                .incluirJogador(1L, 1L, "Jogador", LocalDate.now(), null, BigDecimal.valueOf(200000)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Todos os parâmetros são obrigatórios");
        assertThatThrownBy(() -> meusTimes
                .incluirJogador(1L, 1L, "Jogador", LocalDate.now(), 95, null))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Todos os parâmetros são obrigatórios");

    }

    @Test
    public void definirCapitao() {
        incluirTimeA();

        meusTimes.definirCapitao(8L);
        assertThat(meusTimes.buscarCapitaoDoTime(1L)).isEqualTo(8L);
        meusTimes.definirCapitao(5L);
        assertThat(meusTimes.buscarCapitaoDoTime(1L)).isEqualTo(5L);
    }

    @Test
    public void definirCapitaoInexistente() {
        incluirTimeA();

        assertThatThrownBy(() -> meusTimes.definirCapitao(15L))
                .isExactlyInstanceOf(JogadorNaoEncontradoException.class);
    }

    @Test
    public void buscarCapitaoDeTimeInexistente() {
        incluirTimeA();
        meusTimes.definirCapitao(5L);

        assertThatThrownBy(() -> meusTimes.buscarCapitaoDoTime(2L))
                .isExactlyInstanceOf(TimeNaoEncontradoException.class);
    }

    @Test
    public void buscarNomeJogadorInexistente() {
        incluirTimeA();

        assertThatThrownBy(() -> meusTimes.buscarNomeJogador(15L))
                .isExactlyInstanceOf(JogadorNaoEncontradoException.class);
    }

    @Test
    public void buscarNomeTimeInexistente() {
        incluirTimeA();

        assertThatThrownBy(() -> meusTimes.buscarNomeTime(2L))
                .isExactlyInstanceOf(TimeNaoEncontradoException.class);
    }

    @Test
    public void buscarJogadoresDoTime() {
        incluirTimeA();

        assertThat(meusTimes.buscarJogadoresDoTime(1L))
                .hasSize(11)
                .containsOnly(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L);
    }

    @Test
    public void buscarMelhorJogadorDoTime() {
        incluirTimeA();

        assertThat(meusTimes.buscarMelhorJogadorDoTime(1L)).isEqualTo(6L);
    }

    @Test
    public void buscarJogadorMaisVelho() {
        incluirTimeA();

        assertThat(meusTimes.buscarJogadorMaisVelho(1L)).isEqualTo(1L);
    }

    @Test
    public void buscarTimes() {
        meusTimes.incluirTime(1L, "Meu Time A", LocalDate.now(), "Branco", "Preto");
        meusTimes.incluirTime(2L, "Meu Time B", LocalDate.now(), "Verde", "Amarelo");
        meusTimes.incluirTime(3L, "Meu Time C", LocalDate.now(), "Preto", "Vermelho");

        assertThat(meusTimes.buscarTimes())
                .hasSize(3)
                .containsExactly(1L, 2L, 3L);
    }

    @Test
    public void buscarJogadorMaiorSalario() {
        incluirTimeA();

        assertThat(meusTimes.buscarJogadorMaiorSalario(1L)).isEqualTo(1L);
    }

    @Test
    public void voidBuscarSalarioDoJogador() {
        incluirTimeA();

        assertThat(meusTimes.buscarSalarioDoJogador(4L)).isEqualTo(BigDecimal.valueOf(180000));
    }

    @Test
    public void buscarTopJogadores() {
        incluirTimeA();
        incluirTimeB();

        assertThat(meusTimes.buscarTopJogadores(5))
                .hasSize(5)
                .containsExactly(6L, 11L, 1L, 9L, 12L);

    }

    @Test
    public void buscarCorCamisaTimeForaUniformePrincipalDiferente() {
        meusTimes.incluirTime(1L, "Meu Time A", LocalDate.now(), "Branco", "Preto");
        meusTimes.incluirTime(2L, "Meu Time B", LocalDate.now(), "Vermelho", "Azul");

        assertThat(meusTimes.buscarCorCamisaTimeDeFora(1L, 2L)).isEqualTo("Vermelho");
    }

    @Test
    public void buscarCorCamisaTimeForaUniformePrincipalIgual() {
        meusTimes.incluirTime(1L, "Meu Time A", LocalDate.now(), "Branco", "Preto");
        meusTimes.incluirTime(2L, "Meu Time B", LocalDate.now(), "Branco", "Azul");

        assertThat(meusTimes.buscarCorCamisaTimeDeFora(1L, 2L)).isEqualTo("Azul");
    }

    private void incluirTimeA() {
        meusTimes.incluirTime(1L, "Meu Time A",
                LocalDate.of(1930, Month.JANUARY, 25), "Branco", "Vermelho e preto");

        meusTimes.incluirJogador(1L, 1L, "Goleiro",
                LocalDate.of(1980, Month.JANUARY, 21), 95, BigDecimal.valueOf(450000));
        meusTimes.incluirJogador(2L, 1L, "Zagueiro A",
                LocalDate.of(1982, Month.MARCH, 12), 80, BigDecimal.valueOf(150000));
        meusTimes.incluirJogador(3L, 1L, "Zagueiro B",
                LocalDate.of(1997, Month.DECEMBER, 25), 89, BigDecimal.valueOf(250000));
        meusTimes.incluirJogador(4L, 1L, "Zagueio C",
                LocalDate.of(2002, Month.JULY, 8), 83, BigDecimal.valueOf(180000));
        meusTimes.incluirJogador(5L, 1L, "Zagueiro D",
                LocalDate.of(1994, Month.MAY, 29), 82, BigDecimal.valueOf(270000));
        meusTimes.incluirJogador(6L, 1L, "Lateral A",
                LocalDate.of(1996, Month.AUGUST, 27), 98, BigDecimal.valueOf(400000));
        meusTimes.incluirJogador(7L, 1L, "Lateral B",
                LocalDate.of(2004, Month.APRIL, 17), 90, BigDecimal.valueOf(300000));
        meusTimes.incluirJogador(8L, 1L, "Meio campo A",
                LocalDate.of(1995, Month.MARCH, 2), 87, BigDecimal.valueOf(200000));
        meusTimes.incluirJogador(9L, 1L, "Meio campo B",
                LocalDate.of(1980, Month.JANUARY, 21), 95, BigDecimal.valueOf(320000));
        meusTimes.incluirJogador(10L, 1L, "Centro avante",
                LocalDate.of(2001, Month.NOVEMBER, 14), 90, BigDecimal.valueOf(380000));
        meusTimes.incluirJogador(11L, 1L, "Atacante",
                LocalDate.of(1999, Month.OCTOBER, 16), 98, BigDecimal.valueOf(450000));
    }

    private void incluirTimeB() {
        meusTimes.incluirTime(2L, "Meu Time B",
                LocalDate.of(1910, Month.SEPTEMBER, 1), "Branco e preto", "Preto");

        meusTimes.incluirJogador(12L, 2L, "Goleiro",
                LocalDate.of(1980, Month.DECEMBER, 2), 92, BigDecimal.valueOf(310000));
        meusTimes.incluirJogador(13L, 2L, "Zagueiro A",
                LocalDate.of(1989, Month.JUNE, 27), 88, BigDecimal.valueOf(190000));
        meusTimes.incluirJogador(14L, 2L, "Zagueiro B",
                LocalDate.of(191, Month.MARCH, 9), 87, BigDecimal.valueOf(220000));
        meusTimes.incluirJogador(15L, 2L, "Zagueio C",
                LocalDate.of(1999, Month.AUGUST, 21), 87, BigDecimal.valueOf(195000));
        meusTimes.incluirJogador(16L, 2L, "Zagueiro D",
                LocalDate.of(1996, Month.SEPTEMBER, 23), 89, BigDecimal.valueOf(250000));
        meusTimes.incluirJogador(17L, 2L, "Lateral A",
                LocalDate.of(1995, Month.JULY, 23), 79, BigDecimal.valueOf(400000));
        meusTimes.incluirJogador(18L, 2L, "Lateral B",
                LocalDate.of(2001, Month.JANUARY, 12), 86, BigDecimal.valueOf(330000));
        meusTimes.incluirJogador(19L, 2L, "Meio campo A",
                LocalDate.of(1997, Month.NOVEMBER, 20), 81, BigDecimal.valueOf(205000));
        meusTimes.incluirJogador(20L, 2L, "Meio campo B",
                LocalDate.of(1987, Month.APRIL, 16), 90, BigDecimal.valueOf(305000));
        meusTimes.incluirJogador(21L, 2L, "Centro avante",
                LocalDate.of(2001, Month.OCTOBER, 1), 86, BigDecimal.valueOf(3450000));
        meusTimes.incluirJogador(22L, 2L, "Atacante",
                LocalDate.of(1997, Month.MAY, 7), 92, BigDecimal.valueOf(310000));
    }

    @Test
    public void buscarCapitaoNaoInformado() {
        incluirTimeA();

        assertThatThrownBy(() -> meusTimes.buscarCapitaoDoTime(1L))
                .isExactlyInstanceOf(CapitaoNaoInformadoException.class);
    }
}