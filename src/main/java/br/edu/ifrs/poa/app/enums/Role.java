package br.edu.ifrs.poa.app.enums;

public enum Role {
    USUARIO(1, "Usuario");

    private final int id;
    private final String descricao;
    Role(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }
    public String getDescricao() {
        return descricao;
    }
}
