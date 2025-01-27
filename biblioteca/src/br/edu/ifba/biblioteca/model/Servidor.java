
package br.edu.ifba.biblioteca.model;

public class Servidor extends Usuario {
private static final long serialVersionUID = 1L;

private Cargo cargo;

public Servidor(String cpf, String nome, String endereco, String telefone,
String email, String matricula, Cargo cargo) {
super(cpf, nome, endereco, telefone, email, matricula);
this.cargo = cargo;
}

public Cargo getCargo() { return cargo; }
public void setCargo(Cargo cargo) { this.cargo = cargo; }

@Override
public int getLimiteEmprestimos() {
return 5;
}

@Override
public String toString() {
return String.format("Servidor: %s, CPF: %s, Cargo: %s",
getNome(), getCpf(), cargo.getDescricao());
}
}

