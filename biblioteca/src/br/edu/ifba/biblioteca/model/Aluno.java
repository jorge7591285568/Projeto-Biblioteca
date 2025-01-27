
package br.edu.ifba.biblioteca.model;

public class Aluno extends Usuario {
    private static final long serialVersionUID = 1L;

    private Curso curso;

    public Aluno(String cpf, String nome, String endereco, String telefone,
            String email, String matricula, Curso curso) {
        super(cpf, nome, endereco, telefone, email, matricula);
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public int getLimiteEmprestimos() {
        return 3;
    }

    @Override
    public String toString() {
        return String.format("Aluno: %s, CPF: %s, Curso: %s",
                getNome(), getCpf(), curso.getDescricao());
    }
}
