import { Link } from "react-router-dom";
import { useState } from "react";
import './Register.css';

export default function Register() {
  const [login, setLogin] = useState("");
  const [senha, setSenha] = useState("");
  const [confirmarSenha, setConfirmarSenha] = useState("");
  const [avatar, setAvatar] = useState("");
  const [erroSenha, setErroSenha] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (senha !== confirmarSenha) {
      setErroSenha("As senhas não coincidem.");
      return;
    }

    setErroSenha("");

    try {
      const res = await fetch("http://localhost:8080/v1/users/cadastro", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ login, senha, avatar }),
      });

      if (res.ok) {
        alert("Usuário cadastrado com sucesso!");
        window.location.href = "/login";
      } else {
        const erro = await res.text();
        alert("Erro ao cadastrar: " + erro);
      }
    } catch (err) {
      console.error("Erro ao cadastrar", err);
      alert("Erro ao conectar com o servidor");
    }
  };

  return (
    <div className="register-container">
      <form onSubmit={handleSubmit} className="register-form">
        <h2>Cadastro</h2>

        <input
          type="text"
          placeholder="Login"
          value={login}
          onChange={(e) => setLogin(e.target.value)}
          className="register-input"
          required
        />

        <input
          type="password"
          placeholder="Senha"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
          className="register-input"
          required
        />

        <input
          type="password"
          placeholder="Confirmar Senha"
          value={confirmarSenha}
          onChange={(e) => setConfirmarSenha(e.target.value)}
          className={`register-input ${erroSenha ? "error" : ""}`}
          required
        />

        {erroSenha && <p className="error-text">{erroSenha}</p>}

        <input
          type="text"
          placeholder="Avatar (URL)"
          value={avatar}
          onChange={(e) => setAvatar(e.target.value)}
          className="register-input"
        />

        <button
          type="submit"
          className="register-button"
          disabled={senha !== confirmarSenha}
        >
          Cadastrar
        </button>

        <p className="register-text">
          Já tem uma conta?{" "}
          <Link to="/login" className="register-link">
            Entrar
          </Link>
        </p>
      </form>
    </div>
  );
}
