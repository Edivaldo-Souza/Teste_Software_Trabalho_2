package edu.testsoftware.criaturasSaltitantes.simulationV1.usuario;


public class UsuarioCadastroDTO {
    private String login;
    private String senha;
    private String avatar;

    public UsuarioCadastroDTO() {
    }

    public UsuarioCadastroDTO(String login, String senha, String avatar) {
        this.login = login;
        this.senha = senha;
        this.avatar = avatar;
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
}
