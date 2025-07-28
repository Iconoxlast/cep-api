## CepAPI
Projeto criado para fins demonstrativos, uma API montada em Spring que fornece ao cliente dados sobre um CEP que são obtidos a partir de um servidor externo (um mock, nessa demonstração). As consultas do cliente, incluindo dados retornados e horário da consulta, são logados num banco de dados.

Diagrama:
![Diagrama UML](docs/CepAPI%20diagrama.png)

1. Usuário envia requisição HTTP GET para "http://54.233.35.134:8080/cep/00000000" na CepAPI (00000-000 também é aceitável)
2. ConsultaCepController recebe a requisição e chama ConsultaCepService para coordenar o processamento da requisição, enviando 00000000 como argumento
3. ConsultaCepService se comunica com o ConsultaCepClient, enviando 00000000 para consulta
4. ConsultaCepClient realiza uma requisição GET para o servidor mockado
5. Mock retorna os dados do CEP caso para o ConsultaCepClient estejam cadastrados em sua base de dados. Se não encontrar através da requisição, retorna um erro 400, que causa uma exceção no método do Client
6. ConsultaCepClient retorna os dados para ConsultaCepService. Caso tenha ocorrido exceção (HttpClientErrorException), ela é tratada aqui, e criada uma string com a mensagem de erro ("Erro ao consultar CEP 00000000; invalido ou indisponivel")
7. ConsultaCepService chama o método do LogsConsultasRepository que realizará o INSERT do log no banco de dados, enviando uma instância da entidade JPA referente à tabela logs_consultas como argumento
8. Comunicando-se com a database, log (conteúdo do retorno, data da consulta) é salvo na tabela logs_consultas
9. ConsultaCepService retorna um DTO, DadosCepOutput, para o ConsultaCepController, ou lança CepNaoEncontradoException a partir do tratamento da exceção anterior
10. ConsultaCepController retorna os dados do CEP em JSON. Caso não tenha encontrado as informações, retorna um JSON com a mensagem "Erro ao consultar CEP {CEP consultado}; invalido ou indisponivel"


Detalhes sobre a infraestrutura:
- O servidor mockado foi criado com Wiremock
- Imagens da aplicação do CepAPI e servidor mockado criadas no Docker
- Ambos estão sendo executados como containers na Amazon Elastic Container Service. Containers gerenciados pelo ECS Fargate
- Database MySQL dedicada ao armazenamento dos logs está hospedada na AWS Relational Database Service
- Security groups organizados de maneira que a única comunicação aberta ao público ocorre através do CepAPI (no momento, IP 54.233.35.134 porta 8080). Porta 8081 (do mock) não está aberta
- Usuário utilizado pela aplicação para comunicação com o banco de dados tem privilégios limitados
- Ambiente configurado com variáveis para garantir flexibilidade (URL do mock, URL da base de dados, login do user dedicado no BD, password)

Obs.: Para efeitos de demonstração, só foram cadastrados os CEPs 09080-001 e 13820-000.
