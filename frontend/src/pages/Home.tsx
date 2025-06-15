import './Home.css';

export default function Home() {
  const handlePost = async () => {
    try {
      const res = await fetch("http://localhost:8080/v1/users", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ exemplo: "dado" }), // ajuste conforme necessário
      });

      if (res.ok) {
        const data = await res.json();
        alert("Requisição feita com sucesso: " + JSON.stringify(data));
      } else {
        const erro = await res.text();
        alert("Erro na requisição: " + erro);
      }
    } catch (err) {
      console.error("Erro ao fazer POST", err);
      alert("Erro ao conectar com o servidor");
    }
  };

  return (
    <div className="home-container">
      <div className="home-content">
        <h1 className="home-title">Bem-vindo à Home!</h1>
        <button className="home-button" onClick={handlePost}>Fazer POST</button>
      </div>
    </div>
  );
}
