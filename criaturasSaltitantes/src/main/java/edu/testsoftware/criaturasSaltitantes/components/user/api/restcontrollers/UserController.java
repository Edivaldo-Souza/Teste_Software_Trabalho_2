package edu.testsoftware.criaturasSaltitantes.components.user.api.restcontrollers;

import edu.testsoftware.criaturasSaltitantes.simulationV1.simulation.ProcessamentoCriaturas;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static io.github.libsdl4j.api.SdlSubSystemConst.SDL_INIT_EVERYTHING;

@RestController
@RequestMapping("v1/users")
public class UserController {
    @PostMapping
    public void post(){
        ProcessamentoCriaturas.processamento(10,60);
    }
}
