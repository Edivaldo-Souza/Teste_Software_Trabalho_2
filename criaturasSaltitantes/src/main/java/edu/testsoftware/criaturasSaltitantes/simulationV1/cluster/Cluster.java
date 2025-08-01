package edu.testsoftware.criaturasSaltitantes.simulationV1.cluster;

import edu.testsoftware.criaturasSaltitantes.simulationV1.criatura.Criatura;

import java.util.ArrayList;
import java.util.List;

import static edu.testsoftware.criaturasSaltitantes.simulationV1.criatura.Criatura.CRIATURA_ALTURA;
import static edu.testsoftware.criaturasSaltitantes.simulationV1.criatura.Criatura.CRIATURA_LARGURA;
import static edu.testsoftware.criaturasSaltitantes.simulationV1.simulation.ProcessamentoCriaturas.WINDOW_HEIGHT;
import static edu.testsoftware.criaturasSaltitantes.simulationV1.simulation.ProcessamentoCriaturas.WINDOW_WIDTH;

public class Cluster {
    private List<Criatura> criaturas;
    private float velX, velY;
    private int moedasDoCluster;

    public Cluster() {
        criaturas = new ArrayList<>();
    }

    public void addCriatura(Criatura criatura){
        criaturas.add(criatura);
    }

    public int getMoedasDoCluster() {
        return moedasDoCluster;
    }

    public void setMoedasDoCluster(int moedasDoCluster) {
        this.moedasDoCluster = moedasDoCluster;
    }

    public void receiveCoins(int quantidadeMoedas){
        this.moedasDoCluster += quantidadeMoedas;
    }

    public int giveCoins(){
        this.moedasDoCluster/=2;
        return moedasDoCluster;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public List<Criatura> getCriaturas() {
        return criaturas;
    }

    public void setCriaturas(List<Criatura> criaturas) {
        this.criaturas = criaturas;
    }
}
