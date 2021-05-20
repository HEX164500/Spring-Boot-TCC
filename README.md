# <a name="topo">Spring-Boot-TCC</a>
API Java + Spring Boot com autenticação HTTP Basic para o TCC do Colégio Técnico de Limeira

> # Documentação de Endpoins da API
> 1. [Produto](#produto)
> 2. [Categoria](#categoria)
> 3. [Estado](#estado)
> 4. [Cidade](#cidade)
> 5. [Endereço](#endereco)

<br /><br /><br /><br />


> ## <a name="produto">Produto</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON
>> ##### Campos marcados com ' * ' são obrigatórios em POST e PATCH
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
>>	        // lista de strings contendo os links de imagens adicionais
>>	    ],
>>	    "categorias": [
>>			// Lista de inteiros contendo os ids das categorias
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
>> <br />
>> RESPOSTA DA SOLICITAÇÃO: 201 - O Produto criado
>> <br /><br />
>> 
>> 
>> ### Apagar por id
>> [ DELETE ]  : /produtos/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />
>> 
>> 
>> ### Modificar
>> [ PATCH ]  : /produtos/{id} - inteiro positivo, id do produto a ser alterado
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Produto completo a ser alterado
>> <br />
>> RESPOSTA : 200 - Produto
>> <br /><br />
>> [Voltar ao topo](#topo)

<br /><br /><br /><br />


> ## <a name="categoria">Categoria</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON
>> ##### Campos marcados com ' * ' são obrigatórios em POST e PATCH
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
>> <br />
>> RESPOSTA DA SOLICITAÇÃO: 201 - A Categoria criada
>> <br /><br />
>> 
>> 
>> ### Apagar por id
>> [ DELETE ]  : /categorias/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />
>> 
>> 
>> ### Modificar
>> [ PATCH ]  : /categorias/{id} - inteiro positivo, id da categoria a ser alterado
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : A Categoria completo a ser alterado
>> <br />
>> RESPOSTA : 200 - Categoria
>> <br /><br />
>> [Voltar ao topo](#topo)

<br /><br /><br /><br />


> ## <a name="estado">Estado</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON
>> ##### Campos marcados com ' * ' são obrigatórios em POST e PATCH
>> ``` JSON
>>	{
>>		"id": 1,
>>		"nome": "São Paulo", *
>>		"sigla": "SP", *
>>		"cidades": []
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
>> <br />
>> RESPOSTA DA SOLICITAÇÃO: 201 - O Estado criado
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
>> <br />
>> RESPOSTA : 200 - Estado
>> <br /><br />
>> [Voltar ao topo](#topo)

<br /><br /><br /><br />


> ## <a name="cidade">Cidade</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON
>> ##### Campos marcados com ' * ' são obrigatórios em POST e PATCH
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
>> <br />
>> RESPOSTA DA SOLICITAÇÃO: 201 - A cidade criada
>> <br /><br />
>> 
>> 
>> ### Apagar por id
>> [ DELETE ]  : /cidades/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204
>> <br /><br />
>> 
>> 
>> ### Modificar
>> [ PATCH ]  : /cidades/{id} - inteiro positivo, id da cidade a ser alterado
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : A cidade a ser alterado
>> <br />
>> RESPOSTA : 200 - Cidade
>> <br /><br />
>> [Voltar ao topo](#topo)

<br /><br /><br /><br />


> ## <a name="endereco">Endereço</a>
> #####  [Voltar ao topo](#topo)
>> ### Estrutura JSON
>> ##### Campos marcados com ' * ' são obrigatórios em POST e PATCH
>> ``` JSON
>>	{
>>		"id": 1,
>>     "apelido": "Casa", *
>>     "cep": "12345678", *
>>     "rua": "rua dos sabias", *
>>     "numero": "22", *
>>     "complemento": "", *
>>     "bairro": "Jardim das flores", *
>>     "cidade": {
>>         "id": 1,
>>         "nome": "Limeira",
>>         "estado": 1
>>     }
>>}
>> ```
>> ### Busca por id
>> [ GET ]  : /enderecos/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 200 - Pagina contendo os endereços
>> <br /><br />
>> 
>> 
>> ### Salvar
>> [ POST ]  : /enderecos
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Endereço a ser salvo
>> <br />
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
>> <br />
>> RESPOSTA : 200 - Endereço
>> <br /><br />
>> [Voltar ao topo](#topo)
