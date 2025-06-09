import { Link } from "react-router-dom";
import { useState } from "react";

export default function Register() {
  const [login, setLogin] = useState("");
  const [senha, setSenha] = useState("");
  const [confirmarSenha, setConfirmarSenha] = useState("");
  const [avatar, setAvatar] = useState("");
  const [erroSenha, setErroSenha] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (senha !== confirmarSenha) {
      setErroSenha("As senhas não coincidem.");
      return;
    }

    setErroSenha("");
    console.log({ login, senha, avatar });
  };

  return (
    <div className="flex items-center justify-center h-screen bg-gray-100">
      <form onSubmit={handleSubmit} className="bg-white p-8 rounded-xl shadow-md w-96">
        <h2 className="text-2xl font-bold mb-6 text-center">Cadastro</h2>

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

        <input
          type="password"
          placeholder="Confirmar Senha"
          value={confirmarSenha}
          onChange={(e) => setConfirmarSenha(e.target.value)}
          className={`w-full p-3 mb-4 border rounded-lg ${erroSenha ? "border-red-500" : ""}`}
          required
        />

        {erroSenha && <p className="text-red-500 text-sm mb-2">{erroSenha}</p>}

        <input
          type="text"
          placeholder="Avatar (URL)"
          value={avatar}
          onChange={(e) => setAvatar(e.target.value)}
          className="w-full p-3 mb-4 border rounded-lg"
        />

        <button
          type="submit"
          className="w-full bg-green-500 text-white p-3 rounded-lg hover:bg-green-600 disabled:bg-gray-400"
          disabled={senha !== confirmarSenha}
        >
          Cadastrar
        </button>

        <p className="mt-4 text-center">
          Já tem uma conta?{" "}
          <Link to="/login" className="text-green-500 hover:underline">Entrar</Link>
        </p>
      </form>
    </div>
  );
}
