import { Link } from "react-router-dom";
import { useState } from "react";
import './Login.css';

export default function Login() {
  const [login, setLogin] = useState("");
  const [senha, setSenha] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const res = await fetch("http://localhost:8080/v1/users/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ login, senha }),
      });

      if (res.ok) {
        const data = await res.json();

        console.log("Login bem-sucedido", data);
        localStorage.setItem("usuario", JSON.stringify(data));
        window.location.href = "/home";
      } else {
        const erro = await res.text();
        alert("Erro no login: " + erro);
      }
    } catch (err) {
      console.error("Erro ao fazer login", err);
      alert("Erro ao conectar com o servidor");
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit} className="login-form">
        <h2>Login</h2>

        <input
          type="text"
          placeholder="Login"
          value={login}
          onChange={(e) => setLogin(e.target.value)}
          className="login-input"
          required
        />
        <input
          type="password"
          placeholder="Senha"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
          className="login-input"
          required
        />
        <button type="submit" className="login-button">
          Entrar
        </button>

        <p className="login-text">
          Não tem uma conta?{" "}
          <Link to="/register" className="login-link">
            Cadastre-se
          </Link>
        </p>
      </form>
    </div>
  );
}
