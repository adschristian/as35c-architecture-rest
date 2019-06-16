package br.edu.utfpr.servico;

import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.excecao.NomeClienteMenor5CaracteresException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ServicoCliente {

    private List<ClienteDTO> clientes;

    public ServicoCliente() {
        this.clientes = new ArrayList<>();
    }

    @GetMapping("/servicos/clientes")
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(this.clientes);
    }

    @GetMapping("servicos/clientes/{id}")
    public ResponseEntity<ClienteDTO> listarPorId(@PathVariable int id) {
        Optional<ClienteDTO> clienteEncontrado = this.clientes.stream().filter(c -> c.getId() == id).findAny();

        return ResponseEntity.of(clienteEncontrado);
    }

    @PostMapping("/servicos/clientes")
    public ResponseEntity<ClienteDTO> criar(@RequestBody ClienteDTO cliente) {
        cliente.setId(this.clientes.size() + 1);
        this.clientes.add(cliente);

        return ResponseEntity.status(201).body(cliente);
    }

    @DeleteMapping("/servicos/clientes/{id}")
    public ResponseEntity<ClienteDTO> excluir(@PathVariable int id) {
        if (this.clientes.removeIf(c -> c.getId() == id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("servicos/clientes/{id}")
    public ResponseEntity<ClienteDTO> alterar(@PathVariable int id, @RequestBody ClienteDTO cliente) {
        Optional<ClienteDTO> clienteExistente = this.clientes.stream().filter(c -> c.getId() == id).findAny();

        if (clienteExistente.isPresent()) {
            ClienteDTO clienteEncontrado = clienteExistente.get();

            try {
                clienteEncontrado.setNome(cliente.getNome());
            } catch (NomeClienteMenor5CaracteresException e) {
                return ResponseEntity.badRequest().build();
            }
            clienteEncontrado.setIdade(cliente.getIdade());
            clienteEncontrado.setLimiteCredito(cliente.getLimiteCredito());
            clienteEncontrado.setTelefone(cliente.getTelefone());
            clienteEncontrado.setPais(cliente.getPais());

            return ResponseEntity.ok(clienteEncontrado);
        }

        return ResponseEntity.of(clienteExistente);
    }

}
