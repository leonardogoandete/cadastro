package br.edu.ifrs.poa.app.service;

import br.edu.ifrs.poa.app.enums.Role;
import br.edu.ifrs.poa.app.model.usuario.Usuario;
import br.edu.ifrs.poa.app.repository.UsuarioRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;

@Slf4j
@ApplicationScoped
public class UsuarioService{
    @Inject
    UsuarioRepository usuarioRepository;

    public void cadastrarUsuario(String nome, String cpf, String email, String senha){
        usuarioRepository.persist(new Usuario(nome, cpf, email, senha, Role.USUARIO));
    }


    public String validarUsuario(String cpf, String senha){
        Usuario usuario = usuarioRepository.findByCpf(cpf);
        if (usuario == null){
            return null;
        }
        String hashSenhaArmazenada = usuario.getSenha();
        String hashSenhaFornecida = BcryptUtil.bcryptHash(senha, 12, usuario.getSalt());

        if(hashSenhaArmazenada.equals(hashSenhaFornecida)){
            return gerarToken(cpf);
        }
        return null;
    }

    private String gerarToken(String cpf) {
        return Jwt.issuer("https://localhost:8081")
                .upn(cpf)
                .groups("USUARIO")
                .expiresAt(System.currentTimeMillis() + 3600)
                .innerSign()
                .encrypt();
    }
}
