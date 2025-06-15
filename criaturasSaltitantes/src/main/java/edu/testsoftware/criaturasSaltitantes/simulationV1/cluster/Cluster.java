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

    public Cluster() {
        criaturas = new ArrayList<Criatura>();
    }

    public void addCriatura(Criatura criatura){
        criaturas.add(criatura);
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
