
package br.edu.ifba.biblioteca.model;

public enum Cargo {
DOCENTE("Docente"),
TECNICO("Técnico Administrativo");

private String descricao;

Cargo(String descricao) {
this.descricao = descricao;
}

public String getDescricao() {
return descricao;
}
}



