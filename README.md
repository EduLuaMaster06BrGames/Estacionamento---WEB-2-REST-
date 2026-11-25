Enunciado:
Tendo como base os conceitos de WebService REST e os demais assuntos estudados, desenvolva um servidor REST conforme especificado:
a)     (1,0) o servidor deve possuir conexão com o banco de dados
b)     (1,0) deve haver pelo menos 2 tabelas que se relacionam, de modo que o relacionamento entre elas seja no mínimo 1-N
c)     (1,0) o projeto deve ser desenvolvido utilizando o padrão Data Access Object (DAO) e a arquitetura M(V)C
d)     (3,0) para cada objeto deve-se fornecer os seguintes serviços em formato JSON: insert, delete, update, select by id, select all os quais devem ser implementados utilizando framework de persistência de dados e devem ser validados, lançando exceções conforme os possíveis erros previstos (lançar exceção com os códigos de erro HTTP)
e)     (1,0) para cada objeto deve-se fornecer no mínimo 2 selects extras, além dos definidos na letra anterior
f)       (1,0) além dos objetos já definidos, deve-se implementar serviços para gerenciar o usuário do sistema
g)     (2,0) os serviços de insert e update só podem ser requisitados se o usuário tiver permissão e estiver autenticado no sistema, para isso, deve-se implementar uma camada de segurança/autenticação
 Observações:
- o tema é livre
- a linguagem de programação e o banco de dados a ser utilizado é de sua escolha
