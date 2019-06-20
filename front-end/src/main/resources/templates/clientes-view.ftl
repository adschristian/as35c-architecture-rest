<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Gerenciar Clientes</title>

    <!-- Latest compiled and minified CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="jumbotron">
            <h1>Gerenciamento de Clientes</h1>
            <p>Essa página é responsável por fazer o gerenciamento de clientes. </p>
        </div>
        <div class="row">
            <div class="col">
                <form action="/clientes/criar" method="post">
                    <div class="form-group">
                        <label for="nome">Nome:</label>
                        <input class="form-control" id="nome" name="nome" type="text" value="${(clienteAtual.nome)!}">
                    </div>
                    <div class="form-group">
                        <label for="idade">Idade:</label>
                        <input class="form-control" id="idade" name="idade" type="number" value="${(clienteAtual.idade)!}">
                    </div>
                    <div class="form-group">
                        <label for="telefone">Telefone:</label>
                        <input class="form-control" id="telefone" name="telefone" type="text" value="${(clienteAtual.telefone)!}">
                    </div>
                    <div class="form-group">
                        <label for="limiteCredito">Limite de Crédito:</label>
                        <input class="form-control" id="limiteCredito" name="limiteCredito" type="text" value="${(clienteAtual.limiteCredito)!}">
                    </div>
                    <div class="form-group">
                        <label>País:</label>
                        <select class="custom-select" name="paisId" id="paisId">
                            <option>Selecionar País</option>
                            <#list paises as pais>
                                <option value="${pais.id}">${pais.nome}</option>
                            </#list>
                        </select>
                    </div>

                    <button class="btn btn-primary" type="submit">Criar</button>
                </form>

            </div>
        </div>
        <div class="row">
            <div class="col">
                <table class="table table-striped table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Nome</th>
                            <th>Idade</th>
                            <th>Telefone</th>
                            <th>Limite de Crédito</th>
                            <th>País</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list clientes as cliente>
                            <tr>
                                <td>${cliente.nome}</td>
                                <td>${cliente.idade}</td>
                                <td>${cliente.telefone}</td>
                                <td>${cliente.limiteCredito}</td>
                                <td>${cliente.pais.nome}</td>
                                <td>
                                    <a href="/clientes/prepara-alterar?id=${cliente.id}">Alterar</a> |
                                    <a href="/clientes/excluir?id=${cliente.id}">Excluir</a>
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>