package edu.testsoftware.criaturasSaltitantes.simulationV1.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrar(UsuarioCadastroDTO dto) {
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(senhaCriptografada);
        usuario.setAvatar(dto.getAvatar());
        usuario.setPontuacao(0);

        return repository.save(usuario);
    }

    public Optional<Usuario> autenticar(UsuarioLoginDTO dto) {
        Optional<Usuario> usuarioOptional = repository.findByLogin(dto.getLogin());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
                return Optional.of(usuario);
            }
        }

        return Optional.empty();
    }
}

