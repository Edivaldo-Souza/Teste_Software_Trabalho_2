package edu.testsoftware.criaturasSaltitantes.components.user.api.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "Servidor rodando!";
    }
}
