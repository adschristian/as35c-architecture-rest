package br.edu.utfpr.apresentacao;

import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ClienteController {

    public String inicial(Model data) throws UnirestException {
        ClienteModel arrayClientes[] = new Gson()
                .fromJson(
                        Unirest
                        .get("http://localhost:8081/servicos/clientes")
                        .asJson()
                        .getBody()
                        .toString(),
                        ClienteModel[].class
                );
        data.addAttribute("clientes", arrayClientes);

        return "clientes-view";
    }

}
