package edu.testsoftware.criaturasSaltitantes.simulationV1.usuario;

public class UsuarioBuscadoDTO {
    private String avatar;
    private double pontuacao;
    private int quantidadeSimulacoes;
    private float mediaSimulacoesBemSucedidas;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getQuantidadeSimulacoes() {
        return quantidadeSimulacoes;
    }

    public void setQuantidadeSimulacoes(int quantidadeSimulacoes) {
        this.quantidadeSimulacoes = quantidadeSimulacoes;
    }

    public float getMediaSimulacoesBemSucedidas() {
        return mediaSimulacoesBemSucedidas;
    }

    public void setMediaSimulacoesBemSucedidas(float mediaSimulacoesBemSucedidas) {
        this.mediaSimulacoesBemSucedidas = mediaSimulacoesBemSucedidas;
    }
}
