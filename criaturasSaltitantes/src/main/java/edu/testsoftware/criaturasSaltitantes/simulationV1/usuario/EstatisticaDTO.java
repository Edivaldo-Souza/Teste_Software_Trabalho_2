package edu.testsoftware.criaturasSaltitantes.simulationV1.usuario;

import java.util.List;

public class EstatisticaDTO {
    private List<UsuarioBuscadoDTO> usuarios;
    private int quantidadeSimulacoes;
    private float mediaSimulacoesBemSucedidas;

    public List<UsuarioBuscadoDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioBuscadoDTO> usuarios) {
        this.usuarios = usuarios;
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
