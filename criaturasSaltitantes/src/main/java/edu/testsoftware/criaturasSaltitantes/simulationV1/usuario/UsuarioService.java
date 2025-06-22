package edu.testsoftware.criaturasSaltitantes.simulationV1.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrar(UsuarioCadastroDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha()); // salva direto
        usuario.setAvatar(dto.getAvatar());
        usuario.setPontuacao(0);

        return repository.save(usuario);
    }

    public Optional<Usuario> autenticar(UsuarioLoginDTO dto) {
        Optional<Usuario> usuarioOptional = repository.findByLogin(dto.getLogin());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (dto.getSenha().equals(usuario.getSenha())) {
                return Optional.of(usuario);
            }
        }

        return Optional.empty();
    }

    public Usuario atualizarUsuario(Long id, int simulacaoBemSucedida){
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if(usuarioOptional.isEmpty()) {
            return null;
        }
        if(simulacaoBemSucedida == 1){
            int quantidadeAtualDeSimulacoesBemSucedidas = usuarioOptional.get().getQuantidadeSimulacoesBemSucedidas();
            usuarioOptional.get().setQuantidadeSimulacoesBemSucedidas(quantidadeAtualDeSimulacoesBemSucedidas + 1);
        }
        usuarioOptional.get().setQuantidadeSimulacoes(
                usuarioOptional.get().getQuantidadeSimulacoes() + 1
        );

        usuarioOptional.get().setMediaSimulacoesBemSucedidas(
                usuarioOptional.get().getMediaSimulacoesBemSucedidas()/usuarioOptional.get().getQuantidadeSimulacoes()
        );

        return repository.save(usuarioOptional.get());
    }

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }
}

