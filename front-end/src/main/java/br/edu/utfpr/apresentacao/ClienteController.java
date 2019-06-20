package br.edu.utfpr.apresentacao;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ClienteController {

    private List<PaisModel> paises;

    @GetMapping("/clientes")
    public String inicial(Model data) throws UnirestException, JsonSyntaxException {
        ClienteModel arrayClientes[] = new Gson()
                .fromJson(
                        Unirest.get("http://localhost:8081/servicos/clientes")
                                .asJson()
                                .getBody()
                                .toString(),
                        ClienteModel[].class
                );

        data.addAttribute("clientes", arrayClientes);

        PaisModel arrayPaises[] = new Gson()
                .fromJson(
                        Unirest.get("http://localhost:8081/servicos/paises")
                                .asJson()
                                .getBody()
                                .toString(),
                        PaisModel[].class
                );

        this.paises = Stream.of(arrayPaises).collect(Collectors.toList());

        data.addAttribute("paises", this.paises);

        return "clientes-view";
    }

    @PostMapping("/clientes/criar")
    public String criar(ClienteModel cliente, @RequestParam(value = "paisId") long paisId) throws UnirestException {
        this.paises.stream().filter(p -> p.getId() == paisId).forEach(cliente::setPais);

        Unirest.post("http://localhost:8081/servicos/clientes")
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(cliente, ClienteModel.class))
                .asJson();

        return "redirect:/clientes";
    }

    @GetMapping("/clientes/excluir")
    public String excluir(@RequestParam int id) throws UnirestException {
        Unirest.delete("http://localhost:8081/servicos/clientes/{id}")
                .routeParam("id", String.valueOf(id))
                .asJson();

        return "redirect:/clientes";
    }

    @GetMapping("/clientes/prepara-alterar")
    public String preparaAlterar(@RequestParam int id, Model data) throws UnirestException, JsonSyntaxException {
        ClienteModel clienteExistente = new Gson()
                .fromJson(
                        Unirest.get("http://localhost:8081/servicos/clientes/{id}")
                                .routeParam("id", String.valueOf(id))
                                .asJson()
                                .getBody()
                                .toString(),
                        ClienteModel.class
                );


        data.addAttribute("clienteAtual", clienteExistente);

        PaisModel arrayPaises[] = new Gson()
                .fromJson(
                        Unirest.get("http://localhost:8081/servicos/paises")
                                .asJson()
                                .getBody()
                                .toString(),
                        PaisModel[].class
                );

        data.addAttribute("paises", arrayPaises);

        return "clientes-view-alterar";
    }

    @PostMapping("/clientes/alterar")
    public String alterar(ClienteModel clienteAlterado, @RequestParam(value = "paisId") long paisId) throws UnirestException {
        this.paises.stream().filter(p -> p.getId() == paisId).forEach(clienteAlterado::setPais);

        Unirest.put("http://localhost:8081/servicos/clientes/{id}")
                .routeParam("id", String.valueOf(clienteAlterado.getId()))
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(clienteAlterado, ClienteModel.class))
                .asJson();

        return "redirect:/clientes";
    }

}
