package edu.testsoftware.criaturasSaltitantes.simulationV1.criatura;

import edu.testsoftware.criaturasSaltitantes.simulationV1.usuario.Usuario;
import edu.testsoftware.criaturasSaltitantes.simulationV1.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class CriaturaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void postProcessamentoCriatura(Long usuarioId, Criatura[] criaturas) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (!usuarioOpt.isPresent()) {
            throw new RuntimeException("Usuario não encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        // Salva todas as moedas das criaturas
        for (Criatura criatura : criaturas) {
            usuario.adicionarPontuacao((double) criatura.getMoedas());
        }

        // Salva a média (opcional)
        double media = Arrays.stream(criaturas).mapToDouble(Criatura::getMoedas).average().orElse(0.0);
        usuario.adicionarPontuacao(media);

        usuarioRepository.save(usuario);
    }
}
