package br.edu.utfpr.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.dto.PaisDTO;
import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
public class ServicoPais {

    private List<PaisDTO> paises;

    public ServicoPais() {
        paises = new ArrayList<>();
    }

    @GetMapping ("/servicos/paises")
    public ResponseEntity<List<PaisDTO>> listar() {
        return ResponseEntity.ok(paises);
    }

    @GetMapping ("/servicos/paises/{id}")
    public ResponseEntity<PaisDTO> listarPorId(@PathVariable int id) {
        Optional<PaisDTO> paisEncontrado = paises.stream().filter(p -> p.getId() == id).findAny();

        return ResponseEntity.of(paisEncontrado);
    }

    @PostMapping ("/servicos/paises")
    public ResponseEntity<PaisDTO> criar (@RequestBody PaisDTO pais) {

        pais.setId(paises.size() + 1);
        paises.add(pais);

        return ResponseEntity.status(201).body(pais);
    }

    @DeleteMapping ("/servicos/paises/{id}")
    public ResponseEntity excluir (@PathVariable int id) {
        
        if (paises.removeIf(pais -> pais.getId() == id))
            return ResponseEntity.noContent().build();

        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping ("/servicos/paises/{id}")
    public ResponseEntity<PaisDTO> alterar (@PathVariable int id, @RequestBody PaisDTO pais) {
        Optional<PaisDTO> paisExistente = paises.stream().filter(p -> p.getId() == id).findAny();

        paisExistente.ifPresent(p -> {
            p.setNome(pais.getNome());
            p.setSigla(pais.getSigla());
            p.setCodigoTelefone(pais.getCodigoTelefone());
        });

        return ResponseEntity.of(paisExistente);
    }
}