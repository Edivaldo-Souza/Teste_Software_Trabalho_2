package edu.testsoftware.criaturasSaltitantes.simulationV1.usuario;

public class UsuarioLoginDTO {
    private String login;
    private String senha;

    public UsuarioLoginDTO() {
    }

    public UsuarioLoginDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
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
}
