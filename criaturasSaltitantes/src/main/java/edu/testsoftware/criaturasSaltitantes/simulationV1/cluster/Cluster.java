package edu.testsoftware.criaturasSaltitantes.simulationV1.cluster;

import edu.testsoftware.criaturasSaltitantes.simulationV1.criatura.Criatura;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private List<Criatura> cluster;
    private float velX, velY;

    public Cluster() {
        cluster = new ArrayList<Criatura>();
    }

    public void moveCluster(){
        for(Criatura criatura : cluster){
            criatura.setVelX(velX);
            criatura.setVelY(velY);
            criatura.move();
        }
    }

    public void addCriatura(Criatura criatura){
        criatura.cluster = this;
        criatura.setVelX(0);
        criatura.setVelY(0);
        cluster.add(criatura);
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

    public List<Criatura> getCluster() {
        return cluster;
    }

    public void setCluster(List<Criatura> cluster) {
        this.cluster = cluster;
    }
}
