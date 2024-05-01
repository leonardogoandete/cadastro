package br.edu.ifrs.poa.app.controller;

import br.edu.ifrs.poa.app.model.usuario.UsuarioDTO;
import br.edu.ifrs.poa.app.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.logging.Logger;

@Path("/register")
@Consumes(MediaType.APPLICATION_JSON)
public class CadastroController {
    private static final Logger logger = Logger.getLogger(CadastroController.class.getName());

    @Inject
    UsuarioService usuarioService;

    @POST
    @Transactional
    public Response registerUser(@Valid UsuarioDTO usuario){
        usuarioService.cadastrarUsuario(usuario.nome(), usuario.cpf(), usuario.email(), usuario.senha());
        logger.info("Registering user");
        return Response.status(Response.Status.OK).build();
    }
}
