package br.net.crudgerente.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.crudgerente.model.Gerente;
import br.net.crudgerente.repository.GerenteRepository;

@CrossOrigin
@RestController
public class GerenteREST {
    @Autowired
    private GerenteRepository repo;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/gerentes")
    public List<Gerente> obterGerentes(){
        List<Gerente> lista = repo.findAll();
        return lista.stream().map(e -> mapper.map(e, Gerente.class)).collect(Collectors.toList());
    }

    @GetMapping("/gerentes/{id}")
    public Gerente obterGerente(@PathVariable("id") int id){
        return repo.findById((long) id).orElse(null);
    }

    @PostMapping("/gerentes")
    public Gerente inserirGerente(@RequestBody Gerente gerente) {
        Gerente gerenteSalvo = repo.save(mapper.map(gerente, Gerente.class));
        return gerenteSalvo; 
    }

    @PutMapping("/gerentes/{id}")
    public Gerente alterarGerente(@PathVariable("id") int id, @RequestBody Gerente gerente) {
        Gerente g = repo.findById((long) id).orElse(null);
    
        if (g != null) {
            g.setNome(gerente.getNome());
            g.setEmail(gerente.getEmail());
            g.setCPF(gerente.getCPF());
            g.setTelefone(gerente.getTelefone());
            Gerente gerenteAtualizado = repo.save(g);
            return mapper.map(gerenteAtualizado, Gerente.class);
        }
        return null;
    }

    @DeleteMapping("/gerentes/{id}")
    public Gerente removerGerente(@PathVariable("id") long id) {
        Gerente gerente = repo.findById((long) id).orElse(null);

        if ((gerente != null)&&(repo.count()>1)) {
            repo.deleteById((long) id);
            return mapper.map(gerente, Gerente.class);
        }
        return gerente;
    }
}
