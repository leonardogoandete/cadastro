package br.edu.ifrs.poa.app.repository;

import br.edu.ifrs.poa.app.model.usuario.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    public Usuario findByCpf(String cpf){
        return find("cpf", cpf).firstResult();
    }
}
