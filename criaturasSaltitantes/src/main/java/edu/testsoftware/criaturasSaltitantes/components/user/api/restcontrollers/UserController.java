package edu.testsoftware.criaturasSaltitantes.components.user.api.restcontrollers;

import edu.testsoftware.criaturasSaltitantes.simulationV1.criatura.Criatura;
import edu.testsoftware.criaturasSaltitantes.simulationV1.criatura.CriaturaService;
import edu.testsoftware.criaturasSaltitantes.simulationV1.simulation.ProcessamentoCriaturas;
import edu.testsoftware.criaturasSaltitantes.simulationV1.simulation.RespostaProcessamento;
import edu.testsoftware.criaturasSaltitantes.simulationV1.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/users")
@CrossOrigin(origins = "*")
public class UserController {
    @PostMapping
    public void post(){
        ProcessamentoCriaturas.processamento(10,60);
    }

    @Autowired
    private CriaturaService criaturaService;

    @PostMapping("/processamento/{userId}/{quantidadeCriaturas}/{tempo}")
    public ResponseEntity<?> processarCriaturas(
            @PathVariable("userId") Long userId,
            @PathVariable("quantidadeCriaturas") Integer quantidadeCriaturas,
            @PathVariable("tempo") Integer tempoSegundos) {
        RespostaProcessamento respostaProcessamento = ProcessamentoCriaturas.processamento(quantidadeCriaturas, tempoSegundos);
        criaturaService.postProcessamentoCriatura(userId, respostaProcessamento.getCriaturas());
        return ResponseEntity.ok("Resultados salvos para o usuario " + userId);
    }

    @GetMapping
    public ResponseEntity<EstatisticaDTO> getEstatisticas(){
        List<Usuario> usuarios = usuarioService.listar();
        List<UsuarioBuscadoDTO> usuariosBuscadosDTO = new ArrayList<>();
        int totalSimulacoes = 0;
        int totalSimulacoesBemSucedidas = 0;
        for(Usuario usuario : usuarios){
            UsuarioBuscadoDTO usuarioBuscadoDTO = new UsuarioBuscadoDTO();
            usuarioBuscadoDTO.setPontuacao(usuario.getHistoricoPontuacoes().getLast());
            usuarioBuscadoDTO.setAvatar(usuario.getAvatar());
            usuarioBuscadoDTO.setMediaSimulacoesBemSucedidas(usuario.getMediaSimulacoesBemSucedidas());
            usuarioBuscadoDTO.setQuantidadeSimulacoes(usuario.getQuantidadeSimulacoes());

            totalSimulacoes += usuario.getQuantidadeSimulacoes();
            totalSimulacoesBemSucedidas += usuario.getQuantidadeSimulacoesBemSucedidas();

            usuariosBuscadosDTO.add(usuarioBuscadoDTO);

        }
        EstatisticaDTO estatisticaDTO = new EstatisticaDTO();
        estatisticaDTO.setUsuarios(usuariosBuscadosDTO);
        estatisticaDTO.setQuantidadeSimulacoes(totalSimulacoes);
        estatisticaDTO.setMediaSimulacoesBemSucedidas((float) totalSimulacoesBemSucedidas /totalSimulacoes);

        return ResponseEntity.ok(estatisticaDTO);
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
            return ResponseEntity.ok(sucesso.get());
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

}
