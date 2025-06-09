import { Link } from "react-router-dom";
import { useState } from "react";

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
      const data = await res.json(); // se o back retornar algo útil
      console.log("Login bem-sucedido", data);
      // Exemplo: salvar login no localStorage e redirecionar
      localStorage.setItem("usuario", JSON.stringify(data));
      window.location.href = "/home"; // ou use `useNavigate` do react-router
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
    <div className="flex items-center justify-center h-screen bg-gray-100">
      <form onSubmit={handleSubmit} className="bg-white p-8 rounded-xl shadow-md w-96">
        <h2 className="text-2xl font-bold mb-6 text-center">Login</h2>

        <input
          type="text"
          placeholder="Login"
          value={login}
          onChange={(e) => setLogin(e.target.value)}
          className="w-full p-3 mb-4 border rounded-lg"
          required
        />
        <input
          type="password"
          placeholder="Senha"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
          className="w-full p-3 mb-4 border rounded-lg"
          required
        />
        <button type="submit" className="w-full bg-blue-500 text-white p-3 rounded-lg hover:bg-blue-600">
          Entrar
        </button>

        <p className="mt-4 text-center">
          Não tem uma conta? <Link to="/register" className="text-blue-500 hover:underline">Cadastre-se</Link>
        </p>
      </form>
    </div>
  );
}
