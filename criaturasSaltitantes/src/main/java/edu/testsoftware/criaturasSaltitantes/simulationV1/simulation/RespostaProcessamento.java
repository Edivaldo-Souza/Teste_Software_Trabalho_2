package edu.testsoftware.criaturasSaltitantes.simulationV1.simulation;

import edu.testsoftware.criaturasSaltitantes.simulationV1.criatura.Criatura;

public class RespostaProcessamento {
    private Criatura[] criaturas;
    private int status;

    public Criatura[] getCriaturas() {
        return criaturas;
    }

    public void setCriaturas(Criatura[] criaturas) {
        this.criaturas = criaturas;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
