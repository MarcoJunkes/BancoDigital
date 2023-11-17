package net.javaguides.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.publisher.RabbitMQProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    
    private RabbitMQProducer producer;

    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    // http://localhost:8080/api/v1/publish?message=hello

    @GetMapping("/autocadastro")
    public ResponseEntity<String> autocadastro(@RequestParam("message") String message){
        NovaContaEvent novaContaEvent = new NovaContaEvent();
        novaContaEvent.setCpf(message);
        novaContaEvent.setNome("nome");
        novaContaEvent.setSalario(2000f);

        producer.autocadastro(novaContaEvent);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }

    @GetMapping("/alterar-perfil")
    public ResponseEntity<String> alterarPerfil(@RequestParam("message") String message){
        AlterarPerfilEvent alterarPerfilEvent = new AlterarPerfilEvent();
        alterarPerfilEvent.setNome("cliente novo" + message);
        alterarPerfilEvent.setSalario(3000f);
        alterarPerfilEvent.setNumeroConta(Long.valueOf(message));

        producer.alterarPerfil(alterarPerfilEvent);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }

    @GetMapping("/criar-gerente")
    public ResponseEntity<String> criarGerente(@RequestParam("message") String message){
        NovoGerenteEvent novoGerenteEvent = new NovoGerenteEvent();
        novoGerenteEvent.setCpf(message);
        novoGerenteEvent.setNome("gerente 1");

        producer.inserirGerente(novoGerenteEvent);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }

    @GetMapping("/excluir-gerente")
    public ResponseEntity<String> excluirGerente(@RequestParam("message") String message){
        ExcluirGerenteEvent excluirGerenteEvent = new ExcluirGerenteEvent();
        excluirGerenteEvent.setCpf(message);

        producer.excluirGerente(excluirGerenteEvent);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }
}
