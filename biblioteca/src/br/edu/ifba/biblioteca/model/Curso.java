
package br.edu.ifba.biblioteca.model;

public enum Curso {
ADS("ADS"),
REDES("Redes"),
MULTIMIDIA("Multimidia");

private String descricao;

Curso(String descricao) {
this.descricao = descricao;
}

public String getDescricao() {
return descricao;
}
}

