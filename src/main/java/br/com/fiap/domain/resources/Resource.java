package br.com.fiap.domain.resources;

import jakarta.ws.rs.core.Response;

public interface Resource<T, U> {

    public Response findAll();

    public Response findById(U id);

    public Response findByTexto(String texto);

    public Response persiste(T t);

    public Response update(U id, T t);

    public Response delete(U id);

}
