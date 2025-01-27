
package br.edu.ifba.biblioteca.view;

import br.edu.ifba.biblioteca.exception.BibliotecaException;
import br.edu.ifba.biblioteca.model.*;
import br.edu.ifba.biblioteca.service.BibliotecaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaUi {
    private Scanner scanner;
    private BibliotecaService bibliotecaService;

    public BibliotecaUi() {
        scanner = new Scanner(System.in);
        bibliotecaService = new BibliotecaService();
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
        scanner.close();
    }

    private void exibirMenu() {
        System.out.println("\n=== SISTEMA DE BIBLIOTECA ===");
        System.out.println("1. Cadastrar Usuario");
        System.out.println("2. Editar Usuario");
        System.out.println("3. Remover Usuario");
        System.out.println("4. Listar Alunos");
        System.out.println("5. Listar Servidores");
        System.out.println("6. Cadastrar Livro");
        System.out.println("7. Editar Livro");
        System.out.println("8. Remover Livro");
        System.out.println("9. Listar Livros");
        System.out.println("10. Realizar Emprestimo");
        System.out.println("11. Devolver Livro");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: ");
    }

    public int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * @param opcao
     */
    private void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> editarUsuario();
                case 3 -> removerUsuario();
                case 4 -> listarAlunos();
                case 5 -> listarServidores();
                case 6 -> cadastrarLivro();
                case 7 -> editarLivro();
                case 8 -> removerLivro();
                case 9 -> listarLivros();
                case 10 -> realizarEmprestimo();
                case 11 -> devolverLivro();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } catch (BibliotecaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void cadastrarUsuario() throws BibliotecaException {
        System.out.println("\n=== CADASTRO DE USUÁRIO ===");

        // Solicita o tipo de usuário
        System.out.println("Tipo de usuário (1-Aluno, 2-Servidor): ");
        int tipo = Integer.parseInt(scanner.nextLine());
        if (tipo != 1 && tipo != 2) {
            throw new BibliotecaException("Tipo de usuário inválido");
        }

        // Coleta dados comuns
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        if (cpf.trim().isEmpty()) {
            throw new BibliotecaException("CPF não pode ser vazio");
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            throw new BibliotecaException("Nome não pode ser vazio");
        }

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        // Cria usuário específico baseado no tipo
        if (tipo == 1) {
            System.out.println("Curso (1-ADS, 2-REDES, 3-MULTIMIDIA): ");
            int cursoOpcao = Integer.parseInt(scanner.nextLine());
            if (cursoOpcao < 1 || cursoOpcao > 3) {
                throw new BibliotecaException("Curso inválido");
            }
            Curso curso = Curso.values()[cursoOpcao - 1];
            bibliotecaService.cadastrarUsuario(
                    new Aluno(cpf, nome, endereco, telefone, email, matricula, curso));
        } else {
            System.out.println("Cargo (1-DOCENTE, 2-TECNICO): ");
            int cargoOpcao = Integer.parseInt(scanner.nextLine());
            if (cargoOpcao < 1 || cargoOpcao > 2) {
                throw new BibliotecaException("Cargo inválido");
            }
            Cargo cargo = Cargo.values()[cargoOpcao - 1];
            bibliotecaService.cadastrarUsuario(
                    new Servidor(cpf, nome, endereco, telefone, email, matricula, cargo));
        }
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void editarUsuario() throws BibliotecaException {
        System.out.println("\n=== EDIÇÃO DE USUÁRIO ===");
        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();

        // Busca o usuário e mostra dados atuais
        Usuario usuario = bibliotecaService.buscarUsuario(cpf);
        System.out.println("Usuário encontrado: " + usuario);

        // Coleta novos dados (permite manter dados existentes)
        System.out.print("Novo nome (Enter para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty())
            usuario.setNome(nome);

        System.out.print("Novo endereço (Enter para manter): ");
        String endereco = scanner.nextLine();
        if (!endereco.isEmpty())
            usuario.setEndereco(endereco);

        System.out.print("Novo telefone (Enter para manter): ");
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty())
            usuario.setTelefone(telefone);

        System.out.print("Novo email (Enter para manter): ");
        String email = scanner.nextLine();
        if (!email.isEmpty())
            usuario.setEmail(email);

        // Atualiza dados específicos baseado no tipo de usuário
        if (usuario instanceof Aluno) {
            System.out.println("Novo curso (1-ADS, 2-REDES, 3-MULTIMIDIA, Enter para manter): ");
            String cursoOpcao = scanner.nextLine();
            if (!cursoOpcao.isEmpty()) {
                int opcao = Integer.parseInt(cursoOpcao);
                if (opcao < 1 || opcao > 3) {
                    throw new BibliotecaException("Curso inválido");
                }
                Curso curso = Curso.values()[opcao - 1];
                ((Aluno) usuario).setCurso(curso);
            }
        } else if (usuario instanceof Servidor) {
            System.out.println("Novo cargo (1-DOCENTE, 2-TECNICO, Enter para manter): ");
            String cargoOpcao = scanner.nextLine();
            if (!cargoOpcao.isEmpty()) {
                int opcao = Integer.parseInt(cargoOpcao);
                if (opcao < 1 || opcao > 2) {
                    throw new BibliotecaException("Cargo inválido");
                }
                Cargo cargo = Cargo.values()[opcao - 1];
                ((Servidor) usuario).setCargo(cargo);
            }
        }

        bibliotecaService.editarUsuario(usuario);
        System.out.println("Usuário editado com sucesso!");
    }

    private void removerUsuario() throws BibliotecaException {
        System.out.println("\n=== REMOÇÃO DE USUÁRIO ===");
        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();

        bibliotecaService.removerUsuario(cpf);
        System.out.println("Usuário removido com sucesso!");
    }

    private void listarAlunos() {
        System.out.println("\n=== LISTA DE ALUNOS ===");
        List<Usuario> alunos = bibliotecaService.listarUsuariosPorTipo(Aluno.class);
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        alunos.forEach(System.out::println);
    }

    private void listarServidores() {
        System.out.println("\n=== LISTA DE SERVIDORES ===");
        List<Usuario> servidores = bibliotecaService.listarUsuariosPorTipo(Servidor.class);
        if (servidores.isEmpty()) {
            System.out.println("Nenhum servidor cadastrado.");
            return;
        }
        servidores.forEach(System.out::println);
    }

    private void cadastrarLivro() throws BibliotecaException {
        System.out.println("\n=== CADASTRO DE LIVRO ===");

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        if (isbn.trim().isEmpty()) {
            throw new BibliotecaException("ISBN não pode ser vazio");
        }

        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        if (titulo.trim().isEmpty()) {
            throw new BibliotecaException("Título não pode ser vazio");
        }

        // Coleta autores
        System.out.print("Quantidade de autores: ");
        int qtdAutores = Integer.parseInt(scanner.nextLine());
        if (qtdAutores <= 0) {
            throw new BibliotecaException("Quantidade de autores deve ser maior que zero");
        }

        List<String> autores = new ArrayList<>();
        for (int i = 0; i < qtdAutores; i++) {
            System.out.print("Autor " + (i + 1) + ": ");
            String autor = scanner.nextLine();
            if (autor.trim().isEmpty()) {
                throw new BibliotecaException("Nome do autor não pode ser vazio");
            }
            autores.add(autor);
        }

        System.out.print("Editora: ");
        String editora = scanner.nextLine();

        System.out.print("Número de páginas: ");
        int numPaginas = Integer.parseInt(scanner.nextLine());
        if (numPaginas <= 0) {
            throw new BibliotecaException("Número de páginas deve ser maior que zero");
        }

        System.out.print("Quantidade disponível: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        if (quantidade < 0) {
            throw new BibliotecaException("Quantidade não pode ser negativa");
        }

        Livro livro = new Livro(isbn, titulo, autores, editora, numPaginas, quantidade);
        bibliotecaService.cadastrarLivro(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private void editarLivro() throws BibliotecaException {
        System.out.println("\n=== EDIÇÃO DE LIVRO ===");
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();

        Livro livro = bibliotecaService.buscarLivro(isbn);
        System.out.println("Livro encontrado: " + livro);

        System.out.print("Novo título (Enter para manter): ");
        String titulo = scanner.nextLine();
        if (!titulo.isEmpty())
            livro.setTitulo(titulo);

        System.out.print("Deseja editar autores? (S/N): ");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            System.out.print("Quantidade de autores: ");
            int qtdAutores = Integer.parseInt(scanner.nextLine());
            if (qtdAutores <= 0) {
                throw new BibliotecaException("Quantidade de autores deve ser maior que zero");
            }

            List<String> autores = new ArrayList<>();
            for (int i = 0; i < qtdAutores; i++) {
                System.out.print("Autor " + (i + 1) + ": ");
                String autor = scanner.nextLine();
                if (autor.trim().isEmpty()) {
                    throw new BibliotecaException("Nome do autor não pode ser vazio");
                }
                autores.add(autor);
            }
            livro.setAutores(autores);
        }

        System.out.print("Nova editora (Enter para manter): ");
        String editora = scanner.nextLine();
        if (!editora.isEmpty())
            livro.setEditora(editora);

        System.out.print("Novo número de páginas (Enter para manter): ");
        String numPaginas = scanner.nextLine();
        if (!numPaginas.isEmpty()) {
            int paginas = Integer.parseInt(numPaginas);
            if (paginas <= 0) {
                throw new BibliotecaException("Número de páginas deve ser maior que zero");
            }
            livro.setNumeroPaginas(paginas);
        }

        bibliotecaService.editarLivro(livro);
        System.out.println("Livro editado com sucesso!");
    }

    private void removerLivro() throws BibliotecaException {
        System.out.println("\n=== REMOÇÃO DE LIVRO ===");
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();

        bibliotecaService.removerLivro(isbn);
        System.out.println("Livro removido com sucesso!");
    }

    private void listarLivros() {
        System.out.println("\n=== LISTA DE LIVROS ===");
        List<Livro> livros = bibliotecaService.listarLivros();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        livros.forEach(System.out::println);
    }

    private void realizarEmprestimo() throws BibliotecaException {
        System.out.println("\n=== REALIZAR EMPRÉSTIMO ===");
        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();

        bibliotecaService.realizarEmprestimo(cpf, isbn);
        System.out.println("Empréstimo realizado com sucesso!");
    }

    private void devolverLivro() throws BibliotecaException {
        System.out.println("\n=== DEVOLVER LIVRO ===");
        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();

        bibliotecaService.devolverLivro(cpf, isbn);
        System.out.println("Devolução realizada com sucesso!");
    }
}