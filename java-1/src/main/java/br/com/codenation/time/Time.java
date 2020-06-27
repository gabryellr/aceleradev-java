package br.com.codenation.time;

import br.com.codenation.jogador.Jogador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Time {

    private Long id;
    private String nome, corUniformePrincipal, corUniformeSecundario;
    private LocalDate dataCriacao;
    private List<Jogador> jogadores = new ArrayList<>();
    private Jogador capitao;

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
        this.dataCriacao = dataCriacao;
    }

    public void addJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public Jogador getCapitao() {
        return capitao;
    }

    public void setCapitao(Jogador capitao) {
        this.capitao = capitao;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {
        this.corUniformePrincipal = corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public void setCorUniformeSecundario(String corUniformeSecundario) {
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
