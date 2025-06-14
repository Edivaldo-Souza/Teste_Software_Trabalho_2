package edu.testsoftware.criaturasSaltitantes.components.user.api.restcontrollers;

import edu.testsoftware.criaturasSaltitantes.simulationV1.simulation.ProcessamentoCriaturas;
import edu.testsoftware.criaturasSaltitantes.simulationV1.usuario.Usuario;
import edu.testsoftware.criaturasSaltitantes.simulationV1.usuario.UsuarioCadastroDTO;
import edu.testsoftware.criaturasSaltitantes.simulationV1.usuario.UsuarioLoginDTO;
import edu.testsoftware.criaturasSaltitantes.simulationV1.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static io.github.libsdl4j.api.SdlSubSystemConst.SDL_INIT_EVERYTHING;

@RestController
@RequestMapping("v1/users")
@CrossOrigin(origins = "*")
public class UserController {
    @PostMapping
    public void post(){
        ProcessamentoCriaturas.processamento(10,60);
    }


    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioCadastroDTO dto) {
        try {
            Usuario usuario = usuarioService.cadastrar(dto);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO dto) {
        Optional<Usuario> sucesso = usuarioService.autenticar(dto);
        if (sucesso.isPresent()) {
            return ResponseEntity.ok("Login bem-sucedido");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
