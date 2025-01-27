
package br.edu.ifba.biblioteca.model;

import br.edu.ifba.biblioteca.exception.BibliotecaException;

import java.io.Serializable;
import java.util.List;

public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;

    private String isbn;
    private String titulo;
    private List<String> autores;
    private String editora;
    private int numeroPaginas;
    private int quantidade;

    public Livro(String isbn, String titulo, List<String> autores,
            String editora, int numeroPaginas, int quantidade) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autores = autores;
        this.editora = editora;
        this.numeroPaginas = numeroPaginas;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void emprestar() throws BibliotecaException {
        if (quantidade <= 0) {
            throw new BibliotecaException("Não há exemplares disponíveis");
        }
        quantidade--;
    }

    public void devolver() {
        quantidade++;
    }

    @Override
    public String toString() {
        return String.format("Livro: %s, ISBN: %s, Autores: %s, Quantidade: %d",
                titulo, isbn, String.join(", ", autores), quantidade);
    }
}
