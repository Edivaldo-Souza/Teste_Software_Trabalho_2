package edu.testsoftware.criaturasSaltitantes.simulationV1.usuario;

public class UsuarioBuscadoDTO {
    private String avatar;
    private int quantidadeSimulacoesBemSucedidas;
    private float mediaSimulacoesBemSucedidas;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
}
