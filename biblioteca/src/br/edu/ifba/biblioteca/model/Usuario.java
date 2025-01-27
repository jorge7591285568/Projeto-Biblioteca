
package br.edu.ifba.biblioteca.model;

import br.edu.ifba.biblioteca.exception.BibliotecaException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String matricula;
    private List<String> emprestimos;

    public Usuario(String cpf, String nome, String endereco, String telefone, String email, String matricula) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.matricula = matricula;
        this.emprestimos = new ArrayList<>();
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<String> getEmprestimos() {
        return emprestimos;
    }

    public abstract int getLimiteEmprestimos();

    public void emprestarLivro(String isbn) throws BibliotecaException {
        if (emprestimos.size() >= getLimiteEmprestimos()) {
            throw new BibliotecaException("Limite de empréstimos atingido");
        }
        if (emprestimos.contains(isbn)) {
            throw new BibliotecaException("Usuário já possui este livro emprestado");
        }
        emprestimos.add(isbn);
    }

    public void devolverLivro(String isbn) throws BibliotecaException {
        if (!emprestimos.contains(isbn)) {
            throw new BibliotecaException("Usuário não possui este livro emprestado");
        }
        emprestimos.remove(isbn);
    }
}
