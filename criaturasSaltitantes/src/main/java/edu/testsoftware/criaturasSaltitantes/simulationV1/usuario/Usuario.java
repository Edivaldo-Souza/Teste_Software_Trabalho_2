package edu.testsoftware.criaturasSaltitantes.simulationV1.usuario;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    private String avatar;

    private int quantidadeSimulacoesBemSucedidas = 0;
    private float mediaSimulacoesBemSucedidas = 0;
    private int quantidadeSimulacoes;

    private int pontuacao = 0;

    // Construtor vazio (obrigatório para JPA)
    public Usuario() {
    }

    // Construtor completo
    public Usuario(Long id, String login, String senha, String avatar, int pontuacao) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.avatar = avatar;
        this.pontuacao = pontuacao;
    }

    // Lista para guardar todas as pontuações ou resultados
    @ElementCollection
    @CollectionTable(name = "usuario_pontuacoes", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "pontuacao_resultado")
    private List<Double> historicoPontuacoes = new ArrayList<>();

    // Getters e Setters
    public List<Double> getHistoricoPontuacoes() {
        return historicoPontuacoes;
    }

    public void setHistoricoPontuacoes(List<Double> historicoPontuacoes) {
        this.historicoPontuacoes = historicoPontuacoes;
    }

    public void adicionarPontuacao(Double pontuacao) {
        this.historicoPontuacoes.add(pontuacao);
    }


    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", avatar='" + avatar + '\'' +
                ", pontuacao=" + pontuacao +
                '}';
    }

    public int getQuantidadeSimulacoesBemSucedidas() {
        return quantidadeSimulacoesBemSucedidas;
    }

    public void setQuantidadeSimulacoesBemSucedidas(int quantidadeSimulacoesBemSucedidas) {
        this.quantidadeSimulacoesBemSucedidas = quantidadeSimulacoesBemSucedidas;
    }

    public float getMediaSimulacoesBemSucedidas() {
        return mediaSimulacoesBemSucedidas;
    }

    public void setMediaSimulacoesBemSucedidas(float mediaSimulacoesBemSucedidas) {
        this.mediaSimulacoesBemSucedidas = mediaSimulacoesBemSucedidas;
    }

    public int getQuantidadeSimulacoes() {
        return quantidadeSimulacoes;
    }

    public void setQuantidadeSimulacoes(int quantidadeSimulacoes) {
        this.quantidadeSimulacoes = quantidadeSimulacoes;
    }
}
