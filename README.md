# Projeto-Biblioteca

Projeto Biblioteca

Desenvolver um sistema de gestão de uma biblioteca utilizando conceitos de Programação Orientada a Objetos. O sistema permitirá gerenciar livros, usuários e empréstimos. A aplicação deve ser capaz de realizar operações básicas como cadastro, consulta, alteração e remoção de dados, além de permitir o salvamento e carregamento de dados em arquivos.

Cada grupo (2 componentes no máximo) deve desenvolver um software para uma biblioteca de uma universidade. O sistema será utilizado por um funcionário da biblioteca para realizar o cadastro de usuários, cadastro de livros, além de permitir a realização de empréstimos dos livros aos usuários. As funcionalidades esperadas do sistema são:

●	Cadastro e usuários
●	Edição de usuários
●	Remoção de usuários
●	Listar todos por tipo usuários (aluno ou professor)
●	Cadastro de livros
●	Edição de livros
●	Remoção de livros
●	Listar todos os livros
●	Realizar empréstimo
●	Devolver livro
●	Registro dos dados em arquivos

As principais entidades manipuladas pelo sistema são descritas a seguir. Podem ser adicionados novos atributos que permita a realização das operações do sistema.

●	Usuário: CPF, nome, endereço, telefone, email e matrícula.
●	Usuário Aluno: curso.
●	Usuário Servidor: cargo.
●	Livro: ISBN, título, autor(es), editora, número de páginas e quantidade.

Os possíveis cursos são: ADS, Redes e Multimidia.
Os possíveis cargos são: Docente e Técnico Administrativo

Regras de Negócio
●	Alunos podem pegar até 3 livros por vez.
●	Servidores podem pegar até 5 livros por vez.
●	Um livro só pode ser emprestado se houver exemplares disponíveis no estoque.
●	A listagem dos usuários e livros deve apresentá-los ordenados de forma crescente pelo nome e título, respectivamente.
●	Nas operações de busca, remoção e edição deve-se buscar os usuários pelo CPF e o livro pelo ISBN.
●	Não pode haver dois usuários com o mesmo CPF no sistema, caso o usuário tente cadastrar um usuário com o mesmo CPF deve ser gerada uma exceção. Essa validação deve ser feita no cadastro e edição.
●	Não pode haver dois livros com o mesmo ISBN no sistema, caso o usuário tente cadastrar um livro com o mesmo ISBN deve ser gerada uma exceção. Essa validação deve ser feita no cadastro e edição.
●	As operações de listagem devem mostrar todos os campos cadastrados para o livro ou usuário.
●	A operação de remoção deverá pedir o ISBN ou CPF, se achar a entidade deve remove-la, caso não ache deve gerar uma exceção.
●	Na edição deve ser pedido primeiro o CPF ou ISBN, caso achar o usuário permitir mudar os valores cadastrados, caso não ache deve gerar uma exceção.
●	Um usuário não pode ter o dois empréstimos do mesmo livro, caso tentar pegar o mesmo livro gerar uma exceção.
●	Na função de empréstimo, o usuário deverá registrar em sua lista de empréstimos o ISBN do livro e deverá ser reduzido 1 da quantidade de exemplares disponíveis do livro.
●	Caso não tenha um exemplar disponível do livro, não deixe que o empréstimo seja realizado e lançar uma exceção.
●	A funcionalidade de empréstimo deve pedir o ISBN do livro e o CPF do usuário, caso algum desses não seja achado então lançar uma exceção, caso achar ambos então adicionar o ISBN do livro aos empréstimos do usuário e reduzir 1 da quantidade de exemplares do livro.
●	Na devolução deverá pedir o ISBN do livro e o CPF do usuário, caso algum desses não seja achado então lançar uma exceção, caso achar ambos então remover o ISBN do livro aos empréstimos do usuário e aumentar 1 da quantidade de exemplares do livro.
●	Deverá ser criados 2 arquivos, um para os usuários e outro para os livros. Quando o programa iniciar os dados dos arquivos devem ser carregados e ao usuário escolher a opção de finalizar o programa então sobrescrever o arquivos com as novas informações dos usuários e livros.

Entrega

Deverá ser enviado no formato abaixo o nome da dupla e o link do GitHub ou GitLab contendo o seu projeto.

https://docs.google.com/forms/d/e/1FAIpQLSenvQKwf7iQh5Jyh8VFKaeAcNrK6JgPC1A2kutAUtn3RZJQxQ/viewform?usp=dialog


