package br.edu.ifrs.poa.app.model.usuario;

import br.edu.ifrs.poa.app.enums.Role;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private byte[] salt;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Role role;

    private byte[] gerarSalt(){
        byte[] salt = new byte[8];
        new Random().nextBytes(salt);
        StringBuffer sb = new StringBuffer();
        for (byte b : salt){
            sb.append(String.format("%02x", b));
        }
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    public Usuario(String nome, String cpf, String email, String senha, Role role) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.salt = gerarSalt();
        this.senha = hashSenha(senha, this.salt);
        this.role = role;
    }



    private String hashSenha(String senha, byte[] salt){
        return BcryptUtil.bcryptHash(senha, 12, salt);
    }
}
