package br.edu.ifba.biblioteca.exception;

public class BibliotecaException extends Exception {
    private static final long serialVersionUID = 1L;

    public BibliotecaException(String mensagem) {
        super(mensagem);
    }
}
