# <a name="topo">Spring-Boot-TCC</a>
API Java + Spring Boot com autenticação HTTP Basic para o TCC do Colégio Técnico de Limeira

> # Documentação de Endpoins da API
> 1. [Produto](#produto)
> 1. [Categoria](#categoria)

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
>> RESPOSTA DA SOLICITAÇÃO: 201 - O Produto criado
>> <br /><br />
>> 
>> 
>> ### Apagar por id
>> [ DELETE ]  : /produtos/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204 - Produto
>> <br /><br />
>> 
>> 
>> ### Modificar
>> [ PATCH ]  : /produtos/{id} - inteiro positivo, id do produto a ser alterado
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : O Produto completo a ser alterado
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
>> RESPOSTA DA SOLICITAÇÃO: 201 - A Categoria criada
>> <br /><br />
>> 
>> 
>> ### Apagar por id
>> [ DELETE ]  : /categorias/{id} - inteiro positivo
>> <br /><br />
>> RESPOSTA : 204 - Categoria
>> <br /><br />
>> 
>> 
>> ### Modificar
>> [ PATCH ]  : /categorias/{id} - inteiro positivo, id da categoria a ser alterado
>> <br /><br />
>> CORPO DA REQUISIÇÃO    : A Categoria completo a ser alterado
>> RESPOSTA : 200 - Categoria
>> <br /><br />
>> [Voltar ao topo](#topo)

