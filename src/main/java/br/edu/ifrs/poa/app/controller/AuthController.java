package br.edu.ifrs.poa.app.controller;

import br.edu.ifrs.poa.app.model.login.Login;
import br.edu.ifrs.poa.app.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.logging.Logger;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {
    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @Inject
    UsuarioService usuarioService;

    @POST
    //fazer login e retornar um Response com token
    public Response login(Login login){
        String getToken = usuarioService.validarUsuario(login.cpf(), login.senha());
        if(getToken != null){
            logger.info("Login successful");
            HashMap<String, String> token = new HashMap<>();
            token.put("token", getToken);

            return Response.status(Response.Status.OK).entity(token).build();

        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }



}
