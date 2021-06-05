# <a name="topo">Spring-Boot-TCC</a>
API Java + Spring Boot com autenticação HTTP Basic para o TCC do Colégio Técnico de Limeira

# Autenticação
### Alguns endpoints requerem autenticação do tipo HTTP Basic ou serão negados
### Estes terão este requerimento em sua descrição

> # Documentação de Endpoins da API
> 1. [Produto](#produto)
> 2. [Categoria](#categoria)
> 3. [Estado](#estado)
> 4. [Cidade](#cidade)
> 5. [Endereço](#endereco)
> 6. [Usuario](#usuario)
> 7. [Compra](#compra)
> 8. [Departamento](#departamento)
> 9. [Funcionario](#funcionario)

<br /><br /><br /><br />


> ## <a name="produto">Produto</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON
>> ##### Campos marcados com ' * ' são obrigatórios
>> ``` JSON
>>	{
>>	    "id": 1,
>>	    "nome": "Nome do produto", *
>>	    "descricao": "Descrição do produto",
>>	    "valor": 200.0, *
>>	    "cadastro": "2021-05-18",
>>	    "peso": 0.0,
>>	    "estoque": 0,
>>	    "cor": "",
>>	    "tamanho": "",
>>	    "banner": "link para o banner principal", *
>>	    "imagens": [
>>	        <string>
>>	    ],
>>	    "categorias": [
>>			<string>
>>		]
>>	}
>> ```
>> ### Busca paginada
>> [ GET ]  : /produtos?page=0&size=100&sort=valor,desc&min=0&max=100000
>> <br /><br />
>> RESPOSTA : 200 - Pagina contendo os produtos
>> <br /><br />
>> 
>> 
>> ### Busca por id
>> [ GET ]  : /produtos/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 200 - Produto
>> <br /><br />
>> 
>> 
>> ### Busca texto contido em nome e descrição
>> [ GET ]  : /produtos/search/{text} - string que será buscada
>> <br /><br />
>> RESPOSTA : 200 - Pagina contendo os produtos que contém o texto
>> <br /><br />
>> 
>> 
>> ### Busca por categoria
>> [ GET ]  : /produtos/categoria/{id} - inteiro positivo 
>> <br /><br />
>> RESPOSTA : 200 - Pagina contendo os produtos associados a categoria indicada
>> <br /><br />
>> 
>> 
>> ### Salvar
>> [ POST ]  : /produtos
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Produto a ser salvo
>> <br /><br />
>> RESPOSTA DA SOLICITAÇÃO: 201 - O Produto criado
>> <br /><br />
>> Requer autenticação e autoridade de empregado
>> <br /><br />
>> 
>> 
>> ### Apagar por id
>> [ DELETE ]  : /produtos/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />
>> Requer autenticação e autoridade de empregado
>> <br /><br />
>> 
>> 
>> ### Modificar
>> [ PATCH ]  : /produtos/{id} - inteiro positivo, id do produto a ser alterado
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Produto completo a ser alterado
>> <br /><br />
>> RESPOSTA : 200 - Produto
>> <br /><br />
>> Requer autenticação e autoridade de empregado
>> <br /><br />
>> [Voltar ao topo](#topo)

<br /><br /><br /><br />


> ## <a name="categoria">Categoria</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON
>> ##### Campos marcados com ' * ' são obrigatórios
>> ``` JSON
>>	{
>>	    "id": 1,
>>	    "nome": "Nome da categoria", *
>>	    "descricao": "Descrição da categoria"
>>	}
>> ```
>> ### Busca paginada
>> [ GET ]  : /categorias?page=0&size=100&sort=nome,desc
>> <br /><br />
>> RESPOSTA : 200 - Pagina contendo as categorias
>> <br /><br />
>> 
>> 
>> ### Busca por id
>> [ GET ]  : /categorias/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 200 - Categorias
>> <br /><br />
>> 
>> 
>> ### Salvar
>> [ POST ]  : /categorias
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : A Categoria a ser salva
>> <br /><br />
>> RESPOSTA DA SOLICITAÇÃO: 201 - A Categoria criada
>> <br /><br />
>> Requer autenticação e autoridade de empregado
>> <br /><br />
>> 
>> 
>> ### Apagar por id
>> [ DELETE ]  : /categorias/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />
>> Requer autenticação e autoridade de empregado
>> <br /><br />
>> 
>> 
>> ### Modificar
>> [ PATCH ]  : /categorias/{id} - inteiro positivo, id da categoria a ser alterado
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : A Categoria completo a ser alterado
>> <br /><br />
>> RESPOSTA : 200 - Categoria
>> <br /><br />
>> Requer autenticação e autoridade de empregado
>> <br /><br />
>> [Voltar ao topo](#topo)

<br /><br /><br /><br />


> ## <a name="estado">Estado</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON
>> ##### Campos marcados com ' * ' são obrigatórios
>> ``` JSON
>>	{
>>		"id": 1,
>>		"nome": "São Paulo", *
>>		"sigla": "SP", *
>>		"cidades": [
>>			<Cidade>
>>		]
>>	}
>> ```
>> ### Busca paginada
>> [ GET ]  : /estados?page=0&size=100&sort=nome,desc
>> <br /><br />
>> RESPOSTA : 200 - Pagina contendo os estados
>> <br /><br />
>> 
>> 
>> ### Salvar
>> [ POST ]  : /estados
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Estado a ser salvo
>> <br /><br />
>> RESPOSTA DA SOLICITAÇÃO: 201 - O Estado criado
>> <br /><br />
>> Requer autenticação e autoridade de empregado
>> <br /><br />
>> 
>> 
>> ### Apagar por id
>> [ DELETE ]  : /estados/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />
>> 
>> 
>> ### Modificar
>> [ PATCH ]  : /categorias/{id} - inteiro positivo, id da categoria a ser alterado
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Estado completo a ser alterado
>> <br /><br />
>> RESPOSTA : 200 - Estado
>> <br /><br />
>> [Voltar ao topo](#topo)

<br /><br /><br /><br />


> ## <a name="cidade">Cidade</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON
>> ##### Campos marcados com ' * ' são obrigatórios
>> ``` JSON
>>	{
>>	    "id": 1,
>>	    "nome": "Limeira", *
>>	    "estado": 1 *
>>	}
>> ```
>> ### Salvar
>> [ POST ]  : /cidades
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : A cidade a ser salva
>> <br /><br />
>> RESPOSTA DA SOLICITAÇÃO: 201 - A cidade criada
>> <br /><br />
>> Requer autenticação e autoridade de empregado
>> <br /><br />
>> 
>> 
>> ### Apagar por id
>> [ DELETE ]  : /cidades/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />
>> Requer autenticação e autoridade de empregado
>> <br /><br />
>> 
>> 
>> ### Modificar
>> [ PATCH ]  : /cidades/{id} - inteiro positivo, id da cidade a ser alterado
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : A cidade a ser alterado
>> <br /><br />
>> RESPOSTA : 200 - Cidade
>> <br /><br />
>> Requer autenticação e autoridade de empregado
>> <br /><br />
>> [Voltar ao topo](#topo)

<br /><br /><br /><br />


> ## <a name="endereco">Endereço</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON
>> ##### Campos marcados com ' * ' são obrigatórios
>> ``` JSON
>>	{
>>		"id": 1,
>>     "apelido": "Casa", *
>>     "cep": "12345678", *
>>     "rua": "rua dos sabias", *
>>     "numero": "22", *
>>     "complemento": "", *
>>     "bairro": "Jardim das flores", *
>>     "cidade": <Cidade>
>>	}
>> ```
>> ### Busca por usuario paginada
>> [ GET ]  : /enderecos/usuario?page=0&size=20&sort=cidade,asc
>> <br /><br />
>> RESPOSTA : 200 - Pagina contendo os endereços
>> <br /><br />
>> 
>> 
>> ### Salvar
>> [ POST ]  : /enderecos
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Endereço a ser salvo
>> <br /><br />
>> RESPOSTA DA SOLICITAÇÃO: 201 - O Endereço criado
>> <br /><br />
>> 
>> 
>> ### Apagar por id
>> [ DELETE ]  : /enderecos/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />
>> 
>> 
>> ### Modificar
>> [ PATCH ]  : /enderecos/{id} - inteiro positivo, id do endereço a ser alterado
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Endereço completo a ser alterado
>> <br /><br />
>> RESPOSTA : 200 - Endereço
>> <br /><br />
>> [Voltar ao topo](#topo)

<br /><br /><br /><br />


> ## <a name="usuario">Usuario</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON
>> ##### Campos marcados com ' * ' são obrigatórios
>> ``` JSON
>> 	{
>>		"id": 6,
>>  	"nome": "jao", *
>>  	"email": "email@email.com", *
>>  	"cpf": "00011100011", * 
>>  	"acesso": "USUARIO",
>>  	"genero": "HOMEM",
>>  	"telefones": [
>>      	"1234",
>>      	"5678"
>>  	],
>>  	"ativo": false,
>>  	"registro": "2021-05-21",
>>  	"nascimento": "2021-05-21" *
>>	}
>> ```
>> ### Busca por id ou cpf
>> [ GET ]  : /usuarios?id=0&cpf=12345678901
>> <br /><br />
>> RESPOSTA : 200 - Usuario
>> <br /><br />
>> 
>> 
>> ### Salvar
>> [ POST ]  : /usuarios
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Usuario a ser salvo
>> <br /><br />
>> RESPOSTA DA SOLICITAÇÃO: 201 - O Usuario criado
>> <br /><br />
>> 
>> 
>> ### Desativar usuario por id
>> [ PATCH ]  : /usuarios/desativar/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />
>> 
>> 
>> ### Ativar usuario por id
>> [ PATCH ]  : /usuarios/ativar/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />
>> 
>> 
>> ### Modificar
>> [ PATCH ]  : /usuarios/{id} - inteiro positivo, id da categoria a ser alterado
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Usuario
>> <br /><br />
>> RESPOSTA : 200 - Usuario
>> <br /><br />
>> [Voltar ao topo](#topo)

<br /><br /><br /><br />


> ## <a name="compra">Compra</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON da compra
>> ##### Campos marcados com ' * ' são obrigatórios
>> ``` JSON
>>	{
>>  	"id": 3,
>>  	"cliente": <Usuario>,
>>  	"dataCompra": "2021-05-18",
>>  	"total": 16000.0,
>>  	"estado": "PENDENTE",
>>  	"metodo": "BOLETO",
>>  	"dataPagamento": null,
>>  	"endereco": <Endereço>, *
>>  	"items": [ *
>>     		<ItemCompra> 
>>  	]
>>	}
>> ```
>> ### Estrutura JSON do ItemCompra
>> ##### Campos marcados com ' * ' são obrigatórios
>> ``` JSON
>>	{
>>  	"id": 1,
>>  	"produto" : <Produto>, *
>> 		"quantidade" : 1 *
>>	}
>> ```
>> ### Busca paginada por usuario e estado de pagamento
>> ##### Parametro estado possui default como PENDENTE, e variantes com COMPLETO e CANCELADO
>> [ GET ]  : /compras/usuario/{id}?page=0&size=100&sort=nome,desc&estado=PENDENTE
>> <br /><br />
>> RESPOSTA : 200 - Pagina contendo as compras
>> <br /><br />
>> 
>> 
>> ### Busca por id
>> [ GET ]  : /compras/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 200 - Compra
>> <br /><br />
>> 
>> 
>> ### Salvar
>> nota sobre este endpoint: ele obtem o usuario automaticamente a partir do usuario que estiver logado
>> [ POST ]  : /compras
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : A Compra a ser salva
>> <br /><br />
>> RESPOSTA DA SOLICITAÇÃO: 201 - A Compra criada
>> <br /><br />
>> 
>> 
>> ### Cancelar compra
>> [ PATCH ]  : /compras/cancelar/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />
>> 
>> 
>> ### Completar compra
>> [ PATCH ]  : /compras/completar/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />

<br /><br /><br /><br />


> ## <a name="departamento">Departamento</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON
>> ##### Campos marcados com ' * ' são obrigatórios
>> ``` JSON
>>	{
>>	    "id": 1,
>>	    "nome": "RH", *
>>	    "descricao": "Descrição do departamento"
>>	}
>> ```
>> ### Busca paginada
>> [ GET ]  : /departamentos?page=0&size=100&sort=nome,desc
>> <br /><br />
>> RESPOSTA : 200 - Pagina contendo os departamentos
>> <br /><br />
>> 
>> 
>> ### Busca por id
>> [ GET ]  : /departamentos/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 200 - Departamento
>> <br /><br />
>> 
>> 
>> ### Salvar
>> [ POST ]  : /departamentos
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Departamento a ser salvo
>> <br /><br />
>> RESPOSTA DA SOLICITAÇÃO: 201 - O Departamento
>> <br /><br />
>> 
>> 
>> ### Apagar por id
>> [ DELETE ]  : /departamentos/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />
>> 
>> 
>> ### Modificar
>> [ PATCH ]  : /departamentos/{id} - inteiro positivo, id da categoria a ser alterado
>> <br /><br />
>> CORPO DA REQUISIÇÃO    :  O Departamento a ser alterado
>> <br /><br />
>> RESPOSTA : 200 - Departamento
>> <br /><br />
>> [Voltar ao topo](#topo)

<br /><br /><br /><br />


> ## <a name="funcionario">Funcionario</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON
>> ##### Campos marcados com ' * ' são obrigatórios
>> ``` JSON
>>{
>>  "id": 1,
>>  "nome": "João",
>>  "email": "email@email.com",
>>  "cpf": "12345678901",
>>  "acesso": "EMPREGADO",
>>  "genero": "HOMEM",
>>  "telefones": "2021-05-18",
>>  "ativo": true,
>>  "registro": "2021-05-18",
>>  "nascimento": "2021-05-18",
>>  "funcao": "Programador",
>>  "salario": 0.0,
>>  "admissao": "2021-05-18",
>>  "departamento": <Departamento>
>>}
>> ```
>> ### Busca paginada
>> [ GET ]  : /funcionarios?page=0&size=100&sort=nome,desc
>> <br /><br />
>> RESPOSTA : 200 - Pagina contendo os funcionarios
>> <br /><br />
>> 
>> 
>> ### Busca por id
>> [ GET ]  : /funcionarios/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 200 - Funcionario
>> <br /><br />
>> 
>> 
>> ### Busca por departamento paginado
>> [ GET ]  : /funcionarios/departamento/{id}?page=0&size=100&sort=nome,desc - inteiro positivo
>> <br /><br />
>> RESPOSTA : 200 - Pagina contendo os funcionarios
>> <br /><br />
>> 
>> 
>> ### Salvar
>> [ POST ]  : /funcionarios
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Funcionario a ser salvo
>> <br /><br />
>> RESPOSTA DA SOLICITAÇÃO: 201 - O Funcionario
>> <br /><br />
>> 
>> 
>> ### Apagar por id
>> [ DELETE ]  : /funcionarios/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />
>> 
>> 
>> ### Modificar
>> [ PATCH ]  : /funcionarios/{id} - inteiro positivo, id da categoria a ser alterado
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Funcionario a ser alterado
>> <br /><br />
>> RESPOSTA : 200 - O Funcionario
>> <br /><br />
>> [Voltar ao topo](#topo)