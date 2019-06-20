<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Gerenciar Clientes</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <div class="jumbotron">
        <h1>Alterar Cliente</h1>
    </div>
    <div class="row">
        <div class="col">
            <form action="/clientes/alterar" method="post">
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
                            <#if clienteAtual.pais.id == pais.id>
                                <option value="${pais.id}" selected>${pais.nome}</option>
                            <#else>
                                <option value="${pais.id}">${pais.nome}</option>
                            </#if>
                        </#list>
                    </select>
                </div>

                <input type="hidden" name="id" value="${(clienteAtual.id)!}"></input>

                <button class="btn btn-warning" type="submit">Alterar</button>
            </form>

        </div>
    </div>

</div>
</body>

</html>