package br.com.fiap.domain.resources;

import br.com.fiap.domain.entity.animal.Animal;
import br.com.fiap.domain.service.AnimalService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import org.glassfish.jersey.server.Uri;

import java.net.URI;
import java.util.List;
import java.util.Objects;

public class AnimalResource implements Resource<Animal, Long> {

    @Context
    UriInfo uriInfo;

    AnimalService service = new AnimalService();

    @GET
    @Override
    public Response findAll() {
        List<Animal> all = service.findAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Animal animal = service.findById(id);
        if (Objects.isNull(animal)) return Response.status(404).build();
        return Response.ok(animal).build();
    }

    @POST
    @Override
    public Response persiste(Animal animal) {
        animal.setId(null);
        Animal animais = service.persiste(animal);

        if (Objects.isNull(animais.getId()))
            return Response.notModified( "Não foi possível persistir: " + animal ).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( animal.getId() ) ).build();

        return Response.created(uri).entity(animal).build();
    }
}