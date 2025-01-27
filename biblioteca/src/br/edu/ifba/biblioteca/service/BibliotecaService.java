package br.edu.ifba.biblioteca.service;

import br.edu.ifba.biblioteca.exception.BibliotecaException;
import br.edu.ifba.biblioteca.model.*;
import br.edu.ifba.biblioteca.util.FileUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BibliotecaService {
    private Map<String, Usuario> usuarios;
    private Map<String, Livro> livros;
    private static final String USUARIOS_FILE = "usuarios.dat";
    private static final String LIVROS_FILE = "livros.dat";

    public BibliotecaService() {
        usuarios = new HashMap<>();
        livros = new HashMap<>();
        carregarDados();
    }

    // Métodos para Usuários
    public void cadastrarUsuario(Usuario usuario) throws BibliotecaException {
        if (usuarios.containsKey(usuario.getCpf())) {
            throw new BibliotecaException("CPF já cadastrado");
        }
        usuarios.put(usuario.getCpf(), usuario);
        salvarDados();
    }

    public void editarUsuario(Usuario usuario) throws BibliotecaException {
        if (!usuarios.containsKey(usuario.getCpf())) {
            throw new BibliotecaException("Usuário não encontrado");
        }
        usuarios.put(usuario.getCpf(), usuario);
        salvarDados();
    }

    public void removerUsuario(String cpf) throws BibliotecaException {
        if (!usuarios.containsKey(cpf)) {
            throw new BibliotecaException("Usuário não encontrado");
        }
        usuarios.remove(cpf);
        salvarDados();
    }

    public Usuario buscarUsuario(String cpf) throws BibliotecaException {
        Usuario usuario = usuarios.get(cpf);
        if (usuario == null) {
            throw new BibliotecaException("Usuário não encontrado");
        }
        return usuario;
    }

    public List<Usuario> listarUsuariosPorTipo(Class<?> tipo) {
        return usuarios.values().stream()
                .filter(u -> tipo.isInstance(u))
                .sorted(Comparator.comparing(Usuario::getNome))
                .toList();
    }

    // Métodos para Livros
    public void cadastrarLivro(Livro livro) throws BibliotecaException {
        if (livros.containsKey(livro.getIsbn())) {
            throw new BibliotecaException("ISBN já cadastrado");
        }
        livros.put(livro.getIsbn(), livro);
        salvarDados();
    }

    public void editarLivro(Livro livro) throws BibliotecaException {
        if (!livros.containsKey(livro.getIsbn())) {
            throw new BibliotecaException("Livro não encontrado");
        }
        livros.put(livro.getIsbn(), livro);
        salvarDados();
    }

    public void removerLivro(String isbn) throws BibliotecaException {
        if (!livros.containsKey(isbn)) {
            throw new BibliotecaException("Livro não encontrado");
        }
        livros.remove(isbn);
        salvarDados();
    }

    public Livro buscarLivro(String isbn) throws BibliotecaException {
        Livro livro = livros.get(isbn);
        if (livro == null) {
            throw new BibliotecaException("Livro não encontrado");
        }
        return livro;
    }

    public List<Livro> listarLivros() {
        return livros.values().stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .toList();
    }

    // Métodos de Empréstimo
    public void realizarEmprestimo(String cpf, String isbn) throws BibliotecaException {
        Usuario usuario = buscarUsuario(cpf);
        Livro livro = buscarLivro(isbn);

        usuario.emprestarLivro(isbn);
        livro.emprestar();
        salvarDados();
    }

    public void devolverLivro(String cpf, String isbn) throws BibliotecaException {
        Usuario usuario = buscarUsuario(cpf);
        Livro livro = buscarLivro(isbn);

        usuario.devolverLivro(isbn);
        livro.devolver();
        salvarDados();
    }

    // Métodos de Persistência
    @SuppressWarnings("unchecked")
    private void carregarDados() {
        try {
            Object usuariosObj = FileUtil.carregarObjeto(USUARIOS_FILE);
            Object livrosObj = FileUtil.carregarObjeto(LIVROS_FILE);

            if (usuariosObj != null) {
                usuarios = (Map<String, Usuario>) usuariosObj;
            }
            if (livrosObj != null) {
                livros = (Map<String, Livro>) livrosObj;
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void salvarDados() {
        try {
            FileUtil.salvarObjeto(usuarios, USUARIOS_FILE);
            FileUtil.salvarObjeto(livros, LIVROS_FILE);
        } catch (Exception e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
}
