package br.com.codenation;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.jogador.Jogador;
import br.com.codenation.time.Time;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    private List<Time> times = new ArrayList<>();
    private List<Jogador> jogadores = new ArrayList<>();

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        verificaTimeComMesmoId(id);

        Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
        times.add(time);
    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        verificaJogadorComMesmoId(id);
        verificaSeExisteOTimeComId(idTime);

        Time time = buscaTimePorId(idTime);

        if (nivelHabilidade < 0 || nivelHabilidade > 100) {
            throw new IllegalArgumentException("Nivel de habilidade não pode ser menor que zero ou maior que cem!");
        }

        Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
        time.addJogador(jogador);
        jogadores.add(jogador);
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {
        Jogador jogadorEncontrado = buscarJogadorPorId(idJogador);
        Time time = verificaTimeDoJogadorPorIdTime(jogadorEncontrado.getIdTime());

        time.setCapitao(jogadorEncontrado);
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {
        Time timeEncontrado = buscaTimePorId(idTime);

        Jogador capitao = timeEncontrado.getCapitao();
        if (Objects.isNull(capitao))
            throw new CapitaoNaoInformadoException("Time não possui um capitão!");

        return capitao.getId();
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        Jogador jogadorEncontrado = buscarJogadorPorId(idJogador);

        return jogadorEncontrado.getNome();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {
        Time timeEncontrado = buscaTimePorId(idTime);

        return timeEncontrado.getNome();
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        Time timeEncontrado = buscaTimePorId(idTime);

        return timeEncontrado.getJogadores().stream()
                .map(Jogador::getId)
                .sorted()
                .collect(toList());
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        Time timeEncontrado = times.stream()
                .filter(time -> time.getId().equals(idTime))
                .findFirst()
                .orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado com o id: " + idTime));

        return timeEncontrado.getJogadores().stream()
                .min(comparing(Jogador::getNivelHabilidade).reversed()
                        .thenComparing(Jogador::getId))
                .map(Jogador::getId)
                .orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado!"));
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        Time timeEncontrado = buscaTimePorId(idTime);

        return timeEncontrado.getJogadores().stream()
                .min(comparing(Jogador::getDataNascimento).thenComparing(Jogador::getId))
                .map(Jogador::getId)
                .orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado!"));
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        return times.stream()
                .sorted(comparing(Time::getId))
                .map(Time::getId)
                .collect(toList());
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        Time timeEncontrado = buscaTimePorId(idTime);

        return timeEncontrado.getJogadores()
                .stream().max(comparing(Jogador::getSalario))
                .map(Jogador::getId)
                .orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não existe!"));
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        return jogadores.stream()
                .filter(jogador -> jogador.getId().equals(idJogador))
                .findFirst()
                .map(Jogador::getSalario)
                .orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado!"));
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        List<Jogador> topJogadores = jogadores.stream()
                .sorted(comparingInt(Jogador::getNivelHabilidade).reversed())
                .limit(top)
                .collect(toList());

        return topJogadores.stream()
                .sorted(comparing(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getId))
                .map(Jogador::getId)
                .collect(toList());
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        Time timeCasa = buscaTimePorId(timeDaCasa);
        Time timeFora = buscaTimePorId(timeDeFora);

        if (timeCasa.getCorUniformePrincipal().equalsIgnoreCase(timeFora.getCorUniformePrincipal())) {
            return timeFora.getCorUniformeSecundario();
        }
        return timeFora.getCorUniformePrincipal();
    }

    private void verificaTimeComMesmoId(Long id) {
        boolean timeExistente = times.stream()
                .anyMatch(time -> time.getId().equals(id));

        if (timeExistente) throw new IdentificadorUtilizadoException("Esse id já existe cadastrado!" + id);
    }

    private void verificaJogadorComMesmoId(Long id) {
        boolean jogadorJaExiste = jogadores.stream()
                .anyMatch(jogador -> jogador.getId().equals(id));

        if (jogadorJaExiste) throw new IdentificadorUtilizadoException("Id já existente" + id);
    }

    private void verificaSeExisteOTimeComId(Long idTime) {
        boolean timeExiste = times.stream()
                .anyMatch(time -> time.getId().equals(idTime));

        if (!timeExiste) throw new TimeNaoEncontradoException("Time não encontrado com o id: " + idTime);
    }

    private Jogador buscarJogadorPorId(Long idJogador) {
        return jogadores.stream()
                .filter(jogador -> jogador.getId().equals(idJogador))
                .findAny()
                .orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado com o id: " + idJogador));
    }

    private Time verificaTimeDoJogadorPorIdTime(Long idTime) {
        return times.stream()
                .filter(time -> time.getId().equals(idTime))
                .findFirst()
                .orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado!"));
    }

    private Time buscaTimePorId(Long idTime) {
        return times.stream()
                .filter(time -> time.getId().equals(idTime))
                .findFirst()
                .orElseThrow(() -> new TimeNaoEncontradoException("Time não existe!"));
    }

}