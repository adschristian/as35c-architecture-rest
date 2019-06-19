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

@Controller
public class ClienteController {

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

        return "clientes-view";
    }

    @PostMapping("/clientes/criar")
    public String criar(ClienteModel cliente) throws UnirestException {
        Unirest.post("http://localhost:8081/servicos/clientes")
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(cliente, ClienteModel.class))
                .asJson();

        return "redirect:/clientes";
    }

    @GetMapping("/clientes/excluir")
    public String excluir(@RequestParam int id) throws UnirestException {
        Unirest.delete("http://localhost:8081/servicos/clientes")
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

        ClienteModel arrayClientes[] = new Gson()
                .fromJson(
                        Unirest.get("http://localhost:8081/servicos/clientes")
                        .asJson()
                        .getBody()
                        .toString(),
                        ClienteModel[].class
                );

        data.addAttribute("clientes", arrayClientes);

        return "redirect:/clientes-view-alterar";
    }

    public String alterar(PaisModel paisAlterado) throws UnirestException {
        Unirest.put("http://localhost:8081/servicos/clientes/{id}")
                .routeParam("id", String.valueOf(paisAlterado.getId()))
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(paisAlterado, PaisModel.class))
                .asJson();

        return "redirect:/paises";
    }

}
