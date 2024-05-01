package br.edu.ifrs.poa.app.model.usuario;

public record UsuarioDTO(
    String nome,
    String cpf,
    String email,
    String senha
) {
}
